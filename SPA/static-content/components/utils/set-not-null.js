/**
 * Sets a parameter with a non-null value in the given params object.
 *
 * @param {string} name - The name of the parameter.
 * @param {*} value - The value of the parameter.
 * @param {object} params - The object to store the parameter.
 */
export function SetNotNull(name, value, params) {
    if (value !== undefined) params.set(name, value)
}
