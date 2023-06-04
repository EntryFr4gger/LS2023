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

router.addRouteHandler("/", HomeHandler);
router.addRouteHandler("/login", LoginHandler);
router.addRouteHandler("/register", RegisterHandler);
router.addRouteHandler("/users", usersRouter);
router.addRouteHandler("/boards", boardsRouter);
router.addRouteHandler("/lists", listsRouter);
router.addRouteHandler("/cards", cardsRouters);
router.addDefaultNotFoundRouteHandler(() => window.location.hash = "")

async function App(state) {
    return div(
        await NavBar(state),
        br(),
        await router.handleRoute(state),
    )
}

export default App;