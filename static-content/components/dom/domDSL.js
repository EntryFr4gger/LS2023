/**
 Creates an HTML element with the specified tag, attributes, and children.

 @param {string} tag - The HTML element tag.
 @param {Object | Promise<HTMLElement> | HTMLElement | string} [attributes] -
 The attributes of the element or a single child element.
 @param {Promise<HTMLElement> | HTMLElement | string} [children] - The children elements.

 @returns {Promise<HTMLElement>} - A promise that resolves to the created HTML element.
 */
export async function createElement(tag, attributes, ...children) {
    const element = document.createElement(tag);

    attributes = await attributes;

    if (isElement(attributes) || typeof attributes === "string") appendChild(element, attributes);
    else if (attributes != null && typeof attributes === "object") setAttributes(element, attributes);
    else if (attributes != null)
        throw new DOMException("The attributes provided for createElement are invalid.");

    for (let child of children) {
        child = await child;

        if (child != null && (isElement(child) || typeof child === "string")) appendChild(element, child);
        else if (child != null)
            throw new DOMException(`The provided child: ${child}, is invalid for the element: ${element}.`);
    }

    return element;
}

/**
 Appends a child to the given HTML element.

 @param {HTMLElement} element - The parent element to which the child will be appended.
 @param {string | HTMLElement} child - The child element to be appended, can be a string or an HTMLElement.

 @return {void}
 */
function appendChild(element, child) {
    if (typeof child === "string")
        element.appendChild(document.createTextNode(child));
    else
        element.appendChild(child);
}

/**
 Sets the attributes of an HTML element.

 @param {HTMLElement} element - The HTML element to set the attributes for.
 @param {Object} attributes - An object containing the attribute-value pairs.

 @return {void}
 */
function setAttributes(element, attributes) {
    for (const attribute in attributes) {
        const value = attributes[attribute];
        if (value === null || attribute === "ref") continue;

        if (attribute.startsWith("on")) {
            element.addEventListener(attribute.slice(2).toLowerCase(), value);
        } else if (attribute === "style") {
            for (const style in value) {
                element.style[style] = value[style];
            }
        } else element.setAttribute(attribute, value);
    }
}

/**
 * Checks if an object is a DOM element.
 *
 * @param {any} obj - The object to check
 *
 * @returns {boolean} - Returns true if the object is a DOM element, false otherwise
 */
function isElement(obj) {
    return obj instanceof Element || (obj !== null && typeof obj === "object" && obj.nodeType === 1 && typeof obj.nodeName === "string");
}
