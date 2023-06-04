import {a, div, hr, li, span, svg, ul, use,} from "../../components/dom/domTags.js";
import DeleteBoardHandler from "../../handlers/board/DeleteBoardHandler.js";
import OffCanvasCreate from "../../components/ui/off-canvas/off-canvas-create.js";
import CreateListHandler from "../../handlers/lists/CreateListHandler.js";
import {createRef} from "../../components/utils/create-ref.js";
import CreateInfiniteScroll from "../../components/ui/infinite-scroll/CreateInfiniteScroll.js";


function BoardDetailsPage(state, loadBoardDetails) {
    const ref = createRef()

    return div(
        /*h5(`${state.body["description"]}`),*/
        ul(
            {class: "list-inline d-flex"},
            div({
                    class: "d-flex flex-column flex-shrink-0 p-3 text-white bg-dark",
                    style: {width: "280px", "margin-left": "-30px"}
                },
                div(
                    svg({class: "bi me-2", width: "40", height: "32"}, use({xlink: "#bootstrap"})),
                    span({class: "fs-4"}, `${state.body["name"]}`)
                ),
                hr(),
                ul({class: "nav nav-pills flex-column mb-auto"},
                    li(
                        a({href: `/#boards/${state.body['id']}/users`, class: "nav-link text-white"},
                            svg({
                                class: "bi me-2 fas fa-users fa-xs",
                                width: "16",
                                height: "16"
                            }, use({xlink: "#speedometer2"})),
                            "Users in Board"
                        )
                    ),
                    li(
                        a({href: `#cards/${state.body["id"]}/archived`, class: "nav-link text-white"},
                            svg({
                                class: "bi me-2 fas fa-box",
                                width: "16",
                                height: "16",
                                style: {color: "#FFFFFF"}
                            }, use({xlink: "#table"})),
                            "Archived Cards"
                        )
                    ),
                    OffCanvasCreate("List Create", CreateListHandler(state)),
                ),
                hr(),
                DeleteBoardHandler(state),
            ),
            li(
                {class: "list-inline-item"},
                ul(
                    {class: "d-flex justify-content-center"},
                    div(
                        CreateInfiniteScroll(state, {
                                onLoadMore: loadBoardDetails,
                                resetRef: ref,
                                initialNumChildren: 16,
                                numChildren: 5,
                                overflowHeight: "650px"
                            },
                            "scroll-div d-flex justify-content-start"
                        )
                    )
                )
            )
        )
    )
}

export default BoardDetailsPage;