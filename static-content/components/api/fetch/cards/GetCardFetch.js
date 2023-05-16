import {getUserToken} from "../../../utils/get-token.js";

export async function GetCardFetch(cardId) {
    return await fetch(`cards/${cardId}`, {
        headers: {Authorization: getUserToken()}
    });
}