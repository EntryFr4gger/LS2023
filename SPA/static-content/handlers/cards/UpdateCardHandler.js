import {GetBoardListsFetch} from "../../components/api/fetch/boards/GetBoardListsFetch.js";
import {hashChangeLoc} from "../../components/utils/hash-change-loc.js";
import {MoveCardFetch} from "../../components/api/fetch/cards/MoveCardFetch.js";
import {UpdateCard} from "../../components/ui/pagination/cards/UpdateCard.js";

/**
 * UpdateCardHandler is a function that returns a dropdown menu for updating the list of a card.
 * It includes a list of options representing the available lists to move the card.
 *
 * @param {object} state - The state object containing application state.
 * @param {Number} listId - The ID of the current list containing the card.
 * @param {Number} cardId - The ID of the card to be updated.
 *
 * @returns {HTMLElement} The dropdown menu element for updating the card's list.
 */
export async function UpdateCardHandler(state, listId, cardId) {

    const boardId = state.body["id"]

    const listNames = await GetBoardListsFetch(boardId)

    const listNamesMove = listNames.lists.filter(list => list.id !== listId)

    /**
     * Handles the update of a card.
     *
     * @param {Event} event - The form submission event.
     */
    async function updateCard(event) {
        event.preventDefault()

        const value = event.submitter.id

        const split = value.split("-")

        const listId = Number(split[0])
        const cardId = Number(split[1])

        const response = await MoveCardFetch(cardId, listId)

        if (response) hashChangeLoc(`#boards/${boardId}`)
    }

    return await UpdateCard(updateCard, listNamesMove, cardId)
}