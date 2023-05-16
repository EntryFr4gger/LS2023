import {changeHashLocation} from "../../utils/change-hash-location.js";
import {getUser} from "../../utils/get-user.js";
import {form} from "../../dom/domTags.js";
import {dangerOutlineButton} from "../../ui/button/color-buttons.js";
import {DeleteBoardFetch} from "../../api/fetch/boards/DeleteBoardFetch.js";

function DeleteBoardHandler(state) {

    async function deleteBoard(event) {
        event.preventDefault()
        await DeleteBoardFetch(state.body["id"])
        changeHashLocation(`#users/${getUser()}/boards`)
    }

    return form(
        {onSubmit: deleteBoard},
        dangerOutlineButton("Delete", "submit")
    )
}

export default DeleteBoardHandler;