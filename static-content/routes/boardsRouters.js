import NotFoundPage from "../pages/404Page.js";
import UsersHandeler from "../handlers/users/UsersHandeler.js";
import {Router} from "./router.js";

const boardsRouters = Router()
boardsRouters.addRouteHandler('/1', UsersHandeler);
boardsRouters.addDefaultNotFoundRouteHandler(NotFoundPage);

export default boardsRouters;