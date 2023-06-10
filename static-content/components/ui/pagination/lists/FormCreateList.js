import {button, div, form, input, label} from "../../../dom/domTags.js";

/**
 * FormCreateList is a function that returns a form for creating a new list.
 * The form includes an input field for entering the list name and a submitted button.
 *
 * @param {function} createList - The callback function to be called when the form is submitted.
 *
 * @returns {HTMLElement} The form element for creating a new list.
 */
export async function FormCreateList(createList) {
    return await form({class: "was-validated", onSubmit: createList},
        div(
            {class: "mb-3"},
            label(
                {for: "exampleFormControlInput1", class: "form-label"},
                "Enter list name"
            ),
            input(
                {
                    type: "name",
                    class: "form-control",
                    id: "exampleFormControlInput1",
                    placeholder: "List name",
                    required: true
                }
            ),
            div({class: "invalid-feedback"}, "Please enter a name")
        ),
        div({class: "mb-3"},
            button({class: "btn btn-outline-dark", type: "submit"}, "Submit form")
        )
    )
}