import {getUserToken} from "../../../utils/get-token.js";

/**
 * Executes a fetch request to API.
 * Get the detailed information on the board.
 *
 * @param {Int} boardId board unique identifier.
 *
 * @return {Promise} a Board.
 * */
export async function GetBoardDetailsFetch(boardId) {
    return await fetch(`boards/${boardId}`, {
        headers: {Authorization: getUserToken()}
    });
}