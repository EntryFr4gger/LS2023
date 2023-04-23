import NotFoundPage from "../pages/404Page.js";
import UsersHandeler from "../handlers/users/UsersHandeler.js";
import {Router} from "./router.js";

const listsRouters = Router()
listsRouters.addRouteHandler('/:id', UsersHandeler);
listsRouters.addDefaultNotFoundRouteHandler(NotFoundPage);

export default listsRouters;