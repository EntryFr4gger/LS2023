import BoardDetailsPage from "../../../pages/boards/BoardDetailsPage.js";
import {GetBoardFetch} from "../../api/fetch/boards/GetBoardFetch.js";
import {GetBoardListsFetch} from "../../api/fetch/boards/GetBoardListsFetch.js";

async function DetailsBoardHandler(state) {
    const id = state.pathParams["board_id"];
    if (isNaN(id))
        throw ("Invalid param id");

    const boardRes = await GetBoardFetch(id)
    const listRes = await GetBoardListsFetch(id)

    state.body = await boardRes.json()
    state.body["lists"] = await listRes.json()

    return BoardDetailsPage(state)
}

export default DetailsBoardHandler;
