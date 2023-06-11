import {getUserToken} from "../../../utils/storage/get-token.js";
import SafeFetch from "../../../utils/safe-fetch.js";

/**
 * Executes a fetch request to API.
 * Delete a card.
 *
 * @param {Number} cardId card unique identifier.
 *
 * @return {Promise} deleted Card.
 * */
export async function DeleteCardFetch(cardId) {
    return await SafeFetch(
        `cards/${cardId}`,
        {
            method: "DELETE",
            headers: {'Authorization': getUserToken()}
        }
    );
}
