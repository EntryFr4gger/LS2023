import CardDetailsPage from "../../../pages/cards/CardDetailsPage.js";
import {GetCardFetch} from "../../api/fetch/cards/GetCardFetch.js";


async function DetailsCardHandler(state) {
    const cardId = state.pathParams["card_id"];
    if (isNaN(cardId))
        throw ("Invalid param id");

    const cardRes = await GetCardFetch(cardId)

    state.body = await cardRes.json()

    return CardDetailsPage(state)
}

export default DetailsCardHandler;