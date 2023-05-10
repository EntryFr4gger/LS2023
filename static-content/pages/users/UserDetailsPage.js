import {br, div, li, ul} from "../../components/dom/domTags.js";
import {buttonWithHref} from "../../components/ui/button/with-href.js";
import {getUser} from "../../components/utils/get-user.js";

function UserDetailsPage(state) {
    const items = ['id', 'name', 'email'];

    return div(
        ul(
            ...items.map(item => li((`${item} : ${state.body[item]}`))),
            br(),
            buttonWithHref("Home"),
            buttonWithHref("User Boards", `#users/${getUser()}/boards`)
        )
    )
}

export default UserDetailsPage;