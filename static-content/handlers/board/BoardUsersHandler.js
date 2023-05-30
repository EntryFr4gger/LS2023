import BoardUsersPage from "../../pages/boards/BoardUsersPage.js";
import {GetBoardUsersFetch} from "../../components/api/fetch/boards/GetBoardUsersFetch.js";

async function BoardUsersHandler(state) {
    const boardId = state.pathParams["board_id"];
    if (isNaN(boardId))
        throw ("Invalid param id");

    const response = await GetBoardUsersFetch(boardId)

    const json = await response.json()
    if (response.ok) {
        state.body = json

        return BoardUsersPage(state)
    } else
        alert(json.error)
}

export default BoardUsersHandler;