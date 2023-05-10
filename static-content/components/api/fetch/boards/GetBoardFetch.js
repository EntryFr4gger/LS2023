import {getUserToken} from "../../../utils/get-token.js";

export async function GetBoardFetch(boardId) {
    return await fetch(`boards/${boardId}`, {
        headers: {Authorization: getUserToken()}
    });
}