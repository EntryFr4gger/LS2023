import {button, form, svg, use} from "../../dom/domTags.js";

export function DeleteButton(deleteFunc) {
    return form(
        {onSubmit: deleteFunc},
        button({class: "nav-link text-dark", "data-bs-dismiss": "modal", "aria-label": "Close"},
            svg({class: "bi me-2 fa-solid fa-trash-can", width: "16", height: "16"}, use({xlink: "#people-circle"})),
            "Delete"
        )
    )
}