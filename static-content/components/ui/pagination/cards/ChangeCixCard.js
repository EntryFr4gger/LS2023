import {button, div, form, li, ul} from "../../../dom/domTags.js";

/**
 * Creates a dropdown menu with options to change the position of a card.
 *
 * @param {Function} changeCix - The function to handle changing the position of the card.
 * @param {Object} cards - The list of cards.
 *
 * @returns {Promise<HTMLElement>} The dropdown menu with options to change the card position.
 */
export function ChangeCixCard(changeCix, cards) {
    return div(
        {class: "dropdown dropend"},
        button(
            {
                class: "btn dropdown-toggle",
                type: "button",
                "data-bs-toggle": "dropdown",
                "aria-expanded": "false"
            },
            "Change Place"
        ),
        ul(
            {class: "dropdown-menu"},
            form(
                {onSubmit: changeCix},
                ...cards.cards.map((card, index) =>
                    li(
                        button(
                            {
                                class: "dropdown-item",
                                type: "submit",
                                id: `${index}`,
                                "data-bs-dismiss": "modal",
                                "aria-label": "Close"
                            },
                            card.name)
                    )
                ),
            )
        )
    )
}