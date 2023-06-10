import {GetAllUsers} from "../../components/api/fetch/users/GetAllUsers.js";
import {OptionOfUsers} from "../../components/ui/pagination/users/OptionOfUsers.js";
import {div} from "../../components/dom/domTags.js";

/**
 * GetAllUsersHandler is an asynchronous function that handles retrieving all users associated with a board.
 *
 * @param {Object} state - The state object containing the current state of the application.
 */
async function GetAllUsersHandler(state) {

    const boardId = state.pathParams["board_id"]

    const response = await GetAllUsers(boardId)

    const {users} = await response.json()

    if (response.ok) {
        return div(
            ...users.map(async user => {
                return OptionOfUsers(user);
            })
        )
    } else
        alert(users.error)
}

export default GetAllUsersHandler;

