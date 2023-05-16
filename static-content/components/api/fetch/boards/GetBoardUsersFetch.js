import {getUserToken} from "../../../utils/get-token.js";

export async function GetBoardUsersFetch(boardId) {
    return await fetch(`boards/${boardId}/users`, {
        headers: {Authorization: getUserToken()}
    });
}