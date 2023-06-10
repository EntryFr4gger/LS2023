import {getUserToken} from "../../../utils/storage/get-token.js";
import SafeFetch from "../../../utils/safe-fetch.js";
import {SetNotNull} from "../../../utils/set-not-null.js";

/**
 * Executes a fetch request to API.
 * Get the lists on a board.
 *
 * @param {Number} boardId board unique identifier.
 * @param {number} skip skip database tables.
 * @param {number} limit limit database tables.
 *
 * @return {Promise} list of Lists in a board.
 * */
export async function GetBoardListsFetch(boardId, skip = undefined, limit = undefined) {
    const params = new URLSearchParams()
    SetNotNull("skip", skip, params)
    SetNotNull("limit", limit, params)
    return await SafeFetch(`boards/${boardId}/lists?${params}`, {
        headers: {Authorization: getUserToken()}
    });
}