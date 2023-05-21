import BoardsPage from "../../../pages/boards/BoardsPage.js";
import {GetUserBoardsFetch} from "../../api/fetch/users/GetUserBoardsFetch.js";
import {ListOfBoards} from "../../ui/pagination/boards/ListOfBoards.js";


async function UserBoardsHandler(state) {
    const userId = state.pathParams["user_id"];
    if (isNaN(userId))
        throw ("Invalid param id");

    let boardSkip = 0

    async function loadNewBoards(boardsToLoad) {
        const response = await GetUserBoardsFetch(userId, boardSkip, boardsToLoad)

        const {boards} = await response.json()

        boardSkip += boards.length;

        return boards.map(async board => {
            return await ListOfBoards(board)
        })
    }

    return BoardsPage(state, {loadBoards: loadNewBoards})
}

export default UserBoardsHandler;