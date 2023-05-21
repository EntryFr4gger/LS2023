import CardDetailsPage from "../../pages/cards/CardDetailsPage.js";
import {GetCardFetch} from "../../components/api/fetch/cards/GetCardFetch.js";

async function DetailsCardHandler(state) {
    const cardId = state.pathParams["card_id"];
    if (isNaN(cardId))
        throw ("Invalid param id");

    const response = await GetCardFetch(cardId)

    const json = await response.json()

    if (response.ok) {
        state.body = json

        return CardDetailsPage(state)
    } else
        alert(json.error)
}

export default DetailsCardHandler;