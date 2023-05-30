import BoardDetailsPage from "../../pages/boards/BoardDetailsPage.js";
import {GetBoardFetch} from "../../components/api/fetch/boards/GetBoardFetch.js";
import {GetBoardListsFetch} from "../../components/api/fetch/boards/GetBoardListsFetch.js";
import {ListOfLists} from "../../components/ui/pagination/lists/ListOfLists.js";
import {GetListCardsFetch} from "../../components/api/fetch/lists/GetListCardsFetch.js";

async function DetailsBoardHandler(state) {
    const id = state.pathParams["board_id"];
    if (isNaN(id))
        throw ("Invalid param id");

    let listSkip = 0;

    async function loadBoardDetails(listsToLoad) {
        const response = await GetBoardListsFetch(id, listSkip, listsToLoad)

        const {lists} = await response.json()

        if (response.ok) {
            listSkip += lists.length;

            return lists.map(async list => {
                const resCard = await GetListCardsFetch(list.id)
                const cards = await resCard.json()
                return await ListOfLists(state, list, cards.cards)
            })
        } else
            alert(lists.error)
    }

    const response = await GetBoardFetch(id)

    const json = await response.json()

    if (response.ok) {
        state.body = json
        return BoardDetailsPage(state, loadBoardDetails)
    } else
        alert(json.error)
}

export default DetailsBoardHandler;
