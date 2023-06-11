import {getUserToken} from "../../../utils/storage/get-token.js";
import SafeFetch from "../../../utils/safe-fetch.js";

/**
 * Executes a fetch request to API.
 * Get the detailed information on the board.
 *
 * @param {Number} boardId board unique identifier.
 *
 * @return {Promise} a Board.
 * */
export async function GetBoardDetailsFetch(boardId) {
    return await SafeFetch(`boards/${boardId}`, {
        headers: {Authorization: getUserToken()}
    });
}