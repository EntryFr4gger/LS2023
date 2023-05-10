import {changeHashLocation} from "../../utils/change-hash-location.js";
import {form} from "../../dom/domTags.js";
import {dangerOutlineButton} from "../../ui/button/color-buttons.js";
import {DeleteCardFetch} from "../../api/fetch/cards/DeleteCardsFetch.js";

function DeleteCardHandler(state) {

    async function deleteCard(event) {
        event.preventDefault()
        await DeleteCardFetch(state.body["id"])
        changeHashLocation(`#lists/${state.body["listId"]}`)
    }

    return form(
        {onSubmit: deleteCard},
        dangerOutlineButton("Delete", "submit")
    )
}

export default DeleteCardHandler;