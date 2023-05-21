import {UpdateCardFetch} from "../../../api/fetch/cards/UpdateCardFetch.js";
import {hashChangeLoc} from "../../../utils/hash-change-loc.js";
import {button, div, form, li, ul} from "../../../dom/domTags.js";

export async function ArqCard(state) {

    const boardId = state.body["boardId"]
    const cardId = state.body["id"]

    async function arqCard(event) {
        event.preventDefault()

        const response = await UpdateCardFetch(cardId, null)

        //const json = await response.json()

        hashChangeLoc(`#boards/${boardId}`)
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
            "Arq"
        ),
        ul(
            {class: "dropdown-menu"},
            form(
                {onSubmit: arqCard},
                li(button({class: "dropdown-item", type: "submit"}, "Yes"))),
        )
    )
}