import {button, div, form, input, label, textarea} from "../../../dom/domTags.js";


export async function FormCreateBoard(createBoard) {
    return await form({class: "was-validated", onSubmit: createBoard},
        div(
            {class: "mb-3"},
            label(
                {for: "exampleFormControlInput1", class: "form-label"},
                "Enter board name"
            ),
            input(
                {
                    type: "name",
                    class: "form-control",
                    id: "exampleFormControlInput1",
                    placeholder: "Board name",
                    required: true
                }
            ),
            div({class: "invalid-feedback"}, "Please enter a name")
        ),
        div({class: "mb-3"},
            label({for: "validationTextarea", class: "form-label"}, "Description"),
            textarea({
                class: "form-control",
                id: "validationTextarea",
                placeholder: "Required board description",
                maxLength: "1000",
                required: true
            }),
            div({class: "invalid-feedback"}, "Please enter a message for board description.")
        ),
        div({class: "mb-3"},
            button({class: "btn btn-outline-dark", type: "submit"}, "Submit form")
        )
    )
}