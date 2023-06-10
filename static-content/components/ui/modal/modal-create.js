import {button, div, form, h5, i, input, label, textarea} from "../../dom/domTags.js";

/**
 * ModalCreate is a function that returns a modal for creating a card.
 * It includes a button that triggers the modal and a form for entering card details.
 *
 * @param {function} func - The function to be called on form submission.
 * @param {Number} id - The unique identifier of the modal.
 *
 * @returns {Promise<HTMLElement>} The modal create an element.
 */
export function ModalCreate(func, id) {
    return div(
        div(
            {class: "icon-card-create"},
            i({class: "fas fa-plus", style: {color: "#000000"}}),
            button({type: "button", class: "btn", "data-bs-toggle": "modal", "data-bs-target": `#${id}modal`},
                "Add Card",
            )
        ),
        div({
                class: "modal fade",
                id: `${id}modal`,
                tabindex: "-1",
                "aria-labelledby": `${id}Label`,
                "aria-hidden": "true"
            },
            div({class: "modal-dialog"},
                div({class: "modal-content"},
                    div({class: "modal-header"},
                        h5({class: "modal-title", id: `${id}Label`}, "Create Card"),
                        button({type: "button", class: "btn-close", "data-bs-dismiss": "modal", "aria-label": "Close"})
                    ),
                    form(
                        {onSubmit: func},
                        div({class: "modal-body"},

                            div({class: "mb-3"},
                                label({for: "name", class: "form-label"}, "Name"),
                                input({type: "text", class: "form-control", id: `name-card-${id}`})
                            ),
                            div({class: "mb-3"},
                                label({for: "message", class: "form-label"}, "Description"),
                                textarea({class: "form-control", id: `description-card-${id}`})
                            )
                        ),
                        div({class: "modal-footer"},
                            button({type: "button", class: "btn btn-secondary", "data-bs-dismiss": "modal"}, "Close"),
                            input({type: "submit", class: "btn btn-primary", "data-bs-dismiss": "modal"}, "Submit")
                        )
                    )
                )
            )
        )
    )
}