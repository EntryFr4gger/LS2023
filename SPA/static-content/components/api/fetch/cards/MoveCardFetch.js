import {getUserToken} from "../../../utils/storage/get-token.js";
import SafeFetch from "../../../utils/safe-fetch.js";

/**
 * Executes a fetch request to API.
 * Moves a card to a list.
 *
 * @param {Number} cardId card unique identifier.
 * @param {Number} lid list unique identifier.
 *
 * @return {Promise} a card id.
 * */
export async function MoveCardFetch(cardId, lid = null) {
    return await SafeFetch(`cards/${cardId}`, {
        method: "PUT",
        headers: {Authorization: getUserToken()},
        body: JSON.stringify(
            {lid}
        )
    });
}