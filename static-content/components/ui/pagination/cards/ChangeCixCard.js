import {hashChangeLoc} from "../../../utils/hash-change-loc.js";
import {button, div, form, li, ul} from "../../../dom/domTags.js";
import {GetListCardsFetch} from "../../../api/fetch/lists/GetListCardsFetch.js";
import {RepositionCardFetch} from "../../../api/fetch/cards/RepositionCardFetch.js";

export async function ChangeCixCard(state, listId, cardId) {

    const boardId = state.body["id"]

    const response = await GetListCardsFetch(listId)

    const json = await response.json()


    async function changeCix(event) {
        event.preventDefault()

        const value = Number(event.submitter.id) + 1

        const response = await RepositionCardFetch(listId, cardId, value)

        const updated = await response.json()

        hashChangeLoc(`#boards/${boardId}`)
    }

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
                ...json.cards.map((card, index) =>
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