import BoardUsersPage from "../../pages/boards/BoardUsersPage.js";
import {GetBoardUsersFetch} from "../../components/api/fetch/boards/GetBoardUsersFetch.js";

async function BoardUsersHandler(state) {
    const boardId = state.pathParams["board_id"];
    if (isNaN(boardId))
        throw ("Invalid param id");

    const response = await GetBoardUsersFetch(boardId)

    state.body = await response.json()

    return BoardUsersPage(state)
}

export default BoardUsersHandler;