import {createElement} from "./domDSL.js";


/**
 * Creates a 'li' HTML element.
 *
 * @param {Object | Promise<HTMLElement> | HTMLElement | string} [attributes] element attributes or an element child
 * @param {Promise<HTMLElement> | HTMLElement | string} [children] element children
 *
 * @returns Promise<HTMLElement>
 */
export function li(attributes, ...children) {
    return createElement("li", attributes, ...children);
}

/**
 * Creates a 'ul' HTML element.
 *
 * @param {Object | Promise<HTMLElement> | HTMLElement | string} [attributes] element attributes or an element child
 * @param {Promise<HTMLElement> | HTMLElement | string} [children] element children
 *
 * @returns Promise<HTMLElement>
 */
export function ul(attributes, ...children) {
    return createElement("ul", attributes, ...children);
}

/**
 * Creates a 'br' HTML element.
 *
 * @param {Object | Promise<HTMLElement> | HTMLElement | string} [attributes] element attributes or an element child
 * @param {Promise<HTMLElement> | HTMLElement | string} [children] element children
 *
 * @returns Promise<HTMLElement>
 */
export function br(attributes, ...children) {
    return createElement("br", attributes, ...children);
}

/**
 * Creates a 'h1' HTML element.
 *
 * @param {Object | Promise<HTMLElement> | HTMLElement | string} [attributes] element attributes or an element child
 * @param {Promise<HTMLElement> | HTMLElement | string} [children] element children
 *
 * @returns Promise<HTMLElement>
 */
export function h1(attributes, ...children) {
    return createElement("h1", attributes, ...children);
}

/**
 * Creates a 'h2' HTML element.
 *
 * @param {Object | Promise<HTMLElement> | HTMLElement | string} [attributes] element attributes or an element child
 * @param {Promise<HTMLElement> | HTMLElement | string} [children] element children
 *
 * @returns Promise<HTMLElement>
 */
export function h2(attributes, ...children) {
    return createElement("h2", attributes, ...children);
}

/**
 * Creates a 'h3' HTML element.
 *
 * @param {Object | Promise<HTMLElement> | HTMLElement | string} [attributes] element attributes or an element child
 * @param {Promise<HTMLElement> | HTMLElement | string} [children] element children
 *
 * @returns Promise<HTMLElement>
 */
export function h3(attributes, ...children) {
    return createElement("h3", attributes, ...children);
}

/**
 * Creates a 'button' HTML element.
 *
 * @param {Object | Promise<HTMLElement> | HTMLElement | string} [attributes] element attributes or an element child
 * @param {Promise<HTMLElement> | HTMLElement | string} [children] element children
 *
 * @returns Promise<HTMLElement>
 */
export function button(attributes, ...children) {
    return createElement("button", attributes, ...children);
}

/**
 * Creates a 'div' HTML element.
 *
 * @param {Object | Promise<HTMLElement> | HTMLElement | string} [attributes] element attributes or an element child
 * @param {Promise<HTMLElement> | HTMLElement | string} [children] element children
 *
 * @returns Promise<HTMLElement>
 */
export function div(attributes, ...children) {
    return createElement("div", attributes, ...children);
}

/**
 * Creates a 'p' HTML element.
 *
 * @param {Object | Promise<HTMLElement> | HTMLElement | string} [attributes] element attributes or an element child
 * @param {Promise<HTMLElement> | HTMLElement | string} [children] element children
 *
 * @returns Promise<HTMLElement>
 */
export function p(attributes, ...children) {
    return createElement("p", attributes, ...children);
}


