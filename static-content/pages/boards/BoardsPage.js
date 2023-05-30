import {a, div, hr, li, span, svg, ul, use} from "../../components/dom/domTags.js";
import {getUser} from "../../components/utils/get-user.js";
import OffCanvasCreate from "../../components/ui/off-canvas/off-canvas-create.js";
import CreateBoardHandler from "../../handlers/board/CreateBoardHandler.js";
import {createRef} from "../../components/utils/create-ref.js";
import InfiniteScroll from "../../components/ui/infinite-scroll/InfiniteScroll.js";


async function BoardsPage(state, loadBoards) {
    const ref = createRef()

    return await div(
        ul(
            {class: "list-inline  d-flex"},
            div({
                    class: "d-flex flex-column flex-shrink-0 p-3 text-white bg-dark",
                    style: {width: "280px", "margin-left": "-30px"}
                },
                div(
                    svg({class: "bi me-2", width: "40", height: "32"}, use({xlink: "#bootstrap"})),
                    span({class: "fs-4"}, "My Boards")
                ),
                hr(),
                ul({class: "nav nav-pills flex-column mb-auto"},
                    li(
                        a({href: `/#users/${getUser()}`, class: "nav-link text-white"},
                            svg({
                                class: "bi me-2 far fa-user",
                                width: "16",
                                height: "16"
                            }, use({xlink: "#speedometer2"})),
                            "User Details"
                        )
                    ),
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
                            overflowHeight: "900px"
                        },
                        "hide-scroll-div"
                    )
                )
            )
        )
    )
}


export default BoardsPage;