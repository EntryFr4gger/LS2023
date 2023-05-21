import {a, br, button, div, h2, h5, img, li, p, ul} from "../../components/dom/domTags.js";
import {getUser, userIdLS} from "../../components/utils/get-user.js";

function UserDetailsPage(state) {

    function logout(event) {
        event.preventDefault()
        localStorage.removeItem(userIdLS);
        window.dispatchEvent(new HashChangeEvent("hashchange"));
    }

    return div(
        {class: "d-flex justify-content-center"},
        div({class: "card", style: {width: "30rem"}},
            img({class:"card-img-top", src:"public/0.jpg", alt:"Card image cap"}),
            div({class:"card-body"},
                h5({class:"card-title"},"User Information"),
                h2({class:"card-subtitle"},state.body["name"]),
                p({class:"card-text", style:{"padding-left":"2px"}}, `${state.body["email"]}`)
            ),
            ul({class:"list-group list-group-flush"},
                li({class:"list-group-item"},"Password"),
                li({class:"list-group-item"},"Change Password"),
                li({class:"list-group-item"},"Change Email"),
            ),
            div({class:"card-body"},
                button({class: "dropdown-item", onClick: logout}, "Logout"),
                a({class:"dropdown-item", href:`#users/${getUser()}/boards`},"My Boards"),
            )
        )
    )

    /*div(
        ul(
            ...items.map(item => li((`${item} : ${state.body[item]}`))),
            br(),
            buttonWithHref("Home"),
            buttonWithHref("User Boards", `#users/${getUser()}/boards`)
        )
    )*/
}

export default UserDetailsPage;