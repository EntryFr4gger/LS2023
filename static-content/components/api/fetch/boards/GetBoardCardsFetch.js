import {getUserToken} from "../../../utils/get-token.js";

/**
 * Executes a fetch request to API.
 * Get the cards on a board.
 *
 * @param {Int} boardId board unique identifier.
 *
 * @return {Promise} list of cards in a board.
 * */
export async function GetBoardCardsFetch(boardId) {
    return await fetch(`boards/${boardId}/cards?archived`, {
        headers: {Authorization: getUserToken()}
    });
}