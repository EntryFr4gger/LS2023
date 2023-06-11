import {button, div, form, li, ul} from "../../../dom/domTags.js";

/**
 * Creates a dropdown menu with an "Archive" option.
 *
 * @param {Function} archiveCard - The function to handle archiving of the card.
 *
 * @returns {Promise<HTMLElement>} The dropdown menu with the "Archive" option.
 */
export function ArchiveCard(archiveCard) {
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
            "Archive"
        ),
        ul(
            {class: "dropdown-menu"},
            form(
                {onSubmit: archiveCard},
                li(
                    button(
                        {class: "dropdown-item", type: "submit", "data-bs-dismiss": "modal", "aria-label": "Close"},
                        "Yes"
                    )
                )
            )
        )
    )
}