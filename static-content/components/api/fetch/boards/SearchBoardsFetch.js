import {getUserToken} from "../../../utils/get-token.js";

export async function SearchBoardsFetch(name) {
    return await fetch(`boards/?name=${name}`, {
        headers: {Authorization: getUserToken()}
    });
}