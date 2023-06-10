import {button, div, form, input, label, textarea} from "../../../dom/domTags.js";

/**
 * FormCreateBoard is a function that returns a form for creating a board.
 * It includes input fields for entering the board name and description,
 * and a submitted button for submitting the form.
 *
 * @param {function} createBoard - The function to be called on form submission.
 *
 * @returns {Promise<HTMLElement>} The form creates a board element.
 */
export async function FormCreateBoard(createBoard) {
    return await form({class: "was-validated", onSubmit: createBoard},
        div(
            {class: "mb-3"},
            label(
                {for: "board-name", class: "form-label"},
                "Enter board name"
            ),
            input(
                {
                    type: "name",
                    class: "form-control",
                    id: "board-name",
                    placeholder: "Board name",
                    required: true
                }
            ),
            div({class: "invalid-feedback"}, "Please enter a name")
        ),
        div({class: "mb-3"},
            label({for: "board-description", class: "form-label"}, "Description"),
            textarea({
                class: "form-control",
                id: "board-description",
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