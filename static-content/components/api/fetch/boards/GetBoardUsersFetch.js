import {getUserToken} from "../../../utils/get-token.js";

/**
 * Executes a fetch request to API.
 * Get the list with the users of a board.
 *
 * @param {Int} boardId board unique identifier.
 *
 * @return {Promise} list of Users in a board.
 * */
export async function GetBoardUsersFetch(boardId) {
    return await fetch(`boards/${boardId}/users`, {
        headers: {Authorization: getUserToken()}
    });
}