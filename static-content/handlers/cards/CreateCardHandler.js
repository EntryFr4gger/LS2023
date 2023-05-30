import {hashChangeLoc} from "../../components/utils/hash-change-loc.js";
import {CreateCardFetch} from "../../components/api/fetch/cards/CreateCardFetch.js";
import {ModalCreate} from "../../components/ui/modal/modal-create.js";

function CreateCardHandler(board, list) {

    async function createCard(event) {
        event.preventDefault()
        const boardId = board || state.body["boardId"]
        const listId = list || state.pathParams["list_id"]
        const name = document.getElementById(`name-card-${list}`).value
        const description = document.getElementById(`description-card-${list}`).value

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

    return ModalCreate(createCard, list)
}

export default CreateCardHandler;