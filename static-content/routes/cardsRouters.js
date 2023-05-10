import NotFoundPage from "../pages/shared/404Page.js";
import {Router} from "./router.js";
import DetailsCardHandler from "../components/handlers/cards/DetailsCardHandler.js";

const cardsRouters = Router()
cardsRouters.addRouteHandler('/:card_id', DetailsCardHandler);
cardsRouters.addDefaultNotFoundRouteHandler(NotFoundPage);

export default cardsRouters;