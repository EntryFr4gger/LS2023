import {getUserToken} from "../../../utils/get-token.js";

export async function GetBoardCardsFetch(boardId) {
    return await fetch(`boards/${boardId}/cards?archived`, {
        headers: {Authorization: getUserToken()}
    });
}