import {hashChangeLoc} from "../../components/utils/hash-change-loc.js";
import {AddUserToBoardFetch} from "../../components/api/fetch/boards/AddUserToBoardFetch.js";
import {FormAddUserBoard} from "../../components/ui/pagination/boards/FormAddUserBoard.js";
import GetAllUsersHandler from "../user/GetAllUsersHandler.js";

/**
 * AddUserToBoardHandler is an asynchronous function that handles adding a user to a board.
 *
 * @param {Object} state - The state object containing the necessary data.
 *
 * @returns {HTMLElement} A promise that resolves to the rendered form for adding a user to a board.
 */
async function AddUserToBoardHandler(state) {

    /**
     * addUserToBoard is an asynchronous function that handles the form submission for adding a user to a board.
     *
     * @param {Event} event - The form submission event.
     */
    async function addUserToBoard(event) {
        event.preventDefault()
        const boardId = state.pathParams["board_id"]
        const value = document.getElementById("exampleDataList").value
        const options = document.querySelectorAll('#datalistOptions option')

        const found = Array.from(options).filter(option => option.label === value)

        if (found.length !== 0) {
            const userId = found[0].getAttribute('data-value')

            const response = await AddUserToBoardFetch(boardId, userId)

            if (response)
                hashChangeLoc(`#boards/${boardId}/users`)
        } else {
            alert("That email doesn't exist")
        }
    }

    return await FormAddUserBoard(addUserToBoard, await GetAllUsersHandler(state))
}

export default AddUserToBoardHandler;