import {getUserToken} from "../../../utils/get-token.js";

export async function GetBoardListsFetch(boardId, skip, limit) {
    const params = new URLSearchParams()
    params.set("skip", skip)
    params.set("limit", limit)
    return await fetch(`boards/${boardId}/lists?${params}`, {
        headers: {Authorization: getUserToken()}
    });
}