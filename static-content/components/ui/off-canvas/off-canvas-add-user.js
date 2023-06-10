import {a, button, div, h5, label, svg, use} from "../../dom/domTags.js";

function OffCanvasAddUser(name, addUser) {
    return div(
        a(
            {
                class: "nav-link text-white",
                "data-bs-toggle": "offcanvas",
                href: "#offcanvas-add-user-board",
                role: "button",
                "aria-controls": "offcanvas-add-user-board"
            },
            svg({class: "bi me-2 fa-solid fa-list fa-lm", width: "16", height: "16"}, use({xlink: "#people-circle"})),
            name
        ),
        div(
            {
                class: "offcanvas offcanvas-start",
                tabindex: "-1",
                id: "offcanvas-add-user-board",
                "aria-labelledby": "offcanvas-add-user-board-Label"
            },
            div(
                {class: "offcanvas-header"},
                h5({class: "offcanvas-title", id: "offcanvas-add-user-board-Label"}, name),
                button({type: "button", class: "btn-close", "data-bs-dismiss": "offcanvas", "aria-label": "Close"})
            ),
            div({class: "offcanvas-body"},
                label(
                    {for: "exampleDataList", class: "form-label"},
                    "Datalist example"
                ),
                addUser,
                /*form({class: "d-flex", role: "search", onSubmit: addUser},
                    input({
                        class: "form-control",
                        list: "datalistOptions",
                        id: "exampleDataList",
                        placeholder: "Type to search..."
                    }),
                    datalist(
                        {id: "datalistOptions"},
                        users
                    )
                )*/
                /*form({class: "d-flex", role: "search", onSubmit: search},
                    input({
                        class: "form-control me-2",
                        id: "search-res",
                        type: "search",
                        placeholder: "Search Board",
                        "aria-label": "Search"
                    })
                )*/
            )
        )
    )
}

export default OffCanvasAddUser;