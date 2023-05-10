import {getUserToken} from "../../../utils/get-token.js";

export async function DeleteCardFetch(cardId) {
    return await fetch(
        `http://localhost:9000/cards/${cardId}`,
        {
            method: "DELETE",
            headers: {'Authorization': getUserToken()}
        }
    );
}
