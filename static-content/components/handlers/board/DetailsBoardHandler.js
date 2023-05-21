import BoardDetailsPage from "../../../pages/boards/BoardDetailsPage.js";
import {GetBoardFetch} from "../../api/fetch/boards/GetBoardFetch.js";
import {GetBoardListsFetch} from "../../api/fetch/boards/GetBoardListsFetch.js";
import {SearchBoardsFetch} from "../../api/fetch/boards/SearchBoardsFetch.js";
import {ListOfBoards} from "../../ui/pagination/boards/ListOfBoards.js";
import {ListOfLists} from "../../ui/pagination/lists/ListOfLists.js";
import {GetListCardsFetch} from "../../api/fetch/lists/GetListCardsFetch.js";

async function DetailsBoardHandler(state) {
    const id = state.pathParams["board_id"];
    if (isNaN(id))
        throw ("Invalid param id");

    const boardRes = await GetBoardFetch(id)

    let listSkip = 0;

    async function loadBoardDetails(listsToLoad) {
        const resList = await GetBoardListsFetch(id, listSkip, listsToLoad)

        const {lists} = await resList.json()

        listSkip += lists.length;

        return lists.map(async list => {
            const resCard = await GetListCardsFetch(list.id)
            const cards = await resCard.json()
            return await ListOfLists(list, cards.cards)
        })
    }

    state.body = await boardRes.json()

    return BoardDetailsPage(state, {loadBoardDetails: loadBoardDetails})
}

export default DetailsBoardHandler;
