import {a, nav} from "../components/dom/domTags.js";

async function NavBar() {
    return nav(
        {class: "nav nav-pills"},
        a({class: "nav-link", href: "#"}, "Home"),
    )
}


export default NavBar;