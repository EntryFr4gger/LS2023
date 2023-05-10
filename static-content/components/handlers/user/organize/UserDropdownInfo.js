import {a, button, div, li, ul} from "../../../dom/domTags.js";
import {getUser, userIdLS} from "../../../utils/get-user.js";


async function UserDropdownInfo() {

    function logout(event) {
        event.preventDefault()
        localStorage.removeItem(userIdLS);
        window.dispatchEvent(new HashChangeEvent("hashchange"));
    }

    const userId = getUser()

    return div(
        li({class: "nav-item dropdown"},
            a(
                {
                    class: "nav-link dropdown-toggle",
                    href: "#",
                    role: "button",
                    "data-bs-toggle": "dropdown",
                    "aria-expanded": "false"
                },
                "User Info"),
            ul({class: "dropdown-menu"},
                li(a({class: "dropdown-item", href: `#users/${userId}`}, "User Details")),
                li(a({class: "dropdown-item", href: "#"}, "Another action")),
                li({class: "dropdown-divider"}),
                li(button({class: "dropdown-item", onClick: logout}, "Logout"))
            )
        )
    )
}

export default UserDropdownInfo;