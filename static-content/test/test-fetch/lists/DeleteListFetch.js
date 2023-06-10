import {getUserToken} from "../../../utils/get-token.js";

export async function DeleteListFetch(listId) {
    return await fetch(
        `lists/${listId}`,
        {
            method: "DELETE",
            headers: {'Authorization': getUserToken()}
        }
    );
}