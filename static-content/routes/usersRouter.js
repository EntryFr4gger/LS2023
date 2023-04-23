import NotFoundPage from "../pages/shared/404Page.js";
import UserDetailsHandler from "../handlers/users/UserDetailsHandler.js";
import {Router} from "./router.js";
import UserBoardsHandler from "../handlers/users/UserBoardsHandler.js";

const userRouter = Router()
userRouter.addRouteHandler('/:user_id', UserDetailsHandler);
userRouter.addRouteHandler('/:user_id/boards', UserBoardsHandler);
userRouter.addDefaultNotFoundRouteHandler(NotFoundPage);

export default userRouter;