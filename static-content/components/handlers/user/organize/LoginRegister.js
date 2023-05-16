import {a, br, div, form, input, li, span} from "../../../dom/domTags.js";
import {userIdLS} from "../../../utils/get-user.js";


async function LoginRegister() {

    function login(event) {
        event.preventDefault()
        localStorage.setItem(userIdLS, document.getElementById("userId").value)
        window.dispatchEvent(new HashChangeEvent("hashchange"));
    }

    function token(event) {
        event.preventDefault()
        localStorage.setItem("userToken", `Bearer ${document.getElementById("userToken").value}`)
        window.dispatchEvent(new HashChangeEvent("hashchange"));
    }

    return div(
        li({class: "nav-item"},
            a({class: "nav-link", href: "#"}, "Login")),
        li({class: "nav-item"},
            a({class: "nav-link", href: "#"}, "Register")),
        br(),
        form(
            {class: "container-fluid", onSubmit: login},
            div({class: "input-group"},
                span({class: "input-group-text", id: "basic-addon1"}, "@"),
                input({
                    type: "text",
                    id: "userId",
                    class: "form-control",
                    placeholder: "Username",
                    "aria-label": "Username",
                    "aria-describedby": "basic-addon1"
                })
            )
        ),
        form(
            {class: "container-fluid", onSubmit: token},
            div({class: "input-group"},
                span({class: "input-group-text", id: "basic-addon1"}, "T"),
                input({
                    type: "text",
                    id: "userToken",
                    class: "form-control",
                    placeholder: "Token",
                    "aria-label": "Username",
                    "aria-describedby": "basic-addon1"
                })
            )
        )
    )
}

export default LoginRegister;