import buttonWithRef from "../components/ButtonWithRef.js";


async function BoardsPage(state) {
    const list = document.createElement('ul');
    const boards =state.body["boards"]

    boards.forEach(board => {
        const boardItem = document.createElement('li');
        boardItem.textContent = "Board: " + board["name"];
        boardItem.appendChild(buttonWithRef("board details",`/#boards/${board["id"]}`))
        list.appendChild(boardItem);
    });


    const container = document.createElement("div");
    container.appendChild(list);
    container.appendChild(buttonWithRef("Home","/#"));
    container.appendChild(document.createElement('br'))
    container.appendChild(buttonWithRef("UserDetails","/#users/1"));

    return container;
}


export default BoardsPage;