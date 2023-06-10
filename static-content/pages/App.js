import {Router} from "../routes/router.js";
import usersRouter from "../routes/usersRouter.js";
import NavBar from "./_shared/NavBar.js";
import HomeHandler from "../handlers/_default/HomeHandler.js";
import boardsRouter from "../routes/boardsRouters.js";
import listsRouter from "../routes/listsRouters.js";
import {br, div} from "../components/dom/domTags.js";
import cardsRouters from "../routes/cardsRouters.js";
import RegisterHandler from "../handlers/user/RegisterHandler.js";
import LoginHandler from "../handlers/user/LoginHandler.js";

const router = Router();

// Add route handlers for different routes
router.addRouteHandler("/", HomeHandler);
router.addRouteHandler("/login", LoginHandler);
router.addRouteHandler("/register", RegisterHandler);
router.addRouteHandler("/users", usersRouter);
router.addRouteHandler("/boards", boardsRouter);
router.addRouteHandler("/lists", listsRouter);
router.addRouteHandler("/cards", cardsRouters);

// Add a default not found route handler that redirects to the home page
router.addDefaultNotFoundRouteHandler(() => window.location.hash = "")

/**
 * App is an asynchronous function that generates the main application component.
 *
 * @param {Object} state - The state object.
 *
 * @returns {Promise<HTMLElement>} The main application component.
 */
async function App(state) {
    return div(
        await NavBar(),
        br(),
        await router.handleRoute(state),
    )
}

export default App;