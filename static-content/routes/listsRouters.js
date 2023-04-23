import {Router} from "./router.js";
import ListDetailsHandler from "../handlers/lists/ListDetailsHandler.js";
import NotFoundPage from "../pages/shared/404Page.js";
import cardsRouters from "./cardsRouters.js";

const listsRouter = Router()
listsRouter.addRouteHandler('/:list_id', ListDetailsHandler);
cardsRouters.addDefaultNotFoundRouteHandler(NotFoundPage);

export default listsRouter;