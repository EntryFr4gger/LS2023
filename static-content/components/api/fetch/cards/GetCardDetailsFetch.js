import {getUserToken} from "../../../utils/storage/get-token.js";
import SafeFetch from "../../../utils/safe-fetch.js";

/**
 * Executes a fetch request to API.
 * Get the detailed information of a card.
 *
 * @param {Number} cardId card unique identifier.
 *
 * @return {Promise} a Card.
 * */
export async function GetCardDetailsFetch(cardId) {
    return await SafeFetch(`cards/${cardId}`, {
        headers: {Authorization: getUserToken()}
    });
}