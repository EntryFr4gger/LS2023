import {datalist, form, input} from "../../../dom/domTags.js";

/**
 * FormAddUserBoard is a function that returns a form for adding a user to a board.
 * It includes an input field for searching users, a datalist for displaying search options,
 * and a hidden input field for storing the selected user's ID.
 *
 * @param {function} createBoard - The function to be called on form submission.
 * @param {HTMLElement} datalists - The element representing the list of search options
 * .
 * @returns {Promise<HTMLElement>} The form adds a user board element.
 */
export async function FormAddUserBoard(createBoard, datalists) {
    return await form({class: "d-flex", role: "search", onSubmit: createBoard},
        input({
            class: "form-control",
            list: "datalistOptions",
            id: "exampleDataList",
            placeholder: "Type to search..."
        }),
        datalist(
            {id: "datalistOptions"},
            datalists
        ),
        input({
            id: "hidden-answer",
            type: "hidden",
            name: "user-id"
        }),
    )
}