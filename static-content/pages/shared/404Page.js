import {br, div, h1, p} from "../../components/dom/domTags.js";
import NavBar from "./NavBar.js";

async function NotFoundPage(state, error) {
    return div(
        await NavBar(state),
        br(),
        h1("Error"),
        br(),
        p(error)
    )
}

export default NotFoundPage;