import router from "./routes/router.js";
import handlers from "./handlers/handlers.js";
import App from "./handlers/App.js";


window.addEventListener('load', loadHandler)
window.addEventListener("load", (event) => {
    console.log("page is fully loaded");
});
window.addEventListener('hashchange', loadHandler)

function loadHandler() {
    const path = window.location.hash.replace("#", "/")
    App(path)
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