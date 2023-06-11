import {a, button, div, h5, label, svg, use} from "../../dom/domTags.js";

/**
 * OffCanvasAddUser is a function that returns an offcanvas component for adding a user.
 * It includes a button that triggers the offcanvas and a form for adding the user.
 *
 * @param {string} name - The name of the user.
 * @param {HTMLElement} addUser - The element for adding a user.
 *
 * @returns {Promise<HTMLElement>} The offcanvas add a user element.
 */
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
            )
        )
    )
}

export default OffCanvasAddUser;