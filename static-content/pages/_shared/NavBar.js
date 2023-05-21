import {a, button, div, form, h5, input, li, nav, span, ul} from "../../components/dom/domTags.js";
import UserDropdownInfo from "../../handlers/user/organize/UserDropdownInfo.js"
import LoginRegister from "../../handlers/user/organize/LoginRegister.js";
import {getUser} from "../../components/utils/get-user.js";
import {hashChangeLoc} from "../../components/utils/hash-change-loc.js";

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
            form({class: "d-flex", role: "search", onSubmit: search},
                input({
                    class: "form-control me-2",
                    id: "search-res",
                    type: "search",
                    placeholder: "Search Board",
                    "aria-label": "Search"
                })
            ),
            button({
                    class: "navbar-toggler",
                    type: "button",
                    "data-bs-toggle": "offcanvas",
                    "data-bs-target": "#offcanvasNavbar",
                    "aria-controls": "offcanvasNavbar",
                    "aria-label": "Toggle navigation"
                },
                span({class: "navbar-toggler-icon"})),
            div(
                {
                    class: "offcanvas offcanvas-end",
                    tabindex: "-1",
                    id: "offcanvasNavbar",
                    "aria-labelledby": "offcanvasNavbarLabel"
                },
                div({class: "offcanvas-header"},
                    h5({class: "offcanvas-title", id: "offcanvasNavbarLabel"}, "InfoPage"),
                    button({type: "button", class: "btn-close", "data-bs-dismiss": "offcanvas", "aria-label": "Close"})
                ),
                div({class: "offcanvas-body"},
                    ul({class: "navbar-nav justify-content-end flex-grow-1 pe-3"},
                        li({class: "nav-item"},
                            a({class: "nav-link active", "aria-current": "page", href: "#"}, "Home")),
                        userId == null
                            ? LoginRegister()
                            : UserDropdownInfo()
                    )
                )
            )
        )
    )
}


export default NavBar;
