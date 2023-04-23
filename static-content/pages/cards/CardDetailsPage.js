import buttonWithRef from "../components/ButtonWithRef.js";


function CardDetailsPage(state) {
    const list = document.createElement('ul');

    const items = ['id', 'name', 'description'];

    items.forEach(item => {
        const li = document.createElement('li');
        li.textContent = item + " = " + state.body[item];
        list.appendChild(li);
    });

    const container = document.createElement("div");

    container.appendChild(list)
    container.appendChild(document.createElement('br'))
    container.appendChild(buttonWithRef("Back to Cards", `#boards/1`));

    return container;

}

export default CardDetailsPage;