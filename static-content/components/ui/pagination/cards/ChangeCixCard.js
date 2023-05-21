import {UpdateCardFetch} from "../../../api/fetch/cards/UpdateCardFetch.js";
import {changeHashLocation} from "../../../utils/change-hash-location.js";
import {button, div, form, li, ul} from "../../../dom/domTags.js";
import {GetListCardsFetch} from "../../../api/fetch/lists/GetListCardsFetch.js";

export async function ChangeCixCard(state) {

    const boardId = state.body["boardId"]
    const listId = state.body["listId"]
    const cardId = state.body["id"]

    const response = await GetListCardsFetch(listId)

    const json = await response.json()


    async function changeCix(event) {
        event.preventDefault()

        const value = Number(event.submitter.id)

        const response = await UpdateCardFetch(cardId, listId, value)

        //const updated = await response.json()

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
            "Change Cix"
        ),
        ul(
            {class: "dropdown-menu"},
            form(
                {onSubmit: changeCix},
                ...json.cards.map((card, index) =>
                    li(button({class:"dropdown-item", type: "submit", id: `${index}`}, card.name))),
            )
        )
    )
}