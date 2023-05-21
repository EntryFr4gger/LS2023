import {changeHashLocation} from "../../utils/change-hash-location.js";
import {CreateCardFetch} from "../../api/fetch/cards/CreateCardFetch.js";
import {ModalCreate} from "../../ui/modal/modal-create.js";

function CreateCardHandler(state, board, list) {

    async function createCard(event) {
        event.preventDefault()
        const boardId = board || state.body["boardId"]
        const listId = list || state.pathParams["list_id"]
        const name = document.getElementById("name-card").value
        const description = document.getElementById("description-card").value

        if (name.trim() === "" || description.trim() === "")
            alert("Please fill out all fields")
        else{
            const response =
                await CreateCardFetch(name, description, boardId, listId)

            const json = await response.json()

            if(response.ok)
                changeHashLocation(`#boards/${boardId}`)
            else
                alert(json.error)
        }
    }

    return ModalCreate(createCard, list)
}

export default CreateCardHandler;