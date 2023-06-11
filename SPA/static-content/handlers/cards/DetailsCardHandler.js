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

    const card = await GetCardDetailsFetch(cardId)

    if (card) {
        state.body = card

        return await CardDetailsPage(state)
    }
}

export default DetailsCardHandler;