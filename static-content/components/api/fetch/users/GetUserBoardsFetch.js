import {getUserToken} from "../../../utils/storage/get-token.js";
import {SetNotNull} from "../../../utils/set-not-null.js";
import SafeFetch from "../../../utils/safe-fetch.js";

/**
 * Executes a fetch request to API.
 * Get the list with all user available boards.
 *
 * @param {Number} userId user unique identifier.
 * @param {Number} skip skip database tables.
 * @param {Number} limit limit database tables.
 *
 * @return {Promise} list with user boards.
 * */
export async function GetUserBoardsFetch(userId, skip, limit) {
    const params = new URLSearchParams()
    SetNotNull("skip", skip, params)
    SetNotNull("limit", limit, params)
    return await SafeFetch(`users/${userId}/boards?${params}`, {
        headers: {Authorization: getUserToken()}
    });
}