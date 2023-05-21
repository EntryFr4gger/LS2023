import {SearchBoardsFetch} from "../../api/fetch/boards/SearchBoardsFetch.js";
import {SessionGetRemove} from "../../utils/session-get-remove.js";
import {ListOfBoards} from "../../ui/pagination/boards/ListOfBoards.js";
import BoardsPage from "../../../pages/boards/BoardsPage.js";

async function SearchBoardsHandler(state) {

    const name = SessionGetRemove("search-res")

    let boardSkip = 0;

    async function loadNewBoards(boardsToLoad) {
        const response = await SearchBoardsFetch(name, boardSkip, boardsToLoad)

        const {boards} = await response.json()

        boardSkip += boards.length;

        return boards.map(async board => {
                return await ListOfBoards(board)
            })
    }

    return BoardsPage(state, {loadBoards: loadNewBoards})
}

export default SearchBoardsHandler;

