import BoardDetailsPage from "../../pages/boards/BoardDetailsPage.js";
import {GetBoardDetailsFetch} from "../../components/api/fetch/boards/GetBoardDetailsFetch.js";
import {GetBoardListsFetch} from "../../components/api/fetch/boards/GetBoardListsFetch.js";
import {ListOfLists} from "../../components/ui/pagination/lists/ListOfLists.js";
import {GetListCardsFetch} from "../../components/api/fetch/lists/GetListCardsFetch.js";

/**
 * DetailsBoardHandler is an asynchronous function that handles retrieving and displaying the details of a board.
 *
 * @param {Object} state - The state object containing the necessary data.
 *
 * @returns {Promise} A promise that resolves to the rendered page for displaying board details.
 * @throws {string} If the board_id parameter is invalid.
 */
async function DetailsBoardHandler(state) {
    const id = state.pathParams["board_id"];
    if (isNaN(id))
        throw ("Invalid param id");

    let listSkip = 0;

    /**
     * loadBoardDetails is an asynchronous function that loads the details of the board, including its lists and cards.
     *
     * @param {number} listsToLoad - The number of lists to load.
     *
     * @returns {Array} An array of promises for rendering the lists and cards.
     */
    async function loadBoardDetails(listsToLoad) {
        const response = await GetBoardListsFetch(id, listSkip, listsToLoad)

        const {lists} = await response.json()

        if (response.ok) {
            listSkip += lists.length;

            return lists.map(async list => {
                const resCard = await GetListCardsFetch(list.id)
                const cards = await resCard.json()
                return ListOfLists(state, list, cards.cards);
            })
        } else
            alert(lists.error)
    }

    const response = await GetBoardDetailsFetch(id)

    const json = await response.json()

    if (response.ok) {
        state.body = json
        return BoardDetailsPage(state, loadBoardDetails)
    } else
        alert(json.error)
}

export default DetailsBoardHandler;
