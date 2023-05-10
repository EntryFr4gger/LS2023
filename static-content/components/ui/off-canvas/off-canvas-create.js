import {a, button, div, h5} from "../../dom/domTags.js";

function OffCanvasCreate(createName, func) {
    return div(
        a(
            {
                class: "btn btn-outline-light",
                "data-bs-toggle": "offcanvas",
                href: "#offcanvasExample",
                role: "button",
                "aria-controls": "offcanvasExample"
            },
            createName
        ),
        div(
            {
                class: "offcanvas offcanvas-start",
                tabindex: "-1",
                id: "offcanvasExample",
                "aria-labelledby": "offcanvasExampleLabel"
            },
            div(
                {class: "offcanvas-header"},
                h5({class: "offcanvas-title", id: "offcanvasExampleLabel"}, createName),
                button({type: "button", class: "btn-close", "data-bs-dismiss": "offcanvas", "aria-label": "Close"})
            ),
            div({class: "offcanvas-body"},
                func,
            )
        )
    )
}

export default OffCanvasCreate;