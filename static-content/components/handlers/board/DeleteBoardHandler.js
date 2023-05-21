import {changeHashLocation} from "../../utils/change-hash-location.js";
import {getUser} from "../../utils/get-user.js";
import {DeleteBoardFetch} from "../../api/fetch/boards/DeleteBoardFetch.js";
import {DeleteButton} from "../../ui/button/delete-button.js";

function DeleteBoardHandler(state) {

    async function deleteBoard(event) {
        event.preventDefault()

        const response = await DeleteBoardFetch(state.body["id"])

        const json = await response.json()

        if(response.ok)
            changeHashLocation(`#users/${getUser()}/boards`)
        else
            alert(json.error)
    }

    return DeleteButton(deleteBoard)
}

export default DeleteBoardHandler;