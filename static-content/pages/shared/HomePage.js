import buttonWithRef from "../components/ButtonWithRef.js";
import {br, div, h3, p} from "../components/dom/domTags.js";


async function HomePage() {
    return div(
        h3(
            p("User Home"),
            buttonWithRef("User Details", "#users/1"),
            br(),
            buttonWithRef("Boards", "#users/1/boards")
        )
    )
}

export default HomePage;