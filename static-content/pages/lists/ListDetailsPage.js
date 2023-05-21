import {div, h1, h5, li, table, th, tr, ul} from "../../components/dom/domTags.js";
import {buttonWithHref} from "../../components/ui/button/with-href.js";
import DeleteListHandler from "../../components/handlers/lists/DeleteListHandler.js";
import CreateCardHandler from "../../components/handlers/cards/CreateCardHandler.js";
import OffCanvasCreate from "../../components/ui/off-canvas/off-canvas-create.js";
import {createRef} from "../../components/utils/create-ref.js";
import {getUserToken} from "../../components/utils/get-token.js";
import InfinitScroll from "../../components/ui/infinite-scroll/InfiniteScroll.js";


function ListDetailsPage(state) {

    const items = ['id', 'name'];
    const car = state.body["cards"]["cards"];

    return div(
        h1("List Details:"),
        ul(
            ...items.map(item => li((`${item} : ${state.body[item]}`))),
            li(
                h5("Cards:"),
                ul(
                    ...car.map(currentCard =>
                        tr(
                            th(h5(currentCard['name'])),
                            th(buttonWithHref("Card Details", `/#cards/${currentCard['id']}`)
                            )
                        )
                    )
                    /*{class: "d-flex justify-content-center"},
                    InfinitScroll(state, {
                        onLoadMore: onLoadMoreSports,
                        resetRef: sportsResetRef,
                        initialNumChildren: 15,
                        numChildren: 5,
                        overflowHeight: "400px"
                    })*/
                )
            )
        ),
        buttonWithHref("Board Details", `#boards/${state.body["boardId"]}`),
        DeleteListHandler(state),
        OffCanvasCreate("Card Create", CreateCardHandler(state))
    )
}

export default ListDetailsPage;