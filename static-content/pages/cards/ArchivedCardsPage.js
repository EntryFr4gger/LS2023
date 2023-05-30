import {a, div, h3, h5, img, li, ul} from "../../components/dom/domTags.js";
import {UnarchiveCard} from "../../components/ui/pagination/cards/UnarchiveCard.js";

function ArchivedCardsPage(state) {
    const car = state.body["cards"];
    const boardId = state.pathParams["board_id"]

    return div(
        {class: "d-flex justify-content-center"},
        div({class: "card", style: {width: "25rem"}},
            img({
                class: "card-img-top",
                src: "public/9.jpg",
                alt: "Card image cap",
                style: {"background-color": "#000000"}
            }),
            div({class: "card-body"},
                h3({class: "card-title"}, "Archived Cards"),
            ),
            ul({class: "list-inline solid"},
                ...car.map(currentCard =>
                    div(
                        {class: "d-flex solid"},
                        li({class: "list-inline-item p-3"}, h5(currentCard['name'])),
                        li(
                            {class: "list-inline-item ms-auto p-2"},
                            UnarchiveCard(state, currentCard)
                        )
                    )
                )
            ),
            div({class: "card-body"},
                a({class: "dropdown-item", href: `#boards/${boardId}`}, "Back to Board"),
            )
        )
    )
}

export default ArchivedCardsPage;