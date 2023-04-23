import buttonWithRef from "../components/ButtonWithRef.js";


function BoardDetailsPage(state) {
    const list = document.createElement('ul');


    const items = ['id', 'name', 'description'];

    items.forEach(item => {
        const li = document.createElement('li');
        li.textContent = item + " = " + state.body[item];
        list.appendChild(li);
        const lis = state.body["lists"]["lists"]
        lis.forEach(currentList => {
            items.forEach(currentItem => {
                const li = document.createElement('li');
                li.textContent = item + " = " + currentList[item];
                list.appendChild(li);
                })
            const li = document.createElement('li');
            li.textContent = item + " = " + lis[item];
            list.appendChild(li);
        });
    });

    list.appendChild(buttonWithRef("GetBoardLists",`/#boards/${state.body["id"]}/lists`));
    list.appendChild(buttonWithRef("Get Users for this Board",`#boards/${state.body["id"]}/users`));

    const container = document.createElement("div");

    container.appendChild(list);
    container.appendChild(document.createElement('br'))
    container.appendChild(buttonWithRef("Back to Boards",`#users/1/boards`));

    return container;

}

export default BoardDetailsPage;