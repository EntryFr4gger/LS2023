export function SortableList() {
    const sortableList = document.querySelector(".sortable-list");
    const items = sortableList.querySelectorAll(".item");

    items.forEach(item => {
        item.addEventListener("dragstart", () => {
            setTimeout(() => item.classList.add("dragging"), 0)
        })
        item.addEventListener("dragend", () => item.classList.remove("dragging"))
    })

    const initSortableList = (e) => {
        e.preventDefault();
        const draggingItem = document.querySelector(".dragging");
        let siblings = [...sortableList.querySelectorAll(".item:not(.dragging)")];
        let nextSibling = siblings.find(sibling => {
            console.log(sibling.offsetTop + sibling.offsetHeight)
            return e.clientY <= sibling.offsetTop + sibling.offsetHeight / 2
        })
        //console.log(`${e.clientY}`)
        //console.log(`next:${nextSibling.value}|curr:${draggingItem.value}`)

        sortableList.insertBefore(draggingItem, nextSibling)
    }

    sortableList.addEventListener("dragover", initSortableList)
    sortableList.addEventListener("dragenter", e => e.preventDefault())
    sortableList.addEventListener("drop", updateCard)
}

function updateCard(e) {
    e.preventDefault();
    const sortableList = document.querySelector(".sortable-list");
    const draggingItem = document.querySelector(".dragging");
    let siblings = [...sortableList.querySelectorAll(".item")];
    let i = 0
    siblings.find(sibling => {
        i++
        return sibling.value === draggingItem.value
    })
    console.log(i)


    console.log("::Dropped::")
}

SortableList()
