import {a, div, hr, img, li, strong, ul} from "../../components/dom/domTags.js";
import {getUser, userIdStorage} from "../../components/utils/storage/get-user.js";
import {userTokenStorage} from "../../components/utils/storage/get-token.js";
import {hashChangeLoc} from "../../components/utils/hash-change-loc.js";

/**
 * UserDropdownHandler is an asynchronous function that returns a dropdown menu component for user-specific options.
 *
 * @returns {HTMLElement} The dropdown menu component for user-specific options.
 */
async function UserDropdownHandler() {

    /**
     * logout is a function that handles the user logout.
     *
     * @param {Event} event - The event object triggered by the logout action.
     */
    function logout(event) {
        event.preventDefault()

        sessionStorage.removeItem(userIdStorage);
        sessionStorage.removeItem(userTokenStorage);

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
}

export default UserDropdownHandler;