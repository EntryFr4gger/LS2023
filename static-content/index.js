import router from "./routes/router.js";
import handlers from "./handlers/handlers.js";


window.addEventListener('load', loadHandler)
window.addEventListener('hashchange', hashChangeHandler)

function loadHandler(){

    router.addRouteHandler("home", handlers.getHome)
    router.addRouteHandler("boards/3", handlers.getBoard)
    router.addRouteHandler("boards/3/lists", handlers.getBoardLists)
    router.addDefaultNotFoundRouteHandler(() => window.location.hash = "home")

    hashChangeHandler()
}

function hashChangeHandler(){
    const path =  window.location.hash.replace("#", "")

    const mainContent = document.getElementById("mainContent")

    const handler = router.getRouteHandler(path)
    handler(mainContent)
}