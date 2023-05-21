import {changeHashLocation} from "../../utils/change-hash-location.js";
import {CreateListFetch} from "../../api/fetch/lists/CreateListFetch.js";
import {FormCreateList} from "../../ui/pagination/lists/FormCreateList.js";

function CreateListHandler(state) {
    async function createList(event) {
        event.preventDefault()
        const boardId = state.pathParams["board_id"]
        const name = document.getElementById("exampleFormControlInput1").value

        const response = await CreateListFetch(name, boardId)

        const listId = await response.json()

        changeHashLocation(`#boards/${boardId}`)
    }

    return FormCreateList(createList)
}

export default CreateListHandler;