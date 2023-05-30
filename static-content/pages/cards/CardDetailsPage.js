import {br, div, h1, h2, h5} from "../../components/dom/domTags.js";
import {buttonWithHref} from "../../components/ui/button/with-href.js";
import DeleteCardHandler from "../../handlers/cards/DeleteCardHandler.js";
import {UpdateCard} from "../../components/ui/pagination/cards/UpdateCard.js";
import {ArqCard} from "../../components/ui/pagination/cards/ArqCard.js";


function CardDetailsPage(state) {
    return div(
        h1("Card Details"),
        br(),
        h2(`${state.body["name"]}`),
        h5(`${state.body["description"]}`),
        buttonWithHref("Back To Board", `#boards/${state.body["boardId"]}`),
        DeleteCardHandler(state),
        UpdateCard(state),
        //ChangeCixCard(state),
        ArqCard(state)
    )
}

export default CardDetailsPage;