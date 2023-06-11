import {MoveCardFetch} from "../../components/api/fetch/cards/MoveCardFetch.js";
import {hashChangeLoc} from "../../components/utils/hash-change-loc.js";
import {ArchiveCard} from "../../components/ui/pagination/cards/ArchiveCard.js";

/**
 * ArchiveCardHandler is a function that returns a dropdown menu for archiving a card.
 * It includes a confirmation prompt with options to archive the card.
 *
 * @param {object} state - The state object containing application state.
 * @param {Number} cardId - The ID of the card to be archived.
 *
 * @returns {HTMLElement} The dropdown menu element for archiving the card.
 */
export async function ArchiveCardHandler(state, cardId) {

    const boardId = state.pathParams["board_id"]

    /**
     * Handles the archival of a card.
     *
     * @param {Event} event - The form submission event.
     */
    async function arqCard(event) {
        event.preventDefault()

        const response = await MoveCardFetch(cardId)

        if (response) hashChangeLoc(`#boards/${boardId}`)
    }

    return ArchiveCard(arqCard)
}