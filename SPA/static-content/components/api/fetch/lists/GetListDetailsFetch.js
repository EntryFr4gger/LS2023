import {getUserToken} from "../../../utils/storage/get-token.js";
import SafeFetch from "../../../utils/safe-fetch.js";

/**
 * Executes a fetch request to API.
 * Get detailed information of a list.
 *
 * @param {Number} listId list unique identifier.
 *
 * @return {Promise} a List.
 * */
export async function GetListDetailsFetch(listId) {
    return await SafeFetch(`lists/${listId}`, {
        headers: {Authorization: getUserToken()}
    });
}