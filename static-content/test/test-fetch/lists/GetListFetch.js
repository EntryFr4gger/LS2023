import {getUserToken} from "../../../utils/get-token.js";

export async function GetListFetch(listId) {
    return await fetch(`lists/${listId}`, {
        headers: {Authorization: getUserToken()}
    });
}