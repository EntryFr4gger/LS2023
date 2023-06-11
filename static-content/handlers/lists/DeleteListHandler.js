import {hashChangeLoc} from "../../components/utils/hash-change-loc.js";
import {DeleteListFetch} from "../../components/api/fetch/lists/DeleteListFetch.js";
import {DeleteButtonHover} from "../../components/ui/button/delete-button-hover.js";

/**
 * DeleteListHandler is a function that handles the deletion of a list.
 *
 * @param {Object} state - The state object containing the necessary information.
 * @param {Object} list - The list to be deleted.
 *
 * @returns {HTMLElement} The delete button with hover effect.
 */
function DeleteListHandler(state, list) {

    /**
     * DeleteList is an asynchronous function that handles the deletion of a list.
     *
     * @param {Event} event - The event object triggered by the delete action.
     */
    async function deleteList(event) {
        event.preventDefault()

        const response = await DeleteListFetch(list["id"])

        if (response) hashChangeLoc(`#boards/${state.body["id"]}`)
    }

    return DeleteButtonHover(deleteList)
}

export default DeleteListHandler;