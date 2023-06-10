import {getUserToken} from "../../../utils/get-token.js";

/**
 * Executes a fetch request to API.
 * Delete a card.
 *
 * @param {Int} cardId card unique identifier.
 *
 * @return {Promise} deleted Card.
 * */
export async function DeleteCardFetch(cardId) {
    return await fetch(
        `cards/${cardId}`,
        {
            method: "DELETE",
            headers: {'Authorization': getUserToken()}
        }
    );
}
