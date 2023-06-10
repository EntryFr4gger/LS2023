/**
 * DisableAttribute is a function that sets the "disabled" attribute on a given element attribute.
 *
 * @param {HTMLElement} attribute - The attribute to be disabled.
 */
export function DisableAttribute(attribute) {
    return attribute.setAttribute("disabled", "");
}
