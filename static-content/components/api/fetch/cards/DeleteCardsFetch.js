import {getUserToken} from "../../../utils/get-token.js";

export async function DeleteCardFetch(cardId) {
    return await fetch(
        `cards/${cardId}`,
        {
            method: "DELETE",
            headers: {'Authorization': getUserToken()}
        }
    );
}
