import {hashChangeLoc} from "../../components/utils/hash-change-loc.js";
import {DeleteListFetch} from "../../components/api/fetch/lists/DeleteListFetch.js";
import {DeleteButton} from "../../components/ui/button/delete-button.js";

function DeleteListHandler(state) {

    async function deleteList(event) {
        event.preventDefault()

        const response = await DeleteListFetch(state.body["id"])

        const json = await response.json()

        if (response.ok)
            hashChangeLoc(`#boards/${state.body["boardId"]}`)
        else
            alert(json.error)
    }

    return DeleteButton(deleteList)
}

export default DeleteListHandler;