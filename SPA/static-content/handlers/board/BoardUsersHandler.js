import BoardUsersPage from "../../pages/boards/BoardUsersPage.js";
import {GetBoardUsersFetch} from "../../components/api/fetch/boards/GetBoardUsersFetch.js";

/**
 * BoardUsersHandler is an asynchronous function that handles retrieving and displaying the users of a board.
 *
 * @param {Object} state - The state object containing the necessary data.
 *
 * @returns {Promise} A promise that resolves to the rendered page for displaying board users.
 * @throws {string} If the board_id parameter is invalid.
 */
async function BoardUsersHandler(state) {
    const boardId = state.pathParams["board_id"];
    if (isNaN(boardId))
        throw ("Invalid param id");

    const users = await GetBoardUsersFetch(boardId)

    if (users) {
        state.body = users

        return BoardUsersPage(state)
    }
}

export default BoardUsersHandler;