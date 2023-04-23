import NotFoundPage from "../pages/shared/404Page.js";
import UserDetailsHandler from "../handlers/users/UserDetailsHandler.js";
import {Router} from "./router.js";

const listsRouter = Router()
listsRouter.addRouteHandler('/:id', UserDetailsHandler);

export default listsRouter;