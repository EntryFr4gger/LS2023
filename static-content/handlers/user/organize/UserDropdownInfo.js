import {a, div, hr, img, li, strong, ul} from "../../../components/dom/domTags.js";
import {getUser, userIdStorage} from "../../../components/utils/get-user.js";
import {userTokenStorage} from "../../../components/utils/get-token.js";
import {hashChangeLoc} from "../../../components/utils/hash-change-loc.js";


async function UserDropdownInfo() {

    function logout(event) {
        event.preventDefault()

        localStorage.removeItem(userIdStorage);
        localStorage.removeItem(userTokenStorage);

        hashChangeLoc(`#`)
    }

    const userId = getUser()

    return div({class: "dropdown dropstart dropup-center"},
        a({
                href: "#",
                class: "d-flex align-items-center text-black text-decoration-none dropdown-toggle",
                id: "dropdownUser1",
                "data-bs-toggle": "dropdown",
                "aria-expanded": "false"
            },
            img({src: "public/0.jpg", alt: "", width: "28", height: "28", class: "rounded-circle me-2"}),
            strong("me")
        ),
        ul({class: "dropdown-menu dropdown-menu-white text-small shadow", "aria-labelledby": "dropdownUser1"},
            li(a({class: "dropdown-item", href: `#users/${userId}`}, "Profile")),
            li(a({class: "dropdown-item", href: `#users/${userId}/boards`}, "Boards")),
            li(a({class: "dropdown-item", href: "#"}, "Nothing")),
            li(hr({class: "dropdown-divider"})),
            li(a({class: "dropdown-item", href: "#", onClick: logout}, "Sign out"))
        )
    )
    /*div(
        li({class: "nav-item dropdown"},
            a(
                {class: "nav-link dropdown-toggle", href: "#", role: "button", "data-bs-toggle": "dropdown", "aria-expanded": "false"},
                "User Info"
            ),
            ul({class: "dropdown-menu"},
                li(a({class: "dropdown-item", href: `#users/${userId}`}, "User Details")),
                li(a({class: "dropdown-item", href: `#users/${getUser()}/boards`}, "User Boards")),
                li({class: "dropdown-divider"}),
                li(button({class: "dropdown-item", onClick: logout}, "Logout"))
            )
        )
    )*/
}

export default UserDropdownInfo;