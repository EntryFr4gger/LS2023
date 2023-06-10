import {br, div, h1, h2, h5} from "../../components/dom/domTags.js";
import {buttonWithHref} from "../../components/ui/button/with-href.js";

/**
 * CardDetailsPage is a function that generates the card details page component.
 *
 * @param {Object} state - The state object.
 *
 * @returns {Promise<HTMLElement>} The card details page component.
 *
 * Not used
 */
function CardDetailsPage(state) {
    return div(
        h1("Card Details"),
        br(),
        h2(`${state.body["name"]}`),
        h5(`${state.body["description"]}`),
        buttonWithHref("Back To Board", `#boards/${state.body["boardId"]}`),
        /*DeleteCardHandler(state),
        UpdateCard(state),
        ChangeCixCard(state),
        ArchiveCard(state)*/
    )
}

export default CardDetailsPage;