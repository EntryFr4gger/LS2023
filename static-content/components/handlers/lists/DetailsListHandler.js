import {GetListFetch} from "../../api/fetch/lists/GetListFetch.js";
import {GetListCardsFetch} from "../../api/fetch/lists/GetListCardsFetch.js";
import ListDetailsPage from "../../../pages/lists/ListDetailsPage.js";
import CardDetailsPage from "../../../pages/cards/CardDetailsPage.js";


async function DetailsListHandler(state) {
    const listId = state.pathParams["list_id"];
    if (isNaN(listId))
        throw ("Invalid param id");

    const responseList = await GetListFetch(listId)
    const responseCard = await GetListCardsFetch(listId)

    const jsonList = await responseList.json()
    const jsonCard = await responseCard.json()

    if(responseList.ok) {
        if(responseCard.ok){
            state.body = jsonList
            state.body["cards"] = jsonCard

            return ListDetailsPage(state)
        }
        else
            alert(jsonCard.error)
    }
    else
        alert(jsonList.error)
}

export default DetailsListHandler;