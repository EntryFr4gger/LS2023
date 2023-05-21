import {changeHashLocation} from "../../utils/change-hash-location.js";
import {DeleteCardFetch} from "../../api/fetch/cards/DeleteCardsFetch.js";
import {DeleteButton} from "../../ui/button/delete-button.js";

function DeleteCardHandler(state) {

    async function deleteCard(event) {
        event.preventDefault()

        const response = await DeleteCardFetch(state.body["id"])

        const json = await response.json()

        if(response.ok)
            changeHashLocation(`#boards/${state.body["boardId"]}`)
        else
            alert(json.error)
    }

    return DeleteButton(deleteCard)
}

export default DeleteCardHandler;