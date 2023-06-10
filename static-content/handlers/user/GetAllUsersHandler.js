import {GetAllUsers} from "../../components/api/fetch/users/GetAllUsers.js";
import {OptionOfUsers} from "../../components/ui/pagination/users/OptionOfUsers.js";
import {div} from "../../components/dom/domTags.js";

async function GetAllUsersHandler(state) {

    const boardId = state.pathParams["board_id"]

    const response = await GetAllUsers(boardId)

    const {users} = await response.json()

    if (response.ok) {
        return div(
            ...users.map(async user => {
                return await OptionOfUsers(user)
            })
        )
    } else
        alert(users.error)
}

export default GetAllUsersHandler;

