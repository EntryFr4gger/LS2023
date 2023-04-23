import {div, h1, li, ul} from "../components/dom/domTags.js";


function CardDetailsPage(state) {
    const items = ['id', 'name', 'description'];

    return div(
        h1("Cards Details:"),
        ul(
            ...items.map(item => li(item + " = " + state.body[item])),
        )
    )
}

export default CardDetailsPage;