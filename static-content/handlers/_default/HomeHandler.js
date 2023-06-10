import HomePage from "../../pages/_shared/HomePage.js";

/**
 * HomeHandler is an asynchronous function that handles the home page.
 *
 * @param {object} state - The state object containing relevant information for the home page.
 * @returns {Promise} A promise that resolves to the rendered home page.
 */
async function HomeHandler(state) {
    return await HomePage(state)
}

export default HomeHandler;