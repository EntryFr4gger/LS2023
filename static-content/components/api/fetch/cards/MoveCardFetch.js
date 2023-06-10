import {getUserToken} from "../../../utils/get-token.js";

/**
 * Executes a fetch request to API.
 * Moves a card to a list.
 *
 * @param {Int} cardId card unique identifier.
 * @param {Int} lid list unique identifier.
 *
 * @return {Promise} a card id.
 * */
export async function MoveCardFetch(cardId, lid = null) {
    return await fetch(`cards/${cardId}`, {
        method: "PUT",
        headers: {Authorization: getUserToken()},
        body: JSON.stringify(
            {lid}
        )
    });
}