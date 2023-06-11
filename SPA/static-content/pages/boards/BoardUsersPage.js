import {a, div, h3, img, li, ul} from "../../components/dom/domTags.js";

/**
 * BoardUsersPage is an asynchronous function that generates the board users page component.
 *
 * @param {Object} state - The state object.
 * @returns {HTMLElement} The board users page component.
 */
async function BoardUsersPage(state) {
    const users = state.body['users']

    return div(
        {class: "d-flex justify-content-center"},
        div({class: "card", style: {width: "25rem"}},
            img({
                class: "card-img-top",
                src: "public/5.jpg",
                alt: "Card image cap",
                style: {"background-color": "#000000"}
            }),
            div({class: "card-body"},
                h3({class: "card-title"}, "User on Board"),
            ),
            ul({class: "list-inline solid", style: {overflow: "scroll"}},
                ...users.map(user =>
                    li(
                        {class: "p-3 solid"},
                        img({
                            src: `public/${Math.floor(Math.random() * 10)}.jpg`,
                            alt: "",
                            width: "28",
                            height: "28",
                            class: "rounded-circle me-2"
                        }),
                        `${user[`name`]}`
                    )
                )
            ),
            div({class: "card-body"},
                a({class: "dropdown-item", href: `#boards/${state.pathParams["board_id"]}`}, "Back to Board"),
            )
        )
    )
}


export default BoardUsersPage;