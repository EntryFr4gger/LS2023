import {SearchBoardsFetch} from "../../components/api/fetch/boards/SearchBoardsFetch.js";
import {SessionGetRemove} from "../../components/utils/session-get-remove.js";
import {ListOfBoards} from "../../components/ui/pagination/boards/ListOfBoards.js";
import BoardsPage from "../../pages/boards/BoardsPage.js";

/**
 * SearchBoardsHandler is an asynchronous function that handles searching for boards based on a name.
 *
 * @param {Object} state - The state object containing the necessary data.
 *
 * @returns {Promise} A promise that resolves to the rendered page for displaying the search results.
 */
async function SearchBoardsHandler(state) {

    const name = SessionGetRemove("search-res")

    let boardSkip = 0;

    /**
     * loadBoards is an asynchronous function that loads the search results and renders the boards.
     *
     * @param {Int} boardsToLoad - The number of boards to load.
     *
     * @returns {Array} An array of promises for rendering the boards.
     */
    async function loadBoards(boardsToLoad) {
        const response = await SearchBoardsFetch(name, boardSkip, boardsToLoad)

        const {boards} = await response.json()

        if (response.ok) {
            boardSkip += boards.length;

            return boards.map(async board => {
                return ListOfBoards(board);
            })
        } else
            alert(boards.error)
    }

    return BoardsPage(state, loadBoards)
}

export default SearchBoardsHandler;

