import {getUserToken} from "../../../utils/storage/get-token.js";
import SafeFetch from "../../../utils/safe-fetch.js";

/**
 * Executes a fetch request to API.
 * Creates a new list in a board.
 *
 * @param {String} name list name.
 * @param {Number} boardId board unique identifier.
 *
 * @return {Promise} new list unique identifier.
 * */
export async function CreateListFetch(name, boardId) {
    return await SafeFetch(`lists/`, {
        method: "POST",
        headers: {Authorization: getUserToken()},
        body: JSON.stringify({name, boardId})
    });
}