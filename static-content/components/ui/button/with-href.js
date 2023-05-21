import {a, br} from "../../dom/domTags.js";
import {lightOutlineButton} from "./color-buttons.js";

export function buttonWithHref(text, href = "/#", padding = 1) {
    switch (padding) {
        case 1:
            padding = "p-1"
            break;
        case 2:
            padding = "p-2"
            break;
        case 3:
            padding = "p-3"
            break;
        case 4:
            padding = "p-4"
            break;
        case 5:
            padding = "p-5"
            break;
        default:
            padding = "p-0"
    }
    return a({href: href, class: padding}, lightOutlineButton(text))
}