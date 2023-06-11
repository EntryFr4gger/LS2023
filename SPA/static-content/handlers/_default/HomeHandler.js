import HomePage from "../../pages/_shared/HomePage.js";

/**
 * HomeHandler is an asynchronous function that handles the home page.
 *
 * @returns {Promise} A promise that resolves to the rendered home page.
 */
async function HomeHandler() {
    return await HomePage()
}

export default HomeHandler;