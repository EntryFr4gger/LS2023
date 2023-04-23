import {br, div, h3, p} from "../components/dom/domTags.js";
import buttonWithRef from "../components/ButtonWithRef.js";

async function NotFoundPage(state) {
    return div(
        h3(
            p("Error"),
            buttonWithRef("User Details", "#users/1"),
            br(),
            buttonWithRef("Boards", "#users/1/boards")
        )
    )
}

export default NotFoundPage;