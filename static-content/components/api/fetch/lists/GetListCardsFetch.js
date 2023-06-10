import {getUserToken} from "../../../utils/get-token.js";

/**
 * Executes a fetch request to API.
 * Get the set of cards in a list.
 *
 * @param {Int} listId list unique identifier.
 *
 * @return {Promise} list of Cards in List.
 * */
export async function GetListCardsFetch(listId) {
    return await fetch(`lists/${listId}/cards`, {
        headers: {Authorization: getUserToken()}
    });
}