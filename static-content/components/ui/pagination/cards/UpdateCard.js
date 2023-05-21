import {button, div, form, input, li, ul} from "../../../dom/domTags.js";
import {GetBoardListsFetch} from "../../../api/fetch/boards/GetBoardListsFetch.js";
import {changeHashLocation} from "../../../utils/change-hash-location.js";
import {UpdateCardFetch} from "../../../api/fetch/cards/UpdateCardFetch.js";

export async function UpdateCard(state) {

    const boardId = state.body["boardId"]

    const response = await GetBoardListsFetch(boardId, 0, 100)

    const listNames = await response.json()

    const listNamesMove = listNames.lists.filter(list => list.id !== state.body["listId"])

    async function updateCard(event) {
        event.preventDefault()

        const value = event.submitter.id

        const split = value.split("-")

        const listId = split[0]
        const cardId = split[1]

        const response = await UpdateCardFetch(cardId, listId)

        const updated = await response.json()

        changeHashLocation(`#boards/${boardId}`)
    }

    return div(
        {class: "dropdown"},
        button(
            {
                class: "btn btn-outline-light dropdown-toggle",
                type: "button",
                "data-bs-toggle": "dropdown",
                "aria-expanded": "false"
            },
            "Change List"
        ),
        ul(
            {class: "dropdown-menu"},
            form(
                {onSubmit: updateCard},
                ...listNamesMove.map(list =>
                    li(button({class:"dropdown-item", type: "submit", id: `${list.id}-${state.body["id"]}`}, list.name))),
            )
        )
    )
}