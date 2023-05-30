import {button, div, form} from "../../dom/domTags.js";

export function DeleteButtonHover(deleteFunc) {
    return form(
        {onSubmit: deleteFunc},
        div(
            {class: "hover-btn"},
            button({class: "btn-close", "data-dismiss": "alert"},)
        )
    )
}