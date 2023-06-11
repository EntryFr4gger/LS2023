import {getUserToken} from "../../../utils/storage/get-token.js";
import {getUser} from "../../../utils/storage/get-user.js";
import SafeFetch from "../../../utils/safe-fetch.js";

/**
 * Executes a fetch request to API.
 * Gets all Users that are not on the board.
 *
 * @param {Number} boardId to remove users from board.
 *
 * @return {Promise} list of Users.
 * */
export async function GetAllUsersNotInBoard(boardId) {
    return await SafeFetch(`users/${getUser()}`, {
        method: "POST",
        headers: {Authorization: getUserToken()},
        body: JSON.stringify({id: boardId})
    });
}