import {getUserToken} from "../../../utils/get-token.js";

export async function GetUserBoardsFetch(userId) {
    return await fetch(`users/${userId}/boards`, {
        headers: {Authorization: getUserToken()}
    });
}