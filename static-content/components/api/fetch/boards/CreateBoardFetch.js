import {getUserToken} from "../../../utils/get-token.js";

/**
 * Executes a fetch request to API.
 * Creates a new board and adds the user to the created board.
 *
 * @param {String} name unique name for the board.
 * @param {String} description board description.
 *
 * @return {Promise} new board unique identifier.
 * */
export async function CreateBoardFetch(name, description) {
    return await fetch(`boards/`, {
        method: "POST",
        headers: {Authorization: getUserToken()},
        body: JSON.stringify({name, description})
    });
}