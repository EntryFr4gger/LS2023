import CardDetailsPage from "../../pages/cards/CardDetailsPage.js";
import {GetCardDetailsFetch} from "../../components/api/fetch/cards/GetCardDetailsFetch.js";

/**
 * DetailsCardHandler is a function that handles the retrieval and display of card details.
 *
 * @param {Object} state - The state object containing the necessary information.
 *
 * @returns {Promise} A promise that resolves to the rendered CardDetailsPage.
 */
async function DetailsCardHandler(state) {
    const cardId = state.pathParams["card_id"];
    if (isNaN(cardId))
        throw ("Invalid param id");

    const response = await GetCardDetailsFetch(cardId)

    const json = await response.json()

    if (response.ok) {
        state.body = json

        return CardDetailsPage(state)
    } else
        alert(json.error)
}

export default DetailsCardHandler;