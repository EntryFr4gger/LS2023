import {button, div, form, h2, input, label, section} from "../../components/dom/domTags.js";

function LoginPage(loginUser) {

    return section(
        {class: "text-center"},
        div(
            {
                class: "card mx-4 mx-md-5 shadow-5-strong",
                id: "test",
                style: {
                    "margin-top": "100px",
                    background: "hsla(0, 0%, 100%, 0.1)",
                    "background-color": "White"/*, opacity:"20%"*/
                }
            },
            div(
                {class: "card-body py-5 px-md-5"},
                div({class: "row d-flex justify-content-center"},
                    div({class: "col-lg-8"},
                        h2({class: "fw-bold mb-5"}, "Sign up now"),
                        form(
                            {class: "container-fluid", onSubmit: loginUser},
                            div({class: "form-outline mb-4"},
                                input({
                                    type: "email",
                                    id: "register-email",
                                    class: "form-control",
                                    placeholder: "Email address"
                                }),
                            ),
                            div({class: "form-outline mb-4"},
                                input({
                                    type: "password",
                                    id: "register-password",
                                    class: "form-control",
                                    placeholder: "Password"
                                }),
                            ),
                            div({class: "form-check d-flex justify-content-center mb-4"},
                                input({
                                    class: "form-check-input me-2",
                                    type: "checkbox",
                                    value: "",
                                    id: "form2Example33",
                                    checked: ""
                                }),
                                label({
                                    class: "form-check-label",
                                    for: "form2Example33"
                                }, "Remember me")
                            ),
                            button({type: "submit", class: "btn btn-primary btn-block mb-4"}, "Login"),
                        )
                    )
                )
            )
        )
    )
}

export default LoginPage;