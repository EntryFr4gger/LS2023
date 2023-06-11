import {a} from "../../dom/domTags.js";
import {lightOutlineButton} from "./color-buttons.js";

/**
 * ButtonWithHref function generates a link button with the specified text, href, and padding.
 *
 * @param {string} text - The text content of the button.
 * @param {string} href - The URL to link to. Default is "/#".
 * @param {number} padding - The padding size of the button. Default is 1.
 *
 * @returns {Promise} - The generated link button element.
 */
export function buttonWithHref(text, href = "/#", padding = 1) {
    let classe;
    switch (padding) {
        case 1:
            classe = "p-1"
            break;
        case 2:
            classe = "p-2"
            break;
        case 3:
            classe = "p-3"
            break;
        case 4:
            classe = "p-4"
            break;
        case 5:
            classe = "p-5"
            break;
        default:
            classe = "p-0"
    }
    return a({href: href, class: padding}, lightOutlineButton(text))
}