import {hashChangeLoc} from "../../components/utils/hash-change-loc.js";
import {DeleteCardFetch} from "../../components/api/fetch/cards/DeleteCardsFetch.js";
import {DeleteButton} from "../../components/ui/button/delete-button.js";

function DeleteCardHandler(state) {

    async function deleteCard(event) {
        event.preventDefault()

        const response = await DeleteCardFetch(state.body["id"])

        const json = await response.json()

        if (response.ok)
            hashChangeLoc(`#boards/${state.body["boardId"]}`)
        else
            alert(json.error)
    }

    return DeleteButton(deleteCard)
}

export default DeleteCardHandler;