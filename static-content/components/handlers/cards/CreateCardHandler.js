import {changeHashLocation} from "../../utils/change-hash-location.js";
import {button, div, form, input, label, textarea} from "../../dom/domTags.js";
import {CreateCardFetch} from "../../api/fetch/cards/CreateCardFetch.js";

function CreateCardHandler(state) {

    async function createCard(event) {
        event.preventDefault()
        const boardId = state.body["boardId"]
        const listId = state.pathParams["list_id"]
        const name = document.getElementById("exampleFormControlInput1").value
        const description = document.getElementById("validationTextarea").value

        const response =
            await CreateCardFetch(name, description, boardId, listId)

        const cardId = await response.json()

        changeHashLocation(`#cards/${cardId.id}`)
    }

    return form({class: "was-validated", onSubmit: createCard},
        div(
            {class: "mb-3"},
            label(
                {for: "exampleFormControlInput1", class: "form-label"},
                "Enter card name"
            ),
            input(
                {
                    type: "name",
                    class: "form-control",
                    id: "exampleFormControlInput1",
                    placeholder: "Card name",
                    required: true
                }
            ),
            div({class: "invalid-feedback"}, "Please enter a name")
        ),
        div({class: "mb-3"},
            label({for: "validationTextarea", class: "form-label"}, "Description"),
            textarea({
                class: "form-control",
                id: "validationTextarea",
                placeholder: "Required card description",
                maxLength: "1000",
                required: true
            }),
            div({class: "invalid-feedback"}, "Please enter a message for card description.")
        ),
        div({class: "mb-3"},
            button({class: "btn btn-outline-dark", type: "submit"}, "Submit form")
        )
    )
}

export default CreateCardHandler;