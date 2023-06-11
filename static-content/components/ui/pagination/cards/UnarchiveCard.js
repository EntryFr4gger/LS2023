import {a, button, div, form, li, strong, ul} from "../../../dom/domTags.js";

/**
 * Creates a dropdown menu with options to unarchive a card.
 *
 * @param {Function} unarchiveCard - The function to handle unarchiving of the card.
 * @param {Object} listNames - The list of names for the dropdown options.
 * @param {Object} card - The card to be unarchived.
 *
 * @returns {Promise<HTMLElement>} The dropdown menu with options to unarchive the card.
 */
export function UnarchiveCard(unarchiveCard, listNames, card) {
    return div(
        {class: "dropdown dropend"},
        a({
                href: "#",
                class: "d-flex align-items-center text-black text-decoration-none dropdown-toggle p-2",
                id: "dropdownUser1",
                "data-bs-toggle": "dropdown",
                "aria-expanded": "false"
            },
            strong("Unarchive")
        ),
        ul(
            {class: "dropdown-menu"},
            form(
                {onSubmit: unarchiveCard},
                ...listNames.lists.map(list =>
                    li(button({class: "dropdown-item", type: "submit", id: `${list.id}-${card["id"]}`},
                            list.name
                        )
                    )
                ),
            )
        )
    )
}