import {getUserToken} from "../../../utils/storage/get-token.js";
import SafeFetch from "../../../utils/safe-fetch.js";

/**
 * Executes a fetch request to API.
 * Get the set of cards in a list.
 *
 * @param {Number} listId list unique identifier.
 *
 * @return {Promise} list of Cards in List.
 * */
export async function GetListCardsFetch(listId) {
    return await SafeFetch(`lists/${listId}/cards`, {
        headers: {Authorization: getUserToken()}
    });
}