import {br, div, h1, h2, h5, li, ul} from "../../components/dom/domTags.js";
import {buttonWithHref} from "../../components/ui/button/with-href.js";
import DeleteListHandler from "../../handlers/lists/DeleteListHandler.js";
import CreateCardHandler from "../../handlers/cards/CreateCardHandler.js";
import {darkButton} from "../../components/ui/button/color-buttons.js";

/**
 * ListDetailsPage is a function that generates the list details page component.
 *
 * @param {Object} state - The state object.
 *
 * @returns {Promise<HTMLElement>} The list details a page component.
 */
function ListDetailsPage(state) {
    const car = state.body["cards"]["cards"];

    return div(
        h1("List Details"),
        br(),
        h2(`${state.body["name"]}`),
        ul(
            li(
                h5("Cards"),
                ul(
                    ...car.map(currentCard =>
                        li({class: "dropdown-item"}, buttonWithHref(currentCard['name'], `/#cards/${currentCard['id']}`))
                    )
                )
            )
        ),
        buttonWithHref("Board Details", `#boards/${state.body["boardId"]}`),
        DeleteListHandler(state),
        darkButton(CreateCardHandler(state))
    )
}

export default ListDetailsPage;