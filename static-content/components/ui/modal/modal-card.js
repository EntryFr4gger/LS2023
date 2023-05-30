import {button, div, h5, i, li} from "../../dom/domTags.js";
import DeleteCardHandler from "../../../handlers/cards/DeleteCardHandler.js";
import {ArqCard} from "../pagination/cards/ArqCard.js";

export function ModalCard(state, id, cardName) {


    return li(
        {class: "list-group-item"},
        div(
            {class: "icon-card-create"},
            i({class: "fas fa-plus", style: {color: "#000000"}}),
            button({type: "button", class: "btn-danger", "data-bs-toggle": "modal", "data-bs-target": `#${id}modal`},
                cardName,
            )
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
                        h5({class: "modal-title", id: `${id}Label`}, "Create Info"),
                        button({type: "button", class: "btn-close", "data-bs-dismiss": "modal", "aria-label": "Close"})
                    ),
                    div({class: "modal-body"},
                        DeleteCardHandler(state, id),
                        //ChangeCixCard(state),
                        ArqCard(state, id))
                )
            )
        )
    )
}
