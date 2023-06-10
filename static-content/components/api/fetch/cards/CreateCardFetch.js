import {getUserToken} from "../../../utils/get-token.js";

/**
 * Executes a fetch request to API.
 * Creates a new card in a list.
 *
 * @param {String} name the task name.
 * @param {String} description the task description.
 * @param {Int} boardId board unique identifier.
 * @param {Int} listId list unique identifier.
 *
 * @return {Promise} new card unique identifier.
 * */
export async function CreateCardFetch(name, description, boardId, listId) {
    return await fetch(`cards/`, {
        method: "POST",
        headers: {Authorization: getUserToken()},
        body: JSON.stringify(
            {name, description, boardId, listId}
        )
    });
}