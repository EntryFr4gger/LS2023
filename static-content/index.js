import App from "./pages/App.js";
import NotFoundPage from "./pages/shared/404Page.js";
import {getUserToken} from "./components/utils/get-token.js";


window.addEventListener('load', loadHandler)
window.addEventListener("load", () => {
    console.log("page is fully loaded");
});
window.addEventListener('hashchange', loadHandler)

function createState(path) {
    if (!getUserToken()) localStorage.setItem("userToken", 'Bearer 9f1e3d11-8c18-4cd7-93fc-985c4794cfd9')
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