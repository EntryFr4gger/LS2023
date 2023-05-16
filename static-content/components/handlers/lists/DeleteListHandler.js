import {changeHashLocation} from "../../utils/change-hash-location.js";
import {form} from "../../dom/domTags.js";
import {dangerOutlineButton} from "../../ui/button/color-buttons.js";
import {DeleteListFetch} from "../../api/fetch/lists/DeleteListFetch.js";

function DeleteListHandler(state) {

    async function deleteList(event) {
        event.preventDefault()
        await DeleteListFetch(state.body["id"])
        changeHashLocation(`#boards/${state.body["boardId"]}`)
    }

    return form(
        {onSubmit: deleteList},
        dangerOutlineButton("Delete", "submit")
    )
}

export default DeleteListHandler;