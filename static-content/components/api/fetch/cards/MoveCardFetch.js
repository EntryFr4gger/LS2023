import {getUserToken} from "../../../utils/get-token.js";

export async function MoveCardFetch(cardId, lid = null) {
    return await fetch(`cards/${cardId}`, {
        method: "PUT",
        headers: {Authorization: getUserToken()},
        body: JSON.stringify(
            {lid}
        )
    });
}