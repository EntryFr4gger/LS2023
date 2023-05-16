import {getUserToken} from "../../../utils/get-token.js";

export async function DeleteBoardFetch(boardId) {
    return await fetch(
        `http://localhost:9000/boards/${boardId}`,
        {
            method: "DELETE",
            headers: {'Authorization': getUserToken()}
        }
    );
}