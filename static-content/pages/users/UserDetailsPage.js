import buttonWithRef from "../components/ButtonWithRef.js";
import {br, div, li, ul} from "../components/dom/domTags.js";


function UserDetailsPage(state) {
    const items = ['id', 'name', 'email'];

    return div(
        ul(
            ...items.map( item => li((item + " = " + state.body[item]))),
            buttonWithRef("Home", "/#"),
            br(),
            buttonWithRef("Board Details", "#users/1/boards")
        )
    )
}

export default UserDetailsPage;