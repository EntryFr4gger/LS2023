import {getUserToken} from "../../../utils/get-token.js";

/**
 * Executes a fetch request to API.
 * Get the lists on a board.
 *
 * @param {Int} boardId board unique identifier.
 * @param {Int} skip skip database tables.
 * @param {Int} limit limit database tables.
 *
 * @return {Promise} list of Lists in a board.
 * */
export async function GetBoardListsFetch(boardId, skip, limit) {
    const params = new URLSearchParams()
    params.set("skip", skip)
    params.set("limit", limit)
    return await fetch(`boards/${boardId}/lists?${params}`, {
        headers: {Authorization: getUserToken()}
    });
}