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

    const response = await GetUserDetailsFetch(userId)

    const json = await response.json()

    if (response.ok) {
        state.body = json

        return UserDetailsPage(state)
    } else
        alert(json.error)
}

export default DetailsUserHandler;
