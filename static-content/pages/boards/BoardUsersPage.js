import buttonWithRef from "../components/ButtonWithRef.js";
import {br, div, li, ul} from "../components/dom/domTags.js";


async function BoardUsersPage(state) {
    const items = ['id', 'name', 'email'];
    const users = state.body['users']

    return div(
        ul(
            ...users.map( user =>
                    items.map(currentItem =>
                        li(
                            (1),//currentItem + " = " + user[currentItem]
                            buttonWithRef("Card Details", `/#cards/1`)//${user['id']}
                        )
                    )
            )
        ),
        br()
    )
}


export default BoardUsersPage;