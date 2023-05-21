import NotFoundPage from "../pages/_shared/404Page.js";
import {Router} from "./router.js";
import DetailsCardHandler from "../handlers/cards/DetailsCardHandler.js";
import ArchivedCardHandler from "../handlers/cards/ArchivedCardHandler.js";

const cardsRouters = Router()
cardsRouters.addRouteHandler('/:card_id', DetailsCardHandler);
cardsRouters.addRouteHandler('/:board_id/archived', ArchivedCardHandler);
cardsRouters.addDefaultNotFoundRouteHandler(NotFoundPage);

export default cardsRouters;