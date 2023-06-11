import {a, button, div, h2, h5, img, li, p, ul} from "../../components/dom/domTags.js";
import {getUser, userIdStorage} from "../../components/utils/storage/get-user.js";

/**
 * UserDetailsPage is a function that generates the user details page component.
 *
 * @param {Object} state - The state object containing user details.
 *
 * @returns {Promise<HTMLElement>} The user details a page component.
 */
function UserDetailsPage(state) {

    /**
     * logout is a function that handles user logout.
     *
     * @param {Event} event - The click event.
     */
    function logout(event) {
        event.preventDefault()
        sessionStorage.removeItem(userIdStorage);
        window.dispatchEvent(new HashChangeEvent("hashchange"));
    }

    return div(
        {class: "d-flex justify-content-center"},
        div({class: "card", style: {width: "30rem"}},
            img({class: "card-img-top", src: "public/0.jpg", alt: "Card image cap"}),
            div({class: "card-body"},
                h5({class: "card-title"}, "User Information"),
                h2({class: "card-subtitle"}, state.body["name"]),
                p({class: "card-text", style: {"padding-left": "2px"}}, `${state.body["email"]}`)
            ),
            ul({class: "list-group list-group-flush"},
                li({class: "list-group-item"}, "Password"),
                li({class: "list-group-item"}, "Change Password"),
                li({class: "list-group-item"}, "Change Email"),
            ),
            div({class: "card-body"},
                button({class: "dropdown-item", onClick: logout}, "Logout"),
                a({class: "dropdown-item", href: `#users/${getUser()}/boards`}, "My Boards"),
            )
        )
    )
}

export default UserDetailsPage;