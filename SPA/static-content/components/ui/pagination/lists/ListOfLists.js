import {a, div, h5, li, ul} from "../../../dom/domTags.js";
import CreateCardHandler from "../../../../handlers/cards/CreateCardHandler.js";
import DeleteListHandler from "../../../../handlers/lists/DeleteListHandler.js";
import {ModalCard} from "../../modal/modal-card.js";

/**
 * ListOfLists is a function that returns a list item representing a list.
 *
 * @param {object} state - The state object containing relevant data.
 * @param {object} list - The list object containing list information.
 * @param {array} cards - An array of card objects belonging to the list.
 *
 * @returns {HTMLElement} The list item representing a list.
 */
export async function ListOfLists(state, list, cards) {
    return li(
        {class: "list-inline-item"},
        div(
            {class: "card", style: {width: "18rem"}},
            div(
                {class: "card-header hover-delete"},
                a(
                    {href: `/#lists/${list.id}`, style: {"text-decoration": "none", color: "#000000"}},
                    h5({class: "card-title", id: list.id}, `${list.name}`),
                    DeleteListHandler(state, list)
                )
            ),
            ul(
                {class: "sortable-list list-group "},
                ...cards.map(card => ModalCard(state, card.id, card.name, card.description, list.id)
                )
            ),
            CreateCardHandler(list.boardId, list.id))
    )
}
