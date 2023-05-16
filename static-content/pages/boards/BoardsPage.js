import {br, div, h1, h5, table, th, tr, ul} from "../../components/dom/domTags.js";
import {buttonWithHref} from "../../components/ui/button/with-href.js";
import {getUser} from "../../components/utils/get-user.js";
import OffCanvasCreate from "../../components/ui/off-canvas/off-canvas-create.js";
import CreateBoardHandler from "../../components/handlers/board/CreateBoardHandler.js";


async function BoardsPage(state) {
    let boards
    if (state.body !== undefined) {
        boards = state.body["boards"]
    } else {
        boards = JSON.parse(sessionStorage.getItem("boards"))["boards"]
        sessionStorage.removeItem("boards")
    }

    return div(
        h1("User Boards:"),
        ul(
            table(
                ...boards.map(board =>
                    tr(
                        th(h5(`${board["name"]}`)),
                        th(buttonWithHref("Board Details", `/#boards/${board["id"]}`))
                    )
                )
            )
        ),
        br(),
        buttonWithHref("Home", "/#"),
        buttonWithHref("UserDetails", `/#users/${getUser()}`),
        OffCanvasCreate("Board Create", CreateBoardHandler())
    )
}


export default BoardsPage;