import BoardsPage from "../../pages/boards/BoardsPage.js";
import {GetUserBoardsFetch} from "../../components/api/fetch/users/GetUserBoardsFetch.js";
import {ListOfBoards} from "../../components/ui/pagination/boards/ListOfBoards.js";

/**
 * UserBoardsHandler is an asynchronous function that handles retrieving the boards associated with a user.
 *
 * @param {Object} state - The state object containing the current state of the application.
 */
async function UserBoardsHandler(state) {
    const userId = state.pathParams["user_id"];
    if (isNaN(userId))
        throw ("Invalid param id");

    let boardSkip = 0

    /**
     * LoadBoards is an asynchronous function that loads the boards associated with the user.
     *
     * @param {Number} boardsToLoad - The number of boards to load.
     *
     * @returns {Promise<Array>} An array of board components.
     */
    async function loadBoards(boardsToLoad) {
        const {boards} = await GetUserBoardsFetch(userId, boardSkip, boardsToLoad)

        if (boards) {
            boardSkip += boards.length;

            return boards.map(async board => {
                return ListOfBoards(board);
            })
        }
    }

    return BoardsPage(state, loadBoards)
}

export default UserBoardsHandler;