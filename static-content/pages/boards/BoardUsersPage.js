import {div, h1, li, ul} from "../../components/dom/domTags.js";
import {buttonWithHref} from "../../components/ui/button/with-href.js";


async function BoardUsersPage(state) {
    const users = state.body['users']

    return div(
        h1("Users In Board: "),
        ul(
            ...users.map(user => li(`User : ${user[`name`]}`))
        ),
        buttonWithHref("Board Details", `#boards/${state.pathParams["board_id"]}`)
    )
}


export default BoardUsersPage;