import {hashChangeLoc} from "../../components/utils/hash-change-loc.js";
import {getUser} from "../../components/utils/get-user.js";
import {DeleteBoardFetch} from "../../components/api/fetch/boards/DeleteBoardFetch.js";
import {DeleteButton} from "../../components/ui/button/delete-button.js";

function DeleteBoardHandler(state) {

    async function deleteBoard(event) {
        event.preventDefault()

        const response = await DeleteBoardFetch(state.body["id"])

        const json = await response.json()

        if (response.ok)
            hashChangeLoc(`#users/${getUser()}/boards`)
        else
            alert(json.error)
    }

    return DeleteButton(deleteBoard)
}

export default DeleteBoardHandler;