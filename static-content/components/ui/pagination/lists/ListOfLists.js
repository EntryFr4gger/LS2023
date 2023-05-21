import {a, div, h4, h5, img, li, p, script, span, ul} from "../../../dom/domTags.js";
import CreateCardHandler from "../../../handlers/cards/CreateCardHandler.js";

export async function ListOfLists(list, cards) {
    return li(
        {class: "list-inline-item"},
        div(
            {class: "card", style: {width: "18rem"}},
            div(
                {class: "card-header"},
                a(
                    {href: `/#lists/${list.id}`, style:{"text-decoration":"none", color:"#000000"}},
                    h5({class: "card-title"}, `${list.name}`)
                )
            ),
            ul(
                {class: "sortable-list list-group list-group-flush"},
                ...cards.map(card =>
                    a(
                        {href: `/#cards/${card['id']}`, style: {"text-decoration": "none"}},
                        li({class: "list-group-item"}, `${card.name}`),
                    )
                )
            ),
            CreateCardHandler(undefined, list.boardId, list.id))
    )
}

/*
li(
                        { class: "item", draggable:"true"},
                        div(
                            {class:"details"},
                            span({style: {color: "black"}},`${card.name}`),
                        )
                    )

//script({src:"../../js.js"}),

div(
                { class: "col-md-6 col-12 mb-md-0 mb-4" },
                h5("Pending Tasks"),
                ul(
                    { class: "sortable-list"},
                    li(
                        { class: "item", draggable:"true"},
                        div(
                            {class:"details"},
                            span({style: {color: "black"}},"1"),
                        )
                    ),
                    li(
                        { class: "item", draggable:"true"},
                        div(
                            {class:"details"},
                            span({style: {color: "black"}},"2"),
                        )
                    ),
                    li(
                        { class: "item", draggable:"true"},
                        div(
                            {class:"details"},
                            span({style: {color: "black"}},"3")
                        )
                    ),
                    li(
                        { class: "item", draggable:"true"},
                        div(
                            {class:"details"},
                            span({style: {color: "black"}},"4")
                        )
                    ),
                    li(
                        { class: "item", draggable:"true"},
                        div(
                            {class:"details"},
                            span({style: {color: "black"}},"5")
                        )
                    )
                ),
                script({src:"../../js.js"})
            )
* */