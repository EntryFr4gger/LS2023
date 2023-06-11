import {button, div, form} from "../../dom/domTags.js";

/**
 * DeleteButtonHover component generates a form with a delete button that appears on hover.
 *
 * @param {Function} deleteFunc - The function to be executed when the form is submitted (delete action).
 *
 * @returns {HTMLElement} - The generated form element with the delete button.
 */
export function DeleteButtonHover(deleteFunc) {
    return form(
        {onSubmit: deleteFunc},
        div(
            {class: "hover-btn"},
            button({class: "btn-close", "data-dismiss": "alert"},)
        )
    )
}