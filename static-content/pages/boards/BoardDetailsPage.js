import {div, h1, h4, h5, li, th, tr, ul} from "../../components/dom/domTags.js";
import {buttonWithHref} from "../../components/ui/button/with-href.js";
import DeleteBoardHandler from "../../components/handlers/board/DeleteBoardHandler.js";
import OffCanvasCreate from "../../components/ui/off-canvas/off-canvas-create.js";
import CreateListHandler from "../../components/handlers/lists/CreateListHandler.js";


function BoardDetailsPage(state) {
    const items = ['id', 'name', 'description'];
    const lis = state.body["lists"]["lists"];


    return div(
        h1("Boards:"),
        ul(
            ...items.map(item => li(`${item} : ${state.body[item]}`)),
            li(
                h4("Lists:"),
                ul(
                    ...lis.map(currentList =>
                        tr(
                            th(h5(currentList['name'])),
                            th(buttonWithHref("List Details", `/#lists/${currentList['id']}`)
                            )
                        )
                    )
                )
            )
        ),
        buttonWithHref("Users in Board", `/#boards/${state.body['id']}/users`),
        DeleteBoardHandler(state),
        OffCanvasCreate("List Create", CreateListHandler(state))
    )
}

export default BoardDetailsPage;