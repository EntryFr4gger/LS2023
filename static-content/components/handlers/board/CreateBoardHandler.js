import {changeHashLocation} from "../../utils/change-hash-location.js";
import {CreateBoardFetch} from "../../api/fetch/boards/CreateBoardFetch.js";
import {FormCreateBoard} from "../../ui/pagination/boards/FormCreateBoard.js";

function CreateBoardHandler() {

    async function createBoard(event) {
        event.preventDefault()
        const name = document.getElementById("exampleFormControlInput1").value
        const description = document.getElementById("validationTextarea").value

        const response = await CreateBoardFetch(name, description)

        const boardId = await response.json()

        changeHashLocation(`#boards/${boardId.id}`)
    }

    return FormCreateBoard(createBoard)
}

export default CreateBoardHandler;