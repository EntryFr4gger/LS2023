import buttonWithRef from "../components/ButtonWithRef.js";
import {br, div, li, ul} from "../components/dom/domTags.js";


function ListDetailsPage(state) {
    const items = ['id', 'name', 'description'];
    const car = state.body["cards"]["cards"];

    return div(
        ul(
            ...items.map( item => li((item + " = " + state.body[item]))),
            li("cards "),
            ul(
                ...car.map(currentList =>
                    li(
                        ("name = " + currentList['name']),
                        buttonWithRef("Card Details", `/#cards/${currentList['id']}`)
                    )
                )
            )
        ),
        br()
    )

}

export default ListDetailsPage;