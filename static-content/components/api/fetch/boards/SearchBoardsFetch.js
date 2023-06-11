import {getUserToken} from "../../../utils/storage/get-token.js";
import {SetNotNull} from "../../../utils/set-not-null.js";

/**
 * Executes a fetch request to API.
 * Search for the name of the board in the database.
 *
 * @param {String} name name for the board.
 * @param {Number} skip skip tables.
 * @param {Number} limit limits the return values.
 *
 * @return {Promise} list of Boards.
 * */
export async function SearchBoardsFetch(name, skip, limit) {
    const params = new URLSearchParams()
    SetNotNull("name", name, params)
    SetNotNull("skip", skip, params)
    SetNotNull("limit", limit, params)
    return await fetch(`boards/?${params}`, {
        headers: {Authorization: getUserToken()}
    });
}