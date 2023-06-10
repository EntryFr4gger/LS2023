import {getUserToken} from "../../../utils/get-token.js";

export async function RepositionCardFetch(listId, cardId, cix) {

    return await fetch(`lists/${listId}/cards`, {
        method: "PUT",
        headers: {Authorization: getUserToken()},
        body: JSON.stringify(
            {cardId, cix}
        )
    });
}