import {getUserToken} from "../../../utils/storage/get-token.js";
import SafeFetch from "../../../utils/safe-fetch.js";

/**
 * Executes a fetch request to API.
 * Delete a list.
 *
 * @param {Number} listId list unique identifier.
 *
 * @return {Promise} deleted List.
 * */
export async function DeleteListFetch(listId) {
    return await SafeFetch(
        `lists/${listId}`,
        {
            method: "DELETE",
            headers: {'Authorization': getUserToken()}
        }
    );
}