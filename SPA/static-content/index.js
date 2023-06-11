import App from "./pages/App.js";
import NotFoundPage from "./pages/_shared/404Page.js";

// Event listener for when the window finishes loading
window.addEventListener('load', loadHandler)
window.addEventListener("load", () => {
    console.log("page is fully loaded");
});

// Event listener for hash changes
window.addEventListener('hashchange', loadHandler)

// Create a state object based on the provided path
function createState(path) {
    return {
        path: path,
        queryParams: {},
        pathParams: {},
        bodyParams: {}
    }
}

// Handle errors thrown by components and display the NotFoundPage
function handleComponentError(state, error) {
    NotFoundPage(state, error).then(render);
    throw error
}

// Handler for the window load event
function loadHandler() {
    const state = createState(window.location.hash.replace("#", "/"))
    App(state)
        .then(render)
        .catch((error) => handleComponentError(state, error));
}

// Render the provided element by replacing the content of the mainContent element in the DOM
export function render(element) {
    const mainContent = document.getElementById("mainContent");
    mainContent.replaceChildren(element);
}