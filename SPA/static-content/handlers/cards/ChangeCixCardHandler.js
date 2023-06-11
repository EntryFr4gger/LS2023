import {hashChangeLoc} from "../../components/utils/hash-change-loc.js";
import {GetListCardsFetch} from "../../components/api/fetch/lists/GetListCardsFetch.js";
import {RepositionCardFetch} from "../../components/api/fetch/lists/RepositionCardFetch.js";
import {ChangeCixCard} from "../../components/ui/pagination/cards/ChangeCixCard.js";

/**
 * ChangeCixCardHandler is a function that returns a dropdown menu for changing the position of a card within a list.
 * It includes a list of options representing the available positions to move the card.
 *
 * @param {object} state - The state object containing application state.
 * @param {Number} listId - The ID of the list containing the card.
 * @param {Number} cardId - The ID of the card to be repositioned.
 *
 * @returns {HTMLElement} The dropdown menu element for changing the position of the card.
 */
export async function ChangeCixCardHandler(state, listId, cardId) {

    const boardId = state.body["id"]

    const cards = await GetListCardsFetch(listId)

    /**
     * Handles the change in the position of a card within a list.
     *
     * @param {Event} event - The form submission event.
     */
    async function changeCix(event) {
        event.preventDefault()

        const value = Number(event.submitter.id) + 1

        const response = await RepositionCardFetch(listId, cardId, value)

        if (response) hashChangeLoc(`#boards/${boardId}`)
    }

    return await ChangeCixCard(changeCix, cards)
}