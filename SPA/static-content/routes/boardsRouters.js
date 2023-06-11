import NotFoundPage from "../pages/_shared/404Page.js";
import {Router} from "./router.js";
import DetailsBoardHandler from "../handlers/board/DetailsBoardHandler.js";
import BoardUsersHandler from "../handlers/board/BoardUsersHandler.js";
import SearchBoardsHandler from "../handlers/board/SearchBoardsHandler.js";

const boardsRouter = Router()

// Add route handlers for different board routes
boardsRouter.addRouteHandler('/search', SearchBoardsHandler);
boardsRouter.addRouteHandler('/:board_id', DetailsBoardHandler);
boardsRouter.addRouteHandler('/:board_id/users', BoardUsersHandler);
boardsRouter.addDefaultNotFoundRouteHandler(NotFoundPage);

export default boardsRouter;