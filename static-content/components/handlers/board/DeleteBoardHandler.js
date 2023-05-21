import {changeHashLocation} from "../../utils/change-hash-location.js";
import {getUser} from "../../utils/get-user.js";
import {DeleteBoardFetch} from "../../api/fetch/boards/DeleteBoardFetch.js";
import {DeleteButton} from "../../ui/button/delete-button.js";

function DeleteBoardHandler(state) {

    async function deleteBoard(event) {
        event.preventDefault()
        await DeleteBoardFetch(state.body["id"])
        changeHashLocation(`#users/${getUser()}/boards`)
    }

    return DeleteButton(deleteBoard)
}

export default DeleteBoardHandler;