import {button, div, form, li, ul} from "../../../dom/domTags.js";

/**
 * Creates a dropdown menu with options to update the list of a card.
 *
 * @param {Function} updateCard - The function to handle updating the list of the card.
 * @param {Object[]} listNamesMove - The list of names for the dropdown options.
 * @param {number} cardId - The ID of the card to be updated.
 *
 * @returns {Promise<HTMLElement>} The dropdown menu with options to update the card list.
 */
export function UpdateCard(updateCard, listNamesMove, cardId) {
    return div(
        {class: "dropdown dropend"},
        button(
            {
                class: "btn dropdown-toggle",
                type: "button",
                "data-bs-toggle": "dropdown",
                "aria-expanded": "false",
                "data-bs-offset": "-6,10"
            },
            "Change List"
        ),
        ul(
            {class: "dropdown-menu"},
            form(
                {onSubmit: updateCard},
                ...listNamesMove.map(list =>
                    li(
                        {class: "d-flex justify-content-center"},
                        button({
                                class: "dropdown-item",
                                type: "submit",
                                id: `${list.id}-${cardId}`,
                                "data-bs-dismiss": "modal",
                                "aria-label": "Close"
                            }, list.name
                        )
                    )
                ),
            )
        )
    )
}