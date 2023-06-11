import {hashChangeLoc} from "../../components/utils/hash-change-loc.js";
import {CreateListFetch} from "../../components/api/fetch/lists/CreateListFetch.js";
import {FormCreateList} from "../../components/ui/pagination/lists/FormCreateList.js";
import {DisableAttribute} from "../../components/utils/disable-attribute.js";

/**
 * CreateListHandler is a function that handles the creation of a new list.
 *
 * @param {Object} state - The state object containing the necessary information.
 *
 * @returns {HTMLElement} A promise that resolves to the rendered created list form.
 */
function CreateListHandler(state) {
    async function createList(event) {
        event.preventDefault()
        const boardId = state.pathParams["board_id"]
        const name = document.getElementById("exampleFormControlInput1").value

        DisableAttribute(event.target[1])

        const listId = await CreateListFetch(name, boardId)

        if (listId) hashChangeLoc(`#boards/${boardId}`)
    }

    return FormCreateList(createList)
}

export default CreateListHandler;