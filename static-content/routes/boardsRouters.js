import NotFoundPage from "../pages/_shared/404Page.js";
import {Router} from "./router.js";
import DetailsBoardHandler from "../components/handlers/board/DetailsBoardHandler.js";
import BoardUsersHandler from "../components/handlers/board/BoardUsersHandler.js";
import SearchBoardsHandler from "../components/handlers/board/SearchBoardsHandler.js";

const boardsRouter = Router()
boardsRouter.addRouteHandler('/search', SearchBoardsHandler);
boardsRouter.addRouteHandler('/:board_id', DetailsBoardHandler);
boardsRouter.addRouteHandler('/:board_id/users', BoardUsersHandler);
boardsRouter.addDefaultNotFoundRouteHandler(NotFoundPage);

export default boardsRouter;