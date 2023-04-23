import NotFoundPage from "../pages/shared/404Page.js";
import {Router} from "./router.js";
import BoardDetailsHandler from "../handlers/boards/BoardDetailsHandler.js";
import BoardUsersHandler from "../handlers/boards/BoardUsersHandler.js";

const boardsRouter = Router()
boardsRouter.addRouteHandler('/:board_id', BoardDetailsHandler);
boardsRouter.addRouteHandler('/:board_id/users', BoardUsersHandler);
boardsRouter.addDefaultNotFoundRouteHandler(NotFoundPage);

export default boardsRouter;