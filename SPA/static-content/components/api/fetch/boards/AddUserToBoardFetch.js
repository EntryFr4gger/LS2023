import {getUserToken} from "../../../utils/storage/get-token.js";
import SafeFetch from "../../../utils/safe-fetch.js";

/**
 * Executes a fetch request to API.
 * Add a user to the board.
 *
 * @param {Number} userId user unique identifier.
 * @param {Number} boardId board unique identifier.
 *
 * @return {Promise} true if the user was added to the board, false otherwise.
 * */
export async function AddUserToBoardFetch(boardId, userId) {
    return await SafeFetch(`boards/${boardId}/users`, {
        method: "POST",
        headers: {Authorization: getUserToken()},
        body: JSON.stringify({id: userId})
    });
}