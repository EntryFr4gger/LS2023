import {getUserToken} from "../../../utils/get-token.js";

/**
 * Executes a fetch request to API.
 * Get the list with all user available boards.
 *
 * @param {Int} userId user unique identifier.
 * @param {Int} skip skip database tables.
 * @param {Int} limit limit database tables.
 *
 * @return {Promise} list with user boards.
 * */
export async function GetUserBoardsFetch(userId, skip, limit) {
    const params = new URLSearchParams()
    params.set("skip", skip)
    params.set("limit", limit)
    return await fetch(`users/${userId}/boards?${params}`, {
        headers: {Authorization: getUserToken()}
    });
}