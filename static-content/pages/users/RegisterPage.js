import {button, div, form, h2, input, label, section} from "../../components/dom/domTags.js";


function RegisterPage(registerUser) {

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
                            {class: "container-fluid", onSubmit: registerUser},
                            div({class: "row"},
                                div({class: "col-md-6 mb-4"},
                                    div({class: "form-outline"},
                                        input({
                                            type: "text",
                                            id: "register-first-name",
                                            class: "form-control",
                                            placeholder: "First name"
                                        }),
                                    )
                                ),
                                div({class: "col-md-6 mb-4"},
                                    div({class: "form-outline"},
                                        input({
                                            type: "text",
                                            id: "register-last-name",
                                            class: "form-control",
                                            placeholder: "Last name"
                                        }),
                                    )
                                )
                            ),
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
                            button({type: "submit", class: "btn btn-primary btn-block mb-4"}, "Sign up"),
                        )
                    )
                )
            )
        )
    )
}

export default RegisterPage;