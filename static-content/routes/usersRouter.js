import NotFoundPage from "../pages/shared/404Page.js";
import DetailsUserHandler from "../components/handlers/user/DetailsUserHandler.js";
import {Router} from "./router.js";
import UserBoardsHandler from "../components/handlers/user/UserBoardsHandler.js";

const userRouter = Router()
userRouter.addRouteHandler('/:user_id', DetailsUserHandler);
userRouter.addRouteHandler('/:user_id/boards', UserBoardsHandler);
userRouter.addDefaultNotFoundRouteHandler(NotFoundPage);

export default userRouter;