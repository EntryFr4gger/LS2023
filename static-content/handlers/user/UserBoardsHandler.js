import BoardsPage from "../../pages/boards/BoardsPage.js";
import {GetUserBoardsFetch} from "../../components/api/fetch/users/GetUserBoardsFetch.js";
import {ListOfBoards} from "../../components/ui/pagination/boards/ListOfBoards.js";

async function UserBoardsHandler(state) {
    const userId = state.pathParams["user_id"];
    if (isNaN(userId))
        throw ("Invalid param id");

    let boardSkip = 0

    async function loadBoards(boardsToLoad) {
        const response = await GetUserBoardsFetch(userId, boardSkip, boardsToLoad)

        const {boards} = await response.json()

        if (response.ok) {
            boardSkip += boards.length;

            return boards.map(async board => {
                return await ListOfBoards(board)
            })
        } else
            alert(boards.error)
    }

    return BoardsPage(state, loadBoards)
}

export default UserBoardsHandler;