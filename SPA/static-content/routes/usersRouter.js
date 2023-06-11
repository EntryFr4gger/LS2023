import NotFoundPage from "../pages/_shared/404Page.js";
import DetailsUserHandler from "../handlers/user/DetailsUserHandler.js";
import {Router} from "./router.js";
import UserBoardsHandler from "../handlers/user/UserBoardsHandler.js";

const userRouter = Router()

// Add route handlers for different user routes
userRouter.addRouteHandler('/:user_id', DetailsUserHandler);
userRouter.addRouteHandler('/:user_id/boards', UserBoardsHandler);
userRouter.addDefaultNotFoundRouteHandler(NotFoundPage);

export default userRouter;