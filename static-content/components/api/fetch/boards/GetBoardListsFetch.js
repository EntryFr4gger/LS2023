import {getUserToken} from "../../../utils/get-token.js";

export async function GetBoardListsFetch(boardId) {
    return await fetch(`boards/${boardId}/lists`, {
        headers: {Authorization: getUserToken()}
    });
}