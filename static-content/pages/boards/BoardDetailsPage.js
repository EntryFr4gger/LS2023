import buttonWithRef from "../components/ButtonWithRef.js";
import {div, h1, li, p, ul} from "../components/dom/domTags.js";


function BoardDetailsPage(state) {
    const items = ['id', 'name', 'description'];
    const lis = state.body["lists"]["lists"];

    return div(
        h1("Boards: "),
        ul(
            ...items.map(item => li(item + " = " + state.body[item])),
            p("Lists:"),
            ul(
                ...lis.map(currentList =>
                    li(
                        ("name = " + currentList['name']),
                        buttonWithRef("List Details", `/#lists/${currentList['id']}`)
                    )
                )
            )
        ),
        buttonWithRef("Users in Board", `/#boards/${state.body['id']}/users`)
    )
}

export default BoardDetailsPage;