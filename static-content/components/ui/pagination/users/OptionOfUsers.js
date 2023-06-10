import {option} from "../../../dom/domTags.js";

/**
 * OptionOfUsers is a function that returns an option element representing a user.
 *
 * @param {object} user - The user object containing user information.
 *
 * @returns {HTMLElement} The option element representing a user.
 */
export async function OptionOfUsers(user) {
    return await option({"data-value": `${user.id}`}, `${user.email}`)
}