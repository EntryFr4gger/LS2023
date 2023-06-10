import {br, div, h1, p} from "../../components/dom/domTags.js";
import NavBar from "./NavBar.js";

/**
 * NotFoundPage is an asynchronous function that displays an error message on the page.
 *
 * @param {Object} state - The state object containing the current state of the application.
 * @param {string} error - The error message to display.
 *
 * @returns {Promise<HTMLElement>} The not found page component.
 */
async function NotFoundPage(state, error) {
    return div(
        await NavBar(),
        br(),
        h1("Error"),
        br(),
        p(error)
    )
}

export default NotFoundPage;