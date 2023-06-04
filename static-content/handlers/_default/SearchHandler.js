import {hashChangeLoc} from "../../components/utils/hash-change-loc.js";
import {form, input} from "../../components/dom/domTags.js";

async function SearchHandler() {

    function search(event) {
        event.preventDefault()
        const searchValue = document.getElementById("search-res").value
        if (searchValue.trim() !== "") {
            sessionStorage.setItem("search-res", document.getElementById("search-res").value)
            hashChangeLoc(`#boards/search`)
        }
    }

    return form({class: "d-flex", role: "search", onSubmit: search},
        input({
            class: "form-control me-2",
            id: "search-res",
            type: "search",
            placeholder: "Search Board",
            "aria-label": "Search"
        })
    )
}

export default SearchHandler;