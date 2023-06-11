import {a, br, div, hr, li, span, svg, ul, use,} from "../../components/dom/domTags.js";
import DeleteBoardHandler from "../../handlers/board/DeleteBoardHandler.js";
import OffCanvasCreate from "../../components/ui/off-canvas/off-canvas-create.js";
import CreateListHandler from "../../handlers/lists/CreateListHandler.js";
import {reference} from "../../components/utils/reference.js";
import CreateInfiniteScroll from "../../components/ui/infinite-scroll/CreateInfiniteScroll.js";
import OffCanvasAddUser from "../../components/ui/off-canvas/off-canvas-add-user.js";
import AddUserToBoardHandler from "../../handlers/board/AddUserToBoardHandler.js";

/**
 * BoardDetailsPage is an asynchronous function that generates the board details page component.
 *
 * @param {Object} state - The state object.
 * @param {Function} loadBoardDetails - The function to load board details.
 *
 * @returns {Promise<HTMLElement>} The board details a page component.
 */
function BoardDetailsPage(state, loadBoardDetails) {
    const ref = reference()

    return div(
        ul(
            {class: "list-inline d-flex"},
            div({
                    class: "d-flex flex-column flex-shrink-0 p-3 text-white bg-dark",
                    style: {width: "280px", "margin-left": "-30px"}
                },
                div(
                    svg({class: "bi me-2", width: "40", height: "32"}, use({xlink: "#bootstrap"})),
                    span({class: "fs-4"}, `${state.body["name"]}`),
                    br(),
                    span({style: {"padding-left": "7%"}}, `${state.body["description"]}`)
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
                    OffCanvasAddUser("Add User To Board", AddUserToBoardHandler(state))
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