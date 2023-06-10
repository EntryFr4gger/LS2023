import {GetBoardListsFetch} from "../../../api/fetch/boards/GetBoardListsFetch.js";
import {MoveCardFetch} from "../../../api/fetch/cards/MoveCardFetch.js";
import {hashChangeLoc} from "../../../utils/hash-change-loc.js";
import {a, button, div, form, li, strong, ul} from "../../../dom/domTags.js";

/**
 * UnarchiveCard is a function that returns a dropdown menu for unarchiving a card and moving it to a different list.
 * It includes a list of options representing the available lists to move the card.
 *
 * @param {object} state - The state object containing application state.
 * @param {object} card - The card object to be unarchived.
 *
 * @returns {HTMLElement} The dropdown menu element for unarchiving and moving the card.
 */
export async function UnarchiveCard(state, card) {

    const boardId = state.pathParams["board_id"]

    const response = await GetBoardListsFetch(boardId, 0, 100)

    const listNames = await response.json()

    /**
     * Handles the unarchiving of a card.
     *
     * @param {Event} event - The form submission event.
     */
    async function unarchiveCard(event) {
        event.preventDefault()

        const value = event.submitter.id

        const split = value.split("-")

        const listId = split[0]
        const cardId = split[1]

        const response = await MoveCardFetch(cardId, listId)

        //const updated = await response.json()

        hashChangeLoc(`#boards/${boardId}`)
    }

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