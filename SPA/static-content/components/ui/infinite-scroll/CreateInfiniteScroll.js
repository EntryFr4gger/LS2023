import {div, p} from "../../dom/domTags.js";

/**
 * CreateInfiniteScroll function creates an infinite scroll component with the specified state, props, and className.
 *
 * @param {object} state - The state object.
 * @param {object} props - The prop object containing onLoadMore,
 * initialNumChildren, numChildren, overflowHeight, and resetRef.
 * @param {string} className - The class name for the container element.
 *
 * @returns {HTMLElement} - The generated infinite scroll container element.
 */
async function CreateInfiniteScroll(state, props, className) {

    const {onLoadMore, initialNumChildren, numChildren, overflowHeight, resetRef} = props

    const container = await div({class: className, style: {height: overflowHeight}})
    let isLoading = false;

    // Add a scroll event listener to the container
    container.addEventListener("scroll", async () => {
        // Get the last element in the container
        const lastElement = container.lastElementChild;
        if (!lastElement) return

        // Get the top and bottom positions of the last element and the container
        const {top: lastEleTop} = lastElement.getBoundingClientRect();
        const {bottom: containerBottom} = container.getBoundingClientRect();

        // Check if the last element is within the visible area of the container and not currently loading
        if (lastEleTop < containerBottom && !isLoading) {
            isLoading = true;

            // Load more children asynchronously
            const children = await Promise.all(await onLoadMore(numChildren));
            container.append(...children);

            isLoading = false;
        }
    }, false);

    /**
     * Reset function clears the container and reloads the initial children.
     */
    async function reset() {
        // If already loading, return
        if (isLoading)
            return;

        isLoading = true;
        container.innerHTML = "";

        // Load initial children asynchronously
        const children = await Promise.all(await onLoadMore(initialNumChildren));
        if (children.length === 0)
            container.append(await p({style: {"margin-left": "-25%"}}, "Empty"));
        else
            container.append(...children);


        isLoading = false;
    }

    resetRef.resolve(reset);

    await reset();

    return container;
}

export default CreateInfiniteScroll;
