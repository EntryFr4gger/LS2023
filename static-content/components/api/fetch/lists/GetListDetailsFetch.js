import {getUserToken} from "../../../utils/get-token.js";

/**
 * Executes a fetch request to API.
 * Get detailed information of a list.
 *
 * @param {Int} listId list unique identifier.
 *
 * @return {Promise} a List.
 * */
export async function GetListDetailsFetch(listId) {
    return await fetch(`lists/${listId}`, {
        headers: {Authorization: getUserToken()}
    });
}