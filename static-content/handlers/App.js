import {Router} from "../routes/router.js";
import HomePage from "../pages/shared/HomePage.js";
import usersRouter from "../routes/usersRouter.js";
import NotFoundPage from "../pages/shared/404Page.js";
import NavBar from "../pages/shared/NavBar.js";
import HomeHandeler from "./default/HomeHandeler.js";
import boardsRouter from "../routes/boardsRouters.js";
import listsRouter from "../routes/listsRouters.js";
import cardsRouters from "../routes/cardsRouters.js";

const router = Router();

router.addRouteHandler("/", HomeHandeler);
router.addRouteHandler("/users", usersRouter);
router.addRouteHandler("/boards", boardsRouter);
router.addRouteHandler("/lists", listsRouter);
router.addRouteHandler("/cards", cardsRouters);
router.addDefaultNotFoundRouteHandler(() => window.location.hash = "")

async function App(state) {
    const div = document.createElement("div");
    div.appendChild(await NavBar(state));
    div.appendChild(document.createElement("br"));
    const t = await router.handleRoute(state)
    div.appendChild(t);
    return div;
}

export default App;