import {SearchBoardsFetch} from "../../api/fetch/boards/SearchBoardsFetch.js";
import BoardsPage from "../../../pages/boards/BoardsPage.js";
import {SessionGetRemove} from "../../utils/session-get-remove.js";

async function SearchBoardsHandler(state) {

    const name = SessionGetRemove("search-res")

    const reponse = await SearchBoardsFetch(name)

    state.body = await reponse.json()

    return BoardsPage(state)
}

export default SearchBoardsHandler;

