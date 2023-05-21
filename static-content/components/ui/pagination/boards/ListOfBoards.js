import {a, div, h5, img, li, p} from "../../../dom/domTags.js";

export async function ListOfBoards(board) {
    return li(
        {class: "list-inline-item"},
        div(
            {class: "card border-dark mb-3", style: {width: "18rem"}},
            a(
                {href: `/#boards/${board.id}`, class: "card-a"},
                img({
                    src: `../../public/${Math.floor(Math.random() * 10)}.jpg`,
                    class: "card-img-top",
                    width: "286",
                    height: "150"
                }),
                div(
                    {class: "card-body text-dark"},
                    h5({class: "card-title"}, `${board.name}`),
                    p({class: "card-text"}, `${board.description}`),
                )
            )
        )
    )
}