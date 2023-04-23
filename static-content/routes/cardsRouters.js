import NotFoundPage from "../pages/shared/404Page.js";
import {Router} from "./router.js";
import CardDetailsHandler from "../handlers/cards/CardDetailsHandler.js";

const cardsRouters = Router()
cardsRouters.addRouteHandler('/:card_id', CardDetailsHandler);
cardsRouters.addDefaultNotFoundRouteHandler(NotFoundPage);

export default cardsRouters;