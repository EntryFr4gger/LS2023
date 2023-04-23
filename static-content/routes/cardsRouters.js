import NotFoundPage from "../pages/404Page.js";
import UsersHandeler from "../handlers/users/UsersHandeler.js";
import {Router} from "./router.js";

const cardsRouters = Router()
cardsRouters.addRouteHandler('/1', UsersHandeler);
cardsRouters.addDefaultNotFoundRouteHandler(NotFoundPage);

export default cardsRouters;