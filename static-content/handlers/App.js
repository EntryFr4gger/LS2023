import {Router} from "../routes/router.js";
import usersRouter from "../routes/usersRouter.js";
import NavBar from "../pages/shared/NavBar.js";
import HomeHandeler from "./default/HomeHandeler.js";
import boardsRouter from "../routes/boardsRouters.js";
import listsRouter from "../routes/listsRouters.js";
import {br} from "../pages/components/dom/domTags.js";
import {div} from "../pages/components/dom/domTags.js";


const router = Router();

router.addRouteHandler("/", HomeHandeler);
router.addRouteHandler("/users", usersRouter);
router.addRouteHandler("/boards", boardsRouter);
router.addRouteHandler("/lists", listsRouter);
router.addDefaultNotFoundRouteHandler(() => window.location.hash = "")

async function App(state) {
    return div(
        await NavBar(state),
        br(),
        await router.handleRoute(state),
    )
}

export default App;