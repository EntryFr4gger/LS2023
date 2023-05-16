import BoardsPage from "../../../pages/boards/BoardsPage.js";
import {GetUserBoardsFetch} from "../../api/fetch/users/GetUserBoardsFetch.js";


async function UserBoardsHandler(state) {
    const userId = state.pathParams["user_id"];
    if (isNaN(userId))
        throw ("Invalid param id");

    const response = await GetUserBoardsFetch(userId)

    state.body = await response.json()

    return BoardsPage(state)
}

export default UserBoardsHandler;