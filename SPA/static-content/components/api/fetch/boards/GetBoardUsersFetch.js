import {getUserToken} from "../../../utils/storage/get-token.js";
import SafeFetch from "../../../utils/safe-fetch.js";

/**
 * Executes a fetch request to API.
 * Get the list with the users of a board.
 *
 * @param {Number} boardId board unique identifier.
 *
 * @return {Promise} list of Users in a board.
 * */
export async function GetBoardUsersFetch(boardId) {
    return await SafeFetch(`boards/${boardId}/users`, {
        headers: {Authorization: getUserToken()}
    });
}