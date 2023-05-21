import {changeHashLocation} from "../../utils/change-hash-location.js";
import {DeleteListFetch} from "../../api/fetch/lists/DeleteListFetch.js";
import {DeleteButton} from "../../ui/button/delete-button.js";

function DeleteListHandler(state) {

    async function deleteList(event) {
        event.preventDefault()

        const response = await DeleteListFetch(state.body["id"])

        const json = await response.json()

        if(response.ok)
            changeHashLocation(`#boards/${state.body["boardId"]}`)
        else
            alert(json.error)
    }

    return DeleteButton(deleteList)
}

export default DeleteListHandler;