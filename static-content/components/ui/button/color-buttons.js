import {button} from "../../dom/domTags.js";


/**
 * Outilne Color Button
 * */
export function primaryOutlineButton(text, type = "button") {
    return button({type: type, class: "btn btn-outline-primary"}, text)
}

export function secondaryOutlineButton(text, type = "button") {
    return button({type: type, class: "btn btn-outline-secondary"}, text)
}

export function successOutlineButton(text, type = "button") {
    return button({type: type, class: "btn btn-outline-success"}, text)
}

export function dangerOutlineButton(text, type = "button") {
    return button({type: type, class: "btn btn-outline-danger"}, text)
}

export function warningOutlineButton(text, type = "button") {
    return button({type: type, class: "btn btn-outline-warning"}, text)
}

export function infoOutlineButton(text, type = "button") {
    return button({type: type, class: "btn btn-outline-info"}, text)
}

export function lightOutlineButton(text, type = "button") {
    return button({type: type, class: "btn btn-outline-light"}, text)
}

export function darkOutlineButton(text, type = "button") {
    return button({type: type, class: "btn btn-outline-dark"}, text)
}


/**
 * Full Colors Button
 * */
export function primaryButton(text, type = "button") {
    return button({type: type, className: "btn btn-primary"}, text)
}

export function secondaryButton(text, type = "button") {
    return button({type: type, className: "btn btn-secondary"}, text)
}

export function successButton(text, type = "button") {
    return button({type: type, className: "btn btn-success"}, text)
}

export function dangerButton(text, type = "button") {
    return button({type: type, className: "btn btn-danger"}, text)
}

export function warningButton(text, type = "button") {
    return button({type: type, className: "btn btn-warning"}, text)
}

export function infoButton(text, type = "button") {
    return button({type: type, className: "btn btn-info"}, text)
}

export function lightButton(text, type = "button") {
    return button({type: type, className: "btn btn-light"}, text)
}

export function darkButton(text, type = "button") {
    return button({type: type, className: "btn btn-dark"}, text)
}

export function linkButton(text, type = "button") {
    return button({type: type, className: "btn btn-link"}, text)
}