import {div, h1, h5, li, ul,} from "../../components/dom/domTags.js";
import {buttonWithHref} from "../../components/ui/button/with-href.js";
import DeleteBoardHandler from "../../handlers/board/DeleteBoardHandler.js";
import OffCanvasCreate from "../../components/ui/off-canvas/off-canvas-create.js";
import CreateListHandler from "../../handlers/lists/CreateListHandler.js";
import {createRef} from "../../components/utils/create-ref.js";
import InfiniteScroll from "../../components/ui/infinite-scroll/InfiniteScroll.js";


function BoardDetailsPage(state, loadBoardDetails) {
    const ref = createRef()

    return div(
        h1(`${state.body["name"]}`),
        h5(`${state.body["description"]}`),
        ul(
            {class: "list-inline  d-flex"},
            li(
                {class: "list-inline-item"},
                div(
                    {class: "btn-group-vertical", role: "group", "aria-label": "Vertical button group"},
                    buttonWithHref("Users in Board", `/#boards/${state.body['id']}/users`),
                    DeleteBoardHandler(state),
                    OffCanvasCreate("List Create", CreateListHandler(state)),
                    buttonWithHref("Archived Cards", `#cards/${state.body["id"]}/archived`)
                )
            ),
            li(
                {class: "list-inline-item"},
                ul(
                    {class: "d-flex justify-content-center"},
                    div(
                        InfiniteScroll(state, {
                                onLoadMore: loadBoardDetails,
                                resetRef: ref,
                                initialNumChildren: 16,
                                numChildren: 5,
                                overflowHeight: "650px"
                            },
                            "scroll-div"
                        )
                    )
                )
            )
        )
    )
}

export default BoardDetailsPage;