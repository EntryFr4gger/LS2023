import {div} from "../../dom/domTags.js";

async function InfiniteScroll(state, props, className) {

    const {
        onLoadMore,
        initialNumChildren,
        numChildren,
        overflowHeight,
        resetRef
    } = props;

    const container = await div({class: className, style: {height: overflowHeight}});

    let loading = false;

    container.addEventListener("scroll", async () => {
        const lastElement = container.lastElementChild;
        if (lastElement == null)
            return;

        const {top: lastEleTop} = lastElement.getBoundingClientRect();
        const {bottom: containerBottom} = container.getBoundingClientRect();
        if (lastEleTop < containerBottom && !loading) {
            loading = true;

            const children = await Promise.all(await onLoadMore(numChildren));
            container.append(...children);

            loading = false;
        }
    }, false);

    async function reset() {
        if (loading)
            return;

        loading = true;
        container.innerHTML = "";

        const children = await Promise.all(await onLoadMore(initialNumChildren));
        container.append(...children);

        loading = false;
    }

    resetRef.resolve(reset);

    await reset();

    return container;
}

export default InfiniteScroll;
