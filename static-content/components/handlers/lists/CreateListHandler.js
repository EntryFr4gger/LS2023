import {changeHashLocation} from "../../utils/change-hash-location.js";
import {button, div, form, input, label} from "../../dom/domTags.js";
import {CreateListFetch} from "../../api/fetch/lists/CreateListFetch.js";

function CreateListHandler(state) {
    async function createList(event) {
        event.preventDefault()
        const boardId = state.pathParams["board_id"]
        const name = document.getElementById("exampleFormControlInput1").value

        const response = await CreateListFetch(name, boardId)

        const listId = await response.json()

        changeHashLocation(`#lists/${listId.id}`)
    }

    return form({class: "was-validated", onSubmit: createList},
        div(
            {class: "mb-3"},
            label(
                {for: "exampleFormControlInput1", class: "form-label"},
                "Enter list name"
            ),
            input(
                {
                    type: "name",
                    class: "form-control",
                    id: "exampleFormControlInput1",
                    placeholder: "List name",
                    required: true
                }
            ),
            div({class: "invalid-feedback"}, "Please enter a name")
        ),
        div({class: "mb-3"},
            button({class: "btn btn-outline-dark", type: "submit"}, "Submit form")
        )
    )
}

export default CreateListHandler;