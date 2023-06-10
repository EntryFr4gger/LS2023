import {button, form, svg, use} from "../../dom/domTags.js";

/**
 * DeleteButton component generates a form with a delete button.
 *
 * @param {Function} deleteFunc - The function to be executed when the form is submitted (delete action).
 * @param {String} color - Color of the button.
 *
 * @returns {Promise<HTMLElement>} - The generated form element with the delete button.
 */
export function DeleteButton(deleteFunc, color = "dark") {
    return form(
        {onSubmit: deleteFunc},
        button({class: `nav-link text-${color}`, "data-bs-dismiss": "modal", "aria-label": "Close"},
            svg({class: "bi me-2 fa-solid fa-trash-can", width: "16", height: "16"}, use({xlink: "#people-circle"})),
            "Delete"
        )
    )
}