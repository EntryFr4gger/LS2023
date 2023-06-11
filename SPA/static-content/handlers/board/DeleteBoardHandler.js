import {hashChangeLoc} from "../../components/utils/hash-change-loc.js";
import {getUser} from "../../components/utils/storage/get-user.js";
import {DeleteBoardFetch} from "../../components/api/fetch/boards/DeleteBoardFetch.js";
import {DeleteButton} from "../../components/ui/button/delete-button.js";

/**
 * DeleteBoardHandler is an asynchronous function that handles deleting a board.
 *
 * @param {Object} state - The state object containing the necessary data.
 *
 * @returns {Promise<HTMLElement>} A promise that resolves to the rendered deleted button for deleting a board.
 */
async function DeleteBoardHandler(state) {

    /**
     * deleteBoard is an asynchronous function that handles the button click event for deleting a board.
     *
     * @param {Event} event - The button click event.
     */
    async function deleteBoard(event) {
        event.preventDefault()

        const response = await DeleteBoardFetch(state.body["id"])

        if (response)
            hashChangeLoc(`#users/${getUser()}/boards`)
    }

    return await DeleteButton(deleteBoard, "light ps-3", "mm")
}

export default DeleteBoardHandler;