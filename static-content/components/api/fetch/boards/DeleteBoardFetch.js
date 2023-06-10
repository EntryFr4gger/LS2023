import {getUserToken} from "../../../utils/get-token.js";

/**
 * Executes a fetch request to API.
 * Delete a board.
 *
 * @param {Int} boardId board unique identifier.
 *
 * @return {Promise} deleted Board.
 * */
export async function DeleteBoardFetch(boardId) {
    return await fetch(
        `boards/${boardId}`,
        {
            method: "DELETE",
            headers: {'Authorization': getUserToken()}
        }
    );
}