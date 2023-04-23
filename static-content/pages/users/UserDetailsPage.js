import buttonWithRef from "../components/ButtonWithRef.js";
import {br, div, li, ul} from "../components/dom/domTags.js";


function UserDetailsPage(state) {
    const items = ['id', 'name', 'email'];

    return div(
        ul(
            li((items[0] + " = " + state.body[items[0]])),
            li((items[1] + " = " + state.body[items[1]])),
            li((items[2] + " = " + state.body[items[2]])),
            buttonWithRef("Home", "/#"),
            br(),
            buttonWithRef("Board Details", "#users/1/boards")
        )
    )

    /*const list = document.createElement('ul');

    //const items = ['id', 'name', 'email'];

    items.forEach(item => {
        const li = document.createElement('li');item + " = " + state.body[item]
        li.textContent = ;
        list.appendChild(li);
    });

    const container = document.createElement("div");
    container.appendChild(list);
    container.appendChild(buttonWithRef("Home", "/#"));
    container.appendChild(document.createElement('br'))
    container.appendChild(buttonWithRef("Board Details", "#users/1/boards"));

    return container
*/
}

export default UserDetailsPage;