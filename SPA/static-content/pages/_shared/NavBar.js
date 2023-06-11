import {a, div, nav} from "../../components/dom/domTags.js";
import UserDropdownHandler from "../../handlers/user/UserDropdownHandler.js"
import LoginRegister from "../../components/ui/pagination/users/LoginRegister.js";
import {getUser} from "../../components/utils/storage/get-user.js";
import SearchHandler from "../../handlers/_default/SearchHandler.js";

/**
 * NavBar is an asynchronous function that generates the navigation bar component.
 *
 * @returns {Promise<HTMLElement>} The navigation bar component.
 */
function NavBar() {
    const userId = getUser()

    return nav(
        {class: "navbar bg-body-tertiary fixed-top"},
        div(
            {class: "container-fluid"},
            a({class: "navbar-brand", href: "#"}, "Home"),
            userId != null
                ? SearchHandler()
                : div(),

            userId == null
                ? LoginRegister()
                : UserDropdownHandler()
        )
    )
}


export default NavBar;
