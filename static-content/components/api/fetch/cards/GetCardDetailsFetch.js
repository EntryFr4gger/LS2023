import {getUserToken} from "../../../utils/get-token.js";

/**
 * Executes a fetch request to API.
 * Get the detailed information of a card.
 *
 * @param {Int} cardId card unique identifier.
 *
 * @return {Promise} a Card.
 * */
export async function GetCardDetailsFetch(cardId) {
    return await fetch(`cards/${cardId}`, {
        headers: {Authorization: getUserToken()}
    });
}