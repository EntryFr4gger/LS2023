import {a, div, form, input, nav} from "../../components/dom/domTags.js";
import UserDropdownInfo from "../../handlers/user/organize/UserDropdownInfo.js"
import LoginRegister from "../../handlers/user/organize/LoginRegister.js";
import {getUser} from "../../components/utils/get-user.js";
import {hashChangeLoc} from "../../components/utils/hash-change-loc.js";
import SearchHandler from "../../handlers/_default/SearchHandler.js";

async function NavBar() {
    const userId = getUser()

    function search(event) {
        event.preventDefault()
        const searchValue = document.getElementById("search-res").value
        if (searchValue.trim() !== "") {
            sessionStorage.setItem("search-res", document.getElementById("search-res").value)
            hashChangeLoc(`#boards/search`)
        }
    }

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
