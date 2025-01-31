import {button, div, h5, li, p, svg, ul, use} from "../../dom/domTags.js";
import DeleteCardHandler from "../../../handlers/cards/DeleteCardHandler.js";
import {ArchiveCardHandler} from "../../../handlers/cards/ArchiveCardHandler.js";
import {UpdateCardHandler} from "../../../handlers/cards/UpdateCardHandler.js";
import {ChangeCixCardHandler} from "../../../handlers/cards/ChangeCixCardHandler.js";

/**
 * ModalCard is a function that returns a modal card element.
 * It includes a button that triggers a modal, and the modal content itself.
 * The modal card displays the card name, description, and various actions.
 * It also includes handlers for deleting, archiving, updating, and changing the card's CIX.
 *
 * @param {object} state - The state object.
 * @param {Number} id - The unique identifier of the modal card.
 * @param {string} cardName - The name of the card.
 * @param {string} cardDescription - The description of the card.
 * @param {Number} listId - The identifier of the list the card belongs to.
 *
 * @returns {Promise<HTMLElement>} Modal card element.
 */
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
                        svg({
                            class: "bi me-2 fa-solid fa-list fa-lm",
                            width: "16",
                            height: "16",
                            style: {"padding-top": "8px"}
                        }, use({xlink: "#people-circle"})),
                        h5({class: "modal-title", id: `${id}Label`}, cardName),
                        button({type: "button", class: "btn-close", "data-bs-dismiss": "modal", "aria-label": "Close"})
                    ),
                    div(
                        {class: "modal-body"},
                        ul(
                            {class: "list-inline d-flex justify-content-start", style: {"margin-left": "10px"}},
                            li({class: "list-inline-item"}, svg({
                                class: "bi me-2 fa-regular fa-message",
                                width: "16",
                                height: "16"
                            }, use({xlink: "#people-circle"}))),
                            li({class: "list-inline-item"}, p(cardDescription))
                        ),
                        ul(
                            {class: "list-group"},
                            li({class: "list-group-item d-flex justify-content-center"}, DeleteCardHandler(state, id)),
                            li({class: "list-group-item d-flex justify-content-center"}, ArchiveCardHandler(state, id)),
                            li({class: "list-group-item d-flex justify-content-center"}, UpdateCardHandler(state, listId, id)),
                            li({class: "list-group-item d-flex justify-content-center"}, ChangeCixCardHandler(state, listId, id))
                        ),
                    )
                )
            )
        )
    )
}
