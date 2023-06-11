import {a, div, img, li, ul} from "../../../dom/domTags.js";

/**
 * LoginRegister is a function that returns a dropdown menu component for login and registration options.
 *
 * @returns {HTMLElement} The dropdown menu component for login and registration.
 */
async function LoginRegister() {
    return div({class: "dropdown dropstart dropup-center"},
        a({
                href: "#",
                class: "d-flex align-items-center text-black text-decoration-none dropdown-toggle",
                id: "dropdownUser1",
                "data-bs-toggle": "dropdown",
                "aria-expanded": "false"
            },
            img({src: "public/0.jpg", alt: "", width: "28", height: "28", class: "rounded-circle me-2"}),
        ),
        ul({class: "dropdown-menu dropdown-menu-white text-small shadow", "aria-labelledby": "dropdownUser1"},
            li(a({class: "dropdown-item", href: "#login"}, "Login")),
            li(a({class: "dropdown-item", href: "#register"}, "Register")),
        )
    )
}

export default LoginRegister;