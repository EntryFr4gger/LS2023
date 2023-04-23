import NotFoundPage from "../pages/404Page.js";
import UsersHandeler from "../handlers/users/UsersHandeler.js";
import {Router} from "./router.js";

const userRouter = Router()
userRouter.addRouteHandler('/:id', UsersHandeler);
userRouter.addDefaultNotFoundRouteHandler(NotFoundPage);

export default userRouter;