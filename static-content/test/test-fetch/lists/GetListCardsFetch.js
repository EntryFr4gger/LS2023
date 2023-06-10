import {getUserToken} from "../../../utils/get-token.js";

export async function GetListCardsFetch(listId) {
    return await fetch(`lists/${listId}/cards`, {
        headers: {Authorization: getUserToken()}
    });
}