import {GetBoardCardsFetch} from "../../components/api/fetch/boards/GetBoardCardsFetch.js";
import ArchivedCardsPage from "../../pages/cards/ArchivedCardsPage.js";

async function ArchivedCardHandler(state) {
    const id = state.pathParams["board_id"];
    if (isNaN(id))
        throw ("Invalid param id");

    const response = await GetBoardCardsFetch(id)

    const json = await response.json()

    if (response.ok) {
        state.body = json

        return ArchivedCardsPage(state)
    } else
        alert(json.error)
}

export default ArchivedCardHandler;