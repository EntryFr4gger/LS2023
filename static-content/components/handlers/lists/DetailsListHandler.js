import {GetListFetch} from "../../api/fetch/lists/GetListFetch.js";
import {GetListCardsFetch} from "../../api/fetch/lists/GetListCardsFetch.js";
import ListDetailsPage from "../../../pages/lists/ListDetailsPage.js";


async function DetailsListHandler(state) {
    const listId = state.pathParams["list_id"];
    if (isNaN(listId))
        throw ("Invalid param id");

    const listRes = await GetListFetch(listId)

    const cardRes = await GetListCardsFetch(listId)

    state.body = await listRes.json()
    state.body["cards"] = await cardRes.json()

    return ListDetailsPage(state)
}

export default DetailsListHandler;