import {getUserToken} from "../../../utils/get-token.js";

export async function UpdateCardFetch(cardId, lid) {
    return await fetch(`cards/${cardId}`, {
        method: "PUT",
        headers: {Authorization: getUserToken()},
        body: JSON.stringify(
            {lid, cix: 1}
        )
    });
}