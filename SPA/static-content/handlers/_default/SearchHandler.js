import {hashChangeLoc} from "../../components/utils/hash-change-loc.js";
import {form, input} from "../../components/dom/domTags.js";

/**
 * SearchHandler is an asynchronous function that handles the search functionality.
 *
 * @returns {Promise} A promise that resolves to the rendered search form.
 */
async function SearchHandler() {

    /**
     * search is a function that handles the search event.
     *
     * @param {Event} event - The search event.
     */
    function search(event) {
        event.preventDefault()
        const searchValue = document.getElementById("search-res").value
        if (searchValue.trim() !== "") {
            sessionStorage.setItem("search-res", document.getElementById("search-res").value)
            hashChangeLoc(`#boards/search`)
        }
    }

    return form({class: "d-flex", role: "search", style:{width: "15%"}, onSubmit: search},
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