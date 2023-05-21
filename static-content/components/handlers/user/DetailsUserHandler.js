import UserDetailsPage from "../../../pages/users/UserDetailsPage.js";
import {GetUserFetch} from "../../api/fetch/users/GetUserFetch.js";

async function DetailsUserHandler(state) {
    const userId = state.pathParams["user_id"];
    if (isNaN(userId))
        throw ("Invalid param id");

    const response = await GetUserFetch(userId)

    const json = await response.json()

    if(response.ok) {
        state.body = json

        return UserDetailsPage(state)
    }
    else
        alert(json.error)
}

export default DetailsUserHandler;
