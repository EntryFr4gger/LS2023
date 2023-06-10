import {a, div, nav} from "../../components/dom/domTags.js";
import UserDropdownInfo from "../../handlers/user/organize/UserDropdownInfo.js"
import LoginRegister from "../../handlers/user/organize/LoginRegister.js";
import {getUser} from "../../components/utils/get-user.js";
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
                : UserDropdownInfo()
        )
    )
}


export default NavBar;
