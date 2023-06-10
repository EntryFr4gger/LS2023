import {datalist, form, input} from "../../../dom/domTags.js";


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