import {div, h1, li, ul} from "../../components/dom/domTags.js";
import {buttonWithHref} from "../../components/ui/button/with-href.js";
import {getUser} from "../../components/utils/get-user.js";
import OffCanvasCreate from "../../components/ui/off-canvas/off-canvas-create.js";
import CreateBoardHandler from "../../components/handlers/board/CreateBoardHandler.js";
import {createRef} from "../../components/utils/create-ref.js";
import InfiniteScroll from "../../components/ui/infinite-scroll/InfiniteScroll.js";


async function BoardsPage(state, props) {

    const ref = createRef()

    const {loadBoards} = props

    return await div(
        h1({class: "d-flex"}, "My Boards"),
        ul(
            {class: "list-inline d-flex"},
            li(
                {class: "list-inline-item"},
                div(
                    {class: "btn-group-vertical", role: "group", "aria-label": "Vertical button group"},
                    buttonWithHref("UserDetails", `/#users/${getUser()}`),
                    OffCanvasCreate("Board Create", CreateBoardHandler())
                )
            ),
            li(
                {class: "list-inline-item"},
                ul(
                    {class: "d-flex justify-content-center"},
                    InfiniteScroll(state, {
                            onLoadMore: loadBoards,
                            resetRef: ref,
                            initialNumChildren: 16,
                            numChildren: 5,
                            overflowHeight: "820px"
                        },
                        "hide-scroll-div"
                    )
                )
            )
        )
    )
}


export default BoardsPage;