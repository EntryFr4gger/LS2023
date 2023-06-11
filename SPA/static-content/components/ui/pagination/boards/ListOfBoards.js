import {a, div, h5, img, li, p} from "../../../dom/domTags.js";

/**
 * ListOfBoards is a function that returns a list item representing a board.
 * It includes a card with the board's name, description, and an image.
 *
 * @param {object} board - The board object containing board information.
 *
 * @returns {HTMLElement} The list item representing the board.
 */
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