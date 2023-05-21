import {getUserToken} from "../../../utils/get-token.js";

export async function GetUserBoardsFetch(userId, skip, limit) {
    const params = new URLSearchParams()
    params.set("skip", skip)
    params.set("limit", limit)
    return await fetch(`users/${userId}/boards?${params}`, {
        headers: {Authorization: getUserToken()}
    });
}