import UserDetailsPage from "../../pages/users/UserDetailsPage.js";
import {GetUserDetailsFetch} from "../../components/api/fetch/users/GetUserDetailsFetch.js";

/**
 * DetailsUserHandler is an asynchronous function that handles retrieving the details of a user.
 *
 * @param {Object} state - The state object containing the current state of the application.
 */
async function DetailsUserHandler(state) {
    const userId = state.pathParams["user_id"];
    if (isNaN(userId))
        throw ("Invalid param id");

    const user = await GetUserDetailsFetch(userId)

    if (user) {
        state.body = user

        return UserDetailsPage(state)
    }
}

export default DetailsUserHandler;
