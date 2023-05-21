import {changeHashLocation} from "../../utils/change-hash-location.js";
import {DeleteCardFetch} from "../../api/fetch/cards/DeleteCardsFetch.js";
import {DeleteButton} from "../../ui/button/delete-button.js";

function DeleteCardHandler(state) {

    async function deleteCard(event) {
        event.preventDefault()
        await DeleteCardFetch(state.body["id"])
        changeHashLocation(`#boards/${state.body["boardId"]}`)
    }

    return DeleteButton(deleteCard)
}

export default DeleteCardHandler;