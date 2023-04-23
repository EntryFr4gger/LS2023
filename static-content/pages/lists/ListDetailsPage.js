import buttonWithRef from "../components/ButtonWithRef.js";


function ListDetailsPage(state) {
    const list = document.createElement('ul');

    const items = ['id', 'name', 'description'];

    items.forEach(item => {
        const li = document.createElement('li');
        li.textContent = item + " = " + state.body[item];
        list.appendChild(li);
    });
    const li = document.createElement('li');
    li.textContent = "cards ";
    list.appendChild(li);
    const lists = document.createElement('ul')
    list.appendChild(lists)

    const car = state.body["cards"]["cards"]
    car.forEach(currentList => {
        const li = document.createElement('li');
        li.textContent = "name = " + currentList['name'];
        lists.appendChild(li);
        //const t = `/#cards/${currentList['id']}`
        li.appendChild(buttonWithRef("Card Details", `/#cards/${currentList['id']}`))
    });


    const container = document.createElement("div");

    container.appendChild(list)
    container.appendChild(document.createElement('br'))
    container.appendChild(buttonWithRef("Back to Lists", `#lists/1`));

    return container;

}

export default ListDetailsPage;