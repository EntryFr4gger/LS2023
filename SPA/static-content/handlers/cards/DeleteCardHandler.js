import {hashChangeLoc} from "../../components/utils/hash-change-loc.js";
import {DeleteCardFetch} from "../../components/api/fetch/cards/DeleteCardsFetch.js";
import {DeleteButton} from "../../components/ui/button/delete-button.js";

/**
 * DeleteCardHandler is a function that handles the deletion of a card.
 *
 * @param {Object} state - The state object containing the necessary information.
 * @param {Number} cardId - The ID of the card to be deleted.
 *
 * @returns {Function} A function that handles the deletion of the card.
 */
async function DeleteCardHandler(state, cardId) {

    /**
     * deleteCard is an asynchronous function that handles the deletion of the card.
     *
     * @param {Object} event - The event object representing the form submission.
     */
    async function deleteCard(event) {
        event.preventDefault()

        const response = await DeleteCardFetch(cardId)

        if (response)
            hashChangeLoc(`#boards/${state.pathParams["board_id"]}`)
    }

    return await DeleteButton(deleteCard)
}

export default DeleteCardHandler;
