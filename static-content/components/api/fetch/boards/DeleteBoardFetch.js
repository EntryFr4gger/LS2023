import {getUserToken} from "../../../utils/get-token.js";

export async function DeleteBoardFetch(boardId) {
    return await fetch(
        `boards/${boardId}`,
        {
            method: "DELETE",
            headers: {'Authorization': getUserToken()}
        }
    );
}