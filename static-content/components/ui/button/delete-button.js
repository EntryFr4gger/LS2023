import {form} from "../../dom/domTags.js";
import {dangerOutlineButton} from "./color-buttons.js";

export function DeleteButton(deleteFunc) {
    return form(
        {onSubmit: deleteFunc},
        dangerOutlineButton("Delete", "submit")
    )
}