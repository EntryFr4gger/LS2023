import {GetBoardCardsArchivedFetch} from "../../components/api/fetch/boards/GetBoardCardsArchivedFetch.js";
import ArchivedCardsPage from "../../pages/cards/ArchivedCardsPage.js";

/**
 * ArchivedCardHandler is an asynchronous function that handles retrieving and displaying the archived cards of a board.
 *
 * @param {Object} state - The state object containing the necessary data.
 *
 * @returns {Promise} A promise that resolves to the rendered page for displaying archived cards.
 * @throws {string} If the board_id parameter is invalid.
 */
async function ArchivedCardHandler(state) {
    const id = state.pathParams["board_id"];
    if (isNaN(id))
        throw ("Invalid param id");

    const archivedCards = await GetBoardCardsArchivedFetch(id)

    if (archivedCards) {
        state.body = archivedCards

        return await ArchivedCardsPage(state)
    }
}

export default ArchivedCardHandler;