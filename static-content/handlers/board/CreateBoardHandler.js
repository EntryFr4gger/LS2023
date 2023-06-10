import {hashChangeLoc} from "../../components/utils/hash-change-loc.js";
import {CreateBoardFetch} from "../../components/api/fetch/boards/CreateBoardFetch.js";
import {FormCreateBoard} from "../../components/ui/pagination/boards/FormCreateBoard.js";
import {DisableAttribute} from "../../components/utils/disable-attribute.js";

function CreateBoardHandler() {

    async function createBoard(event) {
        event.preventDefault()
        const name = document.getElementById("exampleFormControlInput1").value
        const description = document.getElementById("validationTextarea").value

        DisableAttribute(event.target[2])

        const response = await CreateBoardFetch(name, description)

        const boardId = await response.json()

        if (response.ok)
            hashChangeLoc(`#boards/${boardId.id}`)
        else
            alert(boardId.error)
    }

    return FormCreateBoard(createBoard)
}

export default CreateBoardHandler;