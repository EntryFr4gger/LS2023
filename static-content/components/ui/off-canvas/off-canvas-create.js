import {a, button, div, h5, svg, use} from "../../dom/domTags.js";

function OffCanvasCreate(createName, func) {
    return div(
        a(
            {
                class: "nav-link text-white",
                "data-bs-toggle": "offcanvas",
                href: "#offcanvasExample",
                role: "button",
                "aria-controls": "offcanvasExample"
            },
            svg({class: "bi me-2 fa-solid fa-list fa-lm", width: "16", height: "16"}, use({xlink: "#people-circle"})),
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