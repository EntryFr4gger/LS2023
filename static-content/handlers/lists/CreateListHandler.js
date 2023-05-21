import {hashChangeLoc} from "../../components/utils/hash-change-loc.js";
import {CreateListFetch} from "../../components/api/fetch/lists/CreateListFetch.js";
import {FormCreateList} from "../../components/ui/pagination/lists/FormCreateList.js";

function CreateListHandler(state) {
    async function createList(event) {
        event.preventDefault()
        const boardId = state.pathParams["board_id"]
        const name = document.getElementById("exampleFormControlInput1").value

        const response = await CreateListFetch(name, boardId)

        const listId = await response.json()

        if (response.ok)
            hashChangeLoc(`#boards/${boardId}`)
        else
            alert(listId.error)
    }

    return FormCreateList(createList)
}

export default CreateListHandler;