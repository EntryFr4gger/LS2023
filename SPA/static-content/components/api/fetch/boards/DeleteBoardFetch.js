import {getUserToken} from "../../../utils/storage/get-token.js";
import SafeFetch from "../../../utils/safe-fetch.js";

/**
 * Executes a fetch request to API.
 * Delete a board.
 *
 * @param {Number} boardId board unique identifier.
 *
 * @return {Promise} deleted Board.
 * */
export async function DeleteBoardFetch(boardId) {
    return await SafeFetch(
        `boards/${boardId}`,
        {
            method: "DELETE",
            headers: {'Authorization': getUserToken()}
        }
    );
}