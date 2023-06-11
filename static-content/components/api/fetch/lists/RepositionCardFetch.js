import {getUserToken} from "../../../utils/storage/get-token.js";
import SafeFetch from "../../../utils/safe-fetch.js";

/**
 * Executes a fetch request to API.
 * Moves a card to a list.
 *
 * @param {Number} listId list unique identifier.
 * @param {Number} cardId card unique identifier.
 * @param {Number} cix desired index.
 *
 * @return {Promise} a card id.
 * */
export async function RepositionCardFetch(listId, cardId, cix) {
    return await SafeFetch(`lists/${listId}/cards`, {
        method: "PUT",
        headers: {Authorization: getUserToken()},
        body: JSON.stringify(
            {cardId, cix}
        )
    });
}