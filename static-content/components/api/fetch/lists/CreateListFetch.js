import {getUserToken} from "../../../utils/get-token.js";

/**
 * Executes a fetch request to API.
 * Creates a new list in a board.
 *
 * @param {String} name list name.
 * @param {Int} boardId board unique identifier.
 *
 * @return {Promise} new list unique identifier.
 * */
export async function CreateListFetch(name, boardId) {
    return await fetch(`lists/`, {
        method: "POST",
        headers: {Authorization: getUserToken()},
        body: JSON.stringify({name, boardId})
    });
}