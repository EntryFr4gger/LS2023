import {hashChangeLoc} from "../../components/utils/hash-change-loc.js";
import {AddUserToBoardFetch} from "../../components/api/fetch/boards/AddUserToBoardFetch.js";
import {FormAddUserBoard} from "../../components/ui/pagination/boards/FormAddUserBoard.js";
import GetAllUsersHandler from "../user/GetAllUsersHandler.js";

function AddUserToBoardHandler(state) {

    async function addUserToBoard(event) {
        event.preventDefault()
        const boardId = state.pathParams["board_id"]
        const value = document.getElementById("exampleDataList").value
        const options = document.querySelectorAll('#datalistOptions option')

        const found = Array.from(options).filter(option => option.label === value)

        if (found.length !== 0) {

            const response = await AddUserToBoardFetch(boardId, found[0].getAttribute('data-value'))

            const json = await response.json()

            if (response.ok)
                hashChangeLoc(`#boards/${boardId}/users`)
            else
                alert(json.error)
        } else {
            alert("That email doesn't exist")
        }
    }

    return FormAddUserBoard(addUserToBoard, GetAllUsersHandler(state))
}

export default AddUserToBoardHandler;