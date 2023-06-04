import {div, p} from "../../dom/domTags.js";

async function CreateInfiniteScroll(state, props, className) {

    const {onLoadMore, initialNumChildren, numChildren, overflowHeight, resetRef} = props

    const container = await div({class: className, style: {height: overflowHeight}})
    let isLoading = false;

    container.addEventListener("scroll", async () => {
        const lastElement = container.lastElementChild;
        if (!lastElement) return

        const {top: lastEleTop} = lastElement.getBoundingClientRect();
        const {bottom: containerBottom} = container.getBoundingClientRect();
        if (lastEleTop < containerBottom && !isLoading) {
            isLoading = true;

            const children = await Promise.all(await onLoadMore(numChildren));
            container.append(...children);

            isLoading = false;
        }
    }, false);

    async function reset() {
        if (isLoading)
            return;

        isLoading = true;
        container.innerHTML = "";

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
