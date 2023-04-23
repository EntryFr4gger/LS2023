import buttonWithRef from "../components/ButtonWithRef.js";
import {div, h1, li, ul} from "../components/dom/domTags.js";


function ListDetailsPage(state) {
    const items = ['id', 'name'];
    const car = state.body["cards"]["cards"];

    return div(
        h1("List Details:"),
        ul(
            ...items.map(item => li((item + " = " + state.body[item]))),
            h1("Cards:"),
            ul(
                ...car.map(currentList =>
                    li(
                        ("name = " + currentList['name']),
                        buttonWithRef("Card Details", `/#cards/${currentList['id']}`)
                    )
                )
            )
        ),
    )

}

export default ListDetailsPage;