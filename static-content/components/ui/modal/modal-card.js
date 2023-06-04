import {button, div, h5, li, p, svg, ul, use} from "../../dom/domTags.js";
import DeleteCardHandler from "../../../handlers/cards/DeleteCardHandler.js";
import {ArchiveCard} from "../pagination/cards/ArchiveCard.js";
import {UpdateCard} from "../pagination/cards/UpdateCard.js";
import {ChangeCixCard} from "../pagination/cards/ChangeCixCard.js";

export function ModalCard(state, id, cardName, cardDescription, listId) {
    return li(
        {class: "list-group-item"},
        button({type: "button", class: "btn", "data-bs-toggle": "modal", "data-bs-target": `#${id}modal`},
            cardName,
        ),
        div({
                class: "modal fade",
                id: `${id}modal`,
                tabindex: "-1",
                "aria-labelledby": `${id}Label`,
                "aria-hidden": "true"
            },
            div({class: "modal-dialog"},
                div({class: "modal-content"},
                    div({class: "modal-header"},
                        svg({class: "bi me-2 fa-solid fa-list fa-lm", width: "16", height: "16", style:{"padding-top":"8px"}}, use({xlink: "#people-circle"})),
                        h5({class: "modal-title", id: `${id}Label`}, cardName),
                        button({type: "button", class: "btn-close", "data-bs-dismiss": "modal", "aria-label": "Close"})
                    ),
                    div(
                        {class: "modal-body"},
                        ul(
                            {class:"list-inline d-flex justify-content-start", style:{"margin-left":"10px"}},
                            li({class: "list-inline-item"}, svg({class: "bi me-2 fa-regular fa-message", width: "16", height: "16"}, use({xlink: "#people-circle"}))),
                            li({class: "list-inline-item"}, p(cardDescription))
                        ),
                        ul(
                            {class: "list-group"},
                            li({class: "list-group-item d-flex justify-content-center"}, DeleteCardHandler(state, id)),
                            li({class: "list-group-item d-flex justify-content-center"}, ArchiveCard(state, id)),
                            li({class: "list-group-item d-flex justify-content-center"}, UpdateCard(state, listId, id)),
                            li({class: "list-group-item d-flex justify-content-center"}, ChangeCixCard(state, listId, id))
                        ),
                    )
                )
            )
        )
    )
}
