import {changeHashLocation} from "../../utils/change-hash-location.js";
import {DeleteListFetch} from "../../api/fetch/lists/DeleteListFetch.js";
import {DeleteButton} from "../../ui/button/delete-button.js";

function DeleteListHandler(state) {

    async function deleteList(event) {
        event.preventDefault()
        await DeleteListFetch(state.body["id"])
        changeHashLocation(`#boards/${state.body["boardId"]}`)
    }

    return DeleteButton(deleteList)
}

export default DeleteListHandler;