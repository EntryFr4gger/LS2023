import buttonWithRef from "../components/ButtonWithRef.js";


function UserDetailsPage(state) {
    const list = document.createElement('ul');

    const items = ['id', 'name', 'email'];

    items.forEach(item => {
        const li = document.createElement('li');
        li.textContent = item + " = " + state.body[item];
        list.appendChild(li);
    });

    const container = document.createElement("div");
    container.appendChild(list);
    container.appendChild(buttonWithRef("Home","/#"));
    container.appendChild(document.createElement('br'))
    container.appendChild(buttonWithRef("Board Details","#users/1/boards"));

    return container

}

export default UserDetailsPage;