import {hashChangeLoc} from "../../components/utils/hash-change-loc.js";
import {CreateCardFetch} from "../../components/api/fetch/cards/CreateCardFetch.js";
import {ModalCreate} from "../../components/ui/modal/modal-create.js";
import {DisableAttribute} from "../../components/utils/disable-attribute.js";
import {RemoveAttribute} from "../../components/utils/remove-attribute.js";

/**
 * CreateCardHandler is a function that handles creating a new card for a specific board and list.
 *
 * @param {Number} boardId - The ID of the board where the card will be created.
 * @param {Number} listId - The ID of the list where the card will be created.
 *
 * @returns {Function} A function that handles the creation of a new card.
 */
async function CreateCardHandler(boardId, listId) {

    /**
     * createCard is an asynchronous function that handles the creation of a new card.
     *
     * @param {Object} event - The event object representing the form submission.
     */
    async function createCard(event) {
        event.preventDefault()
        const name = document.getElementById(`name-card-${listId}`).value
        const description = document.getElementById(`description-card-${listId}`).value

        DisableAttribute(event.target[2])

        if (name.trim() === "" || description.trim() === "") {
            alert("Please fill out all fields")
            RemoveAttribute(event.target[2])
        } else {
            const cardId = await CreateCardFetch(name, description, boardId, listId)

            if (cardId)
                hashChangeLoc(`#boards/${boardId}`)
        }
    }

    return await ModalCreate(createCard, listId)
}

export default CreateCardHandler;