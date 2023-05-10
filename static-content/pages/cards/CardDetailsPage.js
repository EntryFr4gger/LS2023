import {div, h1, li, ul} from "../../components/dom/domTags.js";
import {buttonWithHref} from "../../components/ui/button/with-href.js";
import DeleteCardHandler from "../../components/handlers/cards/DeleteCardHandler.js";


function CardDetailsPage(state) {
    const items = ['id', 'name', 'description'];

    return div(
        h1("Cards Details:"),
        ul(
            ...items.map(item => li(`${item} : ${state.body[item]}`)),
        ),
        buttonWithHref("List Details", `#lists/${state.body["listId"]}`),
        DeleteCardHandler(state)
    )
}

export default CardDetailsPage;