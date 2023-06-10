import {getUserToken} from "../../../utils/get-token.js";
import {getUser} from "../../../utils/get-user.js";

/**
 * Executes a fetch request to API.
 * Gets all Users in the database.
 *
 * @param {Int} boardId to remove users from board.
 *
 * @return {Promise} list of Users.
 * */
export async function GetAllUsers(boardId) {
    return await fetch(`users/${getUser()}`, {
        method: "POST",
        headers: {Authorization: getUserToken()},
        body: JSON.stringify({id: boardId})
    });
}