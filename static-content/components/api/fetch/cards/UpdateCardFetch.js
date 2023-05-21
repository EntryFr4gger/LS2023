import {getUserToken} from "../../../utils/get-token.js";

export async function UpdateCardFetch(cardId, lid, cix = 1) {

    return await fetch(`cards/${cardId}`, {
        method: "PUT",
        headers: {Authorization: getUserToken()},
        body: JSON.stringify(
            {lid, cix}
        )
    });
}