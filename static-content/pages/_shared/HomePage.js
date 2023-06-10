import {div, h1, p} from "../../components/dom/domTags.js";

/**
 * HomePage is an asynchronous function that displays the content of the home page.
 *
 * @returns {Promise<HTMLElement>} The home page component.
 */
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