import {SearchBoardsFetch} from "../../components/api/fetch/boards/SearchBoardsFetch.js";
import {SessionGetRemove} from "../../components/utils/session-get-remove.js";
import {ListOfBoards} from "../../components/ui/pagination/boards/ListOfBoards.js";
import BoardsPage from "../../pages/boards/BoardsPage.js";

async function SearchBoardsHandler(state) {

    const name = SessionGetRemove("search-res")

    let boardSkip = 0;

    async function loadBoards(boardsToLoad) {
        const response = await SearchBoardsFetch(name, boardSkip, boardsToLoad)

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

export default SearchBoardsHandler;

