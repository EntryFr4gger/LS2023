import App from "./pages/App.js";
import NotFoundPage from "./pages/_shared/404Page.js";


window.addEventListener('load', loadHandler)
window.addEventListener("load", () => {
    console.log("page is fully loaded");
});
window.addEventListener('hashchange', loadHandler)

function createState(path) {
    return {
        path: path,
        queryParams: {},
        pathParams: {},
        bodyParams: {}
    }
}

function handleComponentError(state, error) {
    NotFoundPage(state, error).then(render);
    throw error
}

function loadHandler() {
    const state = createState(window.location.hash.replace("#", "/"))
    App(state)
        .then(render)
        .catch((error) => handleComponentError(state, error));
}

export function render(element) {
    const mainContent = document.getElementById("mainContent");
    mainContent.replaceChildren(element);
}