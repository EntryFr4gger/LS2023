import {getUserToken} from "../../../utils/get-token.js";

/**
 * Executes a fetch request to API.
 * Delete a list.
 *
 * @param {Int} listId list unique identifier.
 *
 * @return {Promise} deleted List.
 * */
export async function DeleteListFetch(listId) {
    return await fetch(
        `lists/${listId}`,
        {
            method: "DELETE",
            headers: {'Authorization': getUserToken()}
        }
    );
}