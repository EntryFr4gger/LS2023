import {getUserToken} from "../../../utils/get-token.js";

/**
 * Executes a fetch request to API.
 * Add a user to the board.
 *
 * @param {Int} userId user unique identifier.
 * @param {Int} boardId board unique identifier.
 *
 * @return {Promise} true if the user was added to the board, false otherwise.
 * */
export async function AddUserToBoardFetch(boardId, userId) {
    return await fetch(`boards/${boardId}/users`, {
        method: "POST",
        headers: {Authorization: getUserToken()},
        body: JSON.stringify({id: userId})
    });
}