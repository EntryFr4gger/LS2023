import {hashChangeLoc} from "../../components/utils/hash-change-loc.js";
import {CreateBoardFetch} from "../../components/api/fetch/boards/CreateBoardFetch.js";
import {FormCreateBoard} from "../../components/ui/pagination/boards/FormCreateBoard.js";
import {DisableAttribute} from "../../components/utils/disable-attribute.js";
import {RemoveAttribute} from "../../components/utils/remove-attribute.js";

/**
 * CreateBoardHandler is a function that handles creating a new board.
 *
 * @returns {Promise} A promise that resolves to the rendered form for creating a board.
 */
async function CreateBoardHandler() {

    /**
     * createBoard is an asynchronous function that handles the form submission for creating a board.
     *
     * @param {Event} event - The form submission event.
     */
    async function createBoard(event) {
        event.preventDefault()
        const name = document.getElementById("board-name").value
        const description = document.getElementById("board-description").value

        DisableAttribute(event.target[2])

        const boardId = await CreateBoardFetch(name, description)

        if (boardId)
            hashChangeLoc(`#boards/${boardId.id}`)
        else
            RemoveAttribute(event.target[2])
    }

    return await FormCreateBoard(createBoard)
}

export default CreateBoardHandler;