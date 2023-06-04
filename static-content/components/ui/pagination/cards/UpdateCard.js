import {button, div, form, li, ul} from "../../../dom/domTags.js";
import {GetBoardListsFetch} from "../../../api/fetch/boards/GetBoardListsFetch.js";
import {hashChangeLoc} from "../../../utils/hash-change-loc.js";
import {UpdateCardFetch} from "../../../api/fetch/cards/UpdateCardFetch.js";
import ArchivedCardsPage from "../../../../pages/cards/ArchivedCardsPage.js";

export async function UpdateCard(state, listId, cardId) {

    const boardId = state.body["id"]

    const response = await GetBoardListsFetch(boardId, 0, 100)

    const listNames = await response.json()

    const listNamesMove = listNames.lists.filter(list => list.id !== listId)

    async function updateCard(event) {
        event.preventDefault()

        const value = event.submitter.id

        const split = value.split("-")

        const listId = split[0]
        const cardId = split[1]

        const response = await UpdateCardFetch(cardId, listId)

        const json = await response.json()

        if (response.ok) {
            hashChangeLoc(`#boards/${boardId}`)
        } else
        alert(json.error)
    }

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