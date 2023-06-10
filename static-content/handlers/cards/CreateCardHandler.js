import {hashChangeLoc} from "../../components/utils/hash-change-loc.js";
import {CreateCardFetch} from "../../components/api/fetch/cards/CreateCardFetch.js";
import {ModalCreate} from "../../components/ui/modal/modal-create.js";
import {DisableAttribute} from "../../components/utils/disable-attribute.js";

function CreateCardHandler(boardId, listId) {

    async function createCard(event) {
        event.preventDefault()
        const name = document.getElementById(`name-card-${listId}`).value
        const description = document.getElementById(`description-card-${listId}`).value

        DisableAttribute(event.target[2])

        if (name.trim() === "" || description.trim() === "")
            alert("Please fill out all fields")
        else {
            const response =
                await CreateCardFetch(name, description, boardId, listId)

            const json = await response.json()

            if (response.ok)
                hashChangeLoc(`#boards/${boardId}`)
            else
                alert(json.error)
        }
    }

    return ModalCreate(createCard, listId)
}

export default CreateCardHandler;