import {getUserToken} from "../../../utils/get-token.js";

/**
 * Executes a fetch request to API.
 * Search for the name of the board in the database.
 *
 * @param {String} name name for the board.
 * @param {Int} skip skip tables.
 * @param {Int} limit limits the return values.
 *
 * @return {Promise} list of Boards.
 * */
export async function SearchBoardsFetch(name, skip, limit) {
    const params = new URLSearchParams()
    params.set("name", name)
    params.set("skip", skip)
    params.set("limit", limit)
    return await fetch(`boards/?${params}`, {
        headers: {Authorization: getUserToken()}
    });
}