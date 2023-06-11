import {button} from "../../dom/domTags.js";


/**
 * Outline Color Button
 */

// Generates a button with outline primary color
export function primaryOutlineButton(text, type = "button") {
    return button({type: type, class: "btn btn-outline-primary"}, text);
}

// Generates a button with outline secondary color
export function secondaryOutlineButton(text, type = "button") {
    return button({type: type, class: "btn btn-outline-secondary"}, text);
}

// Generates a button with outline success color
export function successOutlineButton(text, type = "button") {
    return button({type: type, class: "btn btn-outline-success"}, text);
}

// Generates a button with outline danger color
export function dangerOutlineButton(text, type = "button") {
    return button({type: type, class: "btn btn-outline-danger"}, text);
}

// Generates a button with outline warning color
export function warningOutlineButton(text, type = "button") {
    return button({type: type, class: "btn btn-outline-warning"}, text);
}

// Generates a button with outline info color
export function infoOutlineButton(text, type = "button") {
    return button({type: type, class: "btn btn-outline-info"}, text);
}

// Generates a button with outline light color
export function lightOutlineButton(text, type = "button") {
    return button({type: type, class: "btn btn-outline-light"}, text);
}

// Generates a button with outline dark color
export function darkOutlineButton(text, type = "button") {
    return button({type: type, class: "btn btn-outline-dark"}, text);
}

/**
 * Full Colors Button
 */

// Generates a button with primary color
export function primaryButton(text, type = "button") {
    return button({type: type, className: "btn btn-primary"}, text);
}

// Generates a button with secondary color
export function secondaryButton(text, type = "button") {
    return button({type: type, className: "btn btn-secondary"}, text);
}

// Generates a button with success color
export function successButton(text, type = "button") {
    return button({type: type, className: "btn btn-success"}, text);
}

// Generates a button with danger color
export function dangerButton(text, type = "button") {
    return button({type: type, className: "btn btn-danger"}, text);
}

// Generates a button with warning color
export function warningButton(text, type = "button") {
    return button({type: type, className: "btn btn-warning"}, text);
}

// Generates a button with info color
export function infoButton(text, type = "button") {
    return button({type: type, className: "btn btn-info"}, text);
}

// Generates a button with light color
export function lightButton(text, type = "button") {
    return button({type: type, className: "btn btn-light"}, text);
}

// Generates a button with dark color
export function darkButton(text, type = "button") {
    return button({type: type, className: "btn btn-dark"}, text);
}

// Generates a button with link style
export function linkButton(text, type = "button") {
    return button({type: type, className: "btn btn-link"}, text);
}