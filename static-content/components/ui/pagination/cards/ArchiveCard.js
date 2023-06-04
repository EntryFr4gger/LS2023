import {UpdateCardFetch} from "../../../api/fetch/cards/UpdateCardFetch.js";
import {hashChangeLoc} from "../../../utils/hash-change-loc.js";
import {button, div, form, li, ul} from "../../../dom/domTags.js";

export async function ArchiveCard(state, cardId) {

    const boardId = state.pathParams["board_id"]

    async function arqCard(event) {
        event.preventDefault()

        const response = await UpdateCardFetch(cardId, null)

        //const json = await response.json()

        hashChangeLoc(`#boards/${boardId}`)
    }

    return div(
        {class: "dropdown dropend"},
        button(
            {
                class: "btn dropdown-toggle",
                type: "button",
                "data-bs-toggle": "dropdown",
                "aria-expanded": "false",
                "data-bs-offset":"-6,10"
            },
            "Archive"
        ),
        ul(
            {class: "dropdown-menu"},
            form(
                {onSubmit: arqCard},
                li(
                    button(
                        {class: "dropdown-item", type: "submit", "data-bs-dismiss": "modal", "aria-label": "Close"},
                        "Yes"
                    )
                )
            ),
        )
    )
}