import {getUserToken} from "../../../utils/get-token.js";

/**
 * Executes a fetch request to API.
 * Moves a card to a list.
 *
 * @param {Int} listId list unique identifier.
 * @param {Int} cardId card unique identifier.
 * @param {Int} cix desired index.
 *
 * @return {Promise} a card id.
 * */
export async function RepositionCardFetch(listId, cardId, cix) {

    return await fetch(`lists/${listId}/cards`, {
        method: "PUT",
        headers: {Authorization: getUserToken()},
        body: JSON.stringify(
            {cardId, cix}
        )
    });
}