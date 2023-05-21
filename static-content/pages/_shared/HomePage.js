import {div, h1, p} from "../../components/dom/domTags.js";


async function HomePage() {
    return div({class: "container-fluid mt-3"},
        div({class: "row"},
            div({class: "col-md-6"},
                h1("Welcome to Tasks"),
                p("Here's where you can manage all your tasks.")
            )
        )
    )
}

export default HomePage;