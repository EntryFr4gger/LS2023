import {getUserToken} from "../../../utils/storage/get-token.js";
import SafeFetch from "../../../utils/safe-fetch.js";

/**
 * Executes a fetch request to API.
 * Get the cards on a board.
 *
 * @param {Number} boardId board unique identifier.
 *
 * @return {Promise} list of cards in a board.
 * */
export async function GetBoardCardsArchivedFetch(boardId) {
    return await SafeFetch(`boards/${boardId}/cards?archived`, {
        headers: {Authorization: getUserToken()}
    });
}