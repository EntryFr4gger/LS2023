import UserDetailsPage from "../../../pages/users/UserDetailsPage.js";
import {GetUserFetch} from "../../api/fetch/users/GetUserFetch.js";


async function DetailsUserHandler(state) {
    const userId = state.pathParams["user_id"];
    if (isNaN(userId))
        throw ("Invalid param id");

    const response = await GetUserFetch(userId)

    state.body = await response.json()

    return UserDetailsPage(state)
}

export default DetailsUserHandler;
