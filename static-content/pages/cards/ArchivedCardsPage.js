import {div, h1, li, ul} from "../../components/dom/domTags.js";
import {lightOutlineButton} from "../../components/ui/button/color-buttons.js";

function ArchivedCardsPage(state) {
    const car = state.body["cards"];

    return div(
        h1("Archived Cards"),
        ul(
            ...car.map(currentCard =>
                li({class: "dropdown-item"}, lightOutlineButton(currentCard['name']))
            )
        )
    )
}

export default ArchivedCardsPage;