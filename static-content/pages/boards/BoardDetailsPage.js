import buttonWithRef from "../components/ButtonWithRef.js";


function BoardDetailsPage(state) {
    const list = document.createElement('ul');

    const items = ['id', 'name', 'description'];

    items.forEach(item => {
        const li = document.createElement('li');
        li.textContent = item + " = " + state.body[item];
        list.appendChild(li);
    });
    const li = document.createElement('li');
    li.textContent = "lists ";
    list.appendChild(li);
    const lists = document.createElement('ul')
    list.appendChild(lists)

    const lis = state.body["lists"]["lists"]
    lis.forEach(currentList => {
        const li = document.createElement('li');
        li.textContent = "name = " + currentList['name'];
        lists.appendChild(li);
        li.appendChild(buttonWithRef("List Details", `/#lists/${currentList['id']}`))
    });


    const container = document.createElement("div");

    container.appendChild(list);
    container.appendChild(buttonWithRef("Get Users for this Board", `#boards/${state.body["id"]}/users`));
    container.appendChild(document.createElement('br'))
    //container.appendChild(buttonWithRef("Back to Boards", `#users/1/boards`));

    return container;

}

export default BoardDetailsPage;