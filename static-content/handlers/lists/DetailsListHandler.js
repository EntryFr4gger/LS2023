import {GetListDetailsFetch} from "../../components/api/fetch/lists/GetListDetailsFetch.js";
import {GetListCardsFetch} from "../../components/api/fetch/lists/GetListCardsFetch.js";
import ListDetailsPage from "../../pages/lists/ListDetailsPage.js";

/**
 * DetailsListHandler is an asynchronous function that handles retrieving the details of a list and its cards.
 *
 * @param {Object} state - The state object containing the current state of the application.
 */
async function DetailsListHandler(state) {
    const listId = state.pathParams["list_id"];
    if (isNaN(listId))
        throw ("Invalid param id");

    const list = await GetListDetailsFetch(listId)
    const cards = await GetListCardsFetch(listId)

    if (list) {
        if (cards) {
            state.body = list
            state.body["cards"] = cards

            return ListDetailsPage(state)
        }
    }
}

export default DetailsListHandler;