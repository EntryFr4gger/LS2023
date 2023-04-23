import router from "./routes/router.js";
import handlers from "./handlers/handlers.js";
import App from "./handlers/App.js";


window.addEventListener('load', loadHandler)
window.addEventListener("load", (event) => {
    console.log("page is fully loaded");
});
window.addEventListener('hashchange', loadHandler)

function createState(path) {
    return {
        path: path,
        queryParams : {},
        pathParams: {},
        bodyParams: {},
        token: 'Bearer 9f1e3d11-8c18-4cd7-93fc-985c4794cfd9'
    }
}

function loadHandler() {
    const state = createState(window.location.hash.replace("#", "/"))
    App(state)
        .then(render)
    /*

    router.addRouteHandler("home", handlers.getHome)
    router.addRouteHandler("boards/3", handlers.getBoard)
    router.addRouteHandler("boards/3/lists", handlers.getBoardLists)
    router.addDefaultNotFoundRouteHandler(() => window.location.hash = "home")

    hashChangeHandler()*/
}

export function render(element) {
    const mainContent = document.getElementById("mainContent");
    mainContent.replaceChildren(element);
}