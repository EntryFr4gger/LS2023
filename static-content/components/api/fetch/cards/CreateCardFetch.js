import {getUserToken} from "../../../utils/storage/get-token.js";
import SafeFetch from "../../../utils/safe-fetch.js";

/**
 * Executes a fetch request to API.
 * Creates a new card in a list.
 *
 * @param {String} name the task name.
 * @param {String} description the task description.
 * @param {Number} boardId board unique identifier.
 * @param {Number} listId list unique identifier.
 *
 * @return {Promise} new card unique identifier.
 * */
export async function CreateCardFetch(name, description, boardId, listId) {
    return await SafeFetch(`cards/`, {
        method: "POST",
        headers: {Authorization: getUserToken()},
        body: JSON.stringify(
            {name, description, boardId, listId}
        )
    });
}