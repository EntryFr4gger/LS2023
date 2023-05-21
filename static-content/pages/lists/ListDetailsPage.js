import {br, div, h1, h2, h5, li, table, th, tr, ul} from "../../components/dom/domTags.js";
import {buttonWithHref} from "../../components/ui/button/with-href.js";
import DeleteListHandler from "../../components/handlers/lists/DeleteListHandler.js";
import CreateCardHandler from "../../components/handlers/cards/CreateCardHandler.js";
import OffCanvasCreate from "../../components/ui/off-canvas/off-canvas-create.js";
import {createRef} from "../../components/utils/create-ref.js";
import {getUserToken} from "../../components/utils/get-token.js";
import InfinitScroll from "../../components/ui/infinite-scroll/InfiniteScroll.js";
import {darkButton, lightOutlineButton} from "../../components/ui/button/color-buttons.js";


function ListDetailsPage(state) {
    const car = state.body["cards"]["cards"];

    return div(
        h1("List Details"),
        br(),
        h2(`${state.body["name"]}`),
        ul(
            li(
                h5("Cards"),
                ul(
                    ...car.map(currentCard =>
                        li({class:"dropdown-item"},buttonWithHref(currentCard['name'], `/#cards/${currentCard['id']}`))
                    )
                )
            )
        ),
        buttonWithHref("Board Details", `#boards/${state.body["boardId"]}`),
        DeleteListHandler(state),
        darkButton(CreateCardHandler(state))
    )
}

export default ListDetailsPage;