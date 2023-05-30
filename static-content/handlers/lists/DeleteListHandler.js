import {hashChangeLoc} from "../../components/utils/hash-change-loc.js";
import {DeleteListFetch} from "../../components/api/fetch/lists/DeleteListFetch.js";
import {DeleteButtonHover} from "../../components/ui/button/delete-button-hover.js";

function DeleteListHandler(state, list) {

    async function deleteList(event) {
        event.preventDefault()

        const response = await DeleteListFetch(list["id"])

        const json = await response.json()

        if (response.ok)
            hashChangeLoc(`#boards/${state.body["id"]}`)
        else
            alert(json.error)
    }

    return DeleteButtonHover(deleteList)
}

export default DeleteListHandler;