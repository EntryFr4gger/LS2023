import NotFoundPage from "../pages/shared/404Page.js";
import UserDetailsHandler from "../handlers/users/UserDetailsHandler.js";
import {Router} from "./router.js";

const cardsRouters = Router()
cardsRouters.addRouteHandler('/1', UserDetailsHandler);
cardsRouters.addDefaultNotFoundRouteHandler(NotFoundPage);

export default cardsRouters;