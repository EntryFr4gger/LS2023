import {hashChangeLoc} from "../../components/utils/hash-change-loc.js";
import {CreateCardFetch} from "../../components/api/fetch/cards/CreateCardFetch.js";
import {ModalCreate} from "../../components/ui/modal/modal-create.js";
import {DisableAttribute} from "../../components/utils/disable-attribute.js";

/**
 * CreateCardHandler is a function that handles creating a new card for a specific board and list.
 *
 * @param {Int} boardId - The ID of the board where the card will be created.
 * @param {Int} listId - The ID of the list where the card will be created.
 *
 * @returns {Function} A function that handles the creation of a new card.
 */
function CreateCardHandler(boardId, listId) {

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

        if (name.trim() === "" || description.trim() === "")
            alert("Please fill out all fields")
        else {
            const response =
                await CreateCardFetch(name, description, boardId, listId)

            const json = await response.json()

            if (response.ok)
                hashChangeLoc(`#boards/${boardId}`)
            else
                alert(json.error)
        }
    }

    return ModalCreate(createCard, listId)
}

export default CreateCardHandler;