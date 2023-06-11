import {Router} from "./router.js";
import NotFoundPage from "../pages/_shared/404Page.js";
import cardsRouters from "./cardsRouters.js";
import DetailsListHandler from "../handlers/lists/DetailsListHandler.js";

const listsRouter = Router()

// Add route handlers for different list routes
listsRouter.addRouteHandler('/:list_id', DetailsListHandler);
cardsRouters.addDefaultNotFoundRouteHandler(NotFoundPage);

export default listsRouter;