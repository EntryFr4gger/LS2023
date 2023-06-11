import {GetBoardListsFetch} from "../../components/api/fetch/boards/GetBoardListsFetch.js";
import {MoveCardFetch} from "../../components/api/fetch/cards/MoveCardFetch.js";
import {hashChangeLoc} from "../../components/utils/hash-change-loc.js";
import {UnarchiveCard} from "../../components/ui/pagination/cards/UnarchiveCard.js";

/**
 * UnarchiveCardHandler is a function that returns a dropdown menu for unarchiving a card and moving it to a different list.
 * It includes a list of options representing the available lists to move the card.
 *
 * @param {object} state - The state object containing application state.
 * @param {object} card - The card object to be unarchived.
 *
 * @returns {HTMLElement} The dropdown menu element for unarchiving and moving the card.
 */
export async function UnarchiveCardHandler(state, card) {

    const boardId = state.pathParams["board_id"]

    const listNames = await GetBoardListsFetch(boardId)

    /**
     * Handles the unarchiving of a card.
     *
     * @param {Event} event - The form submission event.
     */
    async function unarchiveCard(event) {
        event.preventDefault()

        const value = event.submitter.id

        const split = value.split("-")

        const listId = Number(split[0])
        const cardId = Number(split[1])

        const response = await MoveCardFetch(cardId, listId)

        if (response) hashChangeLoc(`#boards/${boardId}`)
    }

    return UnarchiveCard(unarchiveCard, listNames, card)
}