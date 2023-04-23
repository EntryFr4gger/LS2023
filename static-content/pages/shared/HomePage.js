import buttonWithRef from "../components/ButtonWithRef.js";


async function HomePage(state) {
    const h1 = document.createElement("h3");
    const text = document.createTextNode("User Home");
    h1.appendChild(text);

    const container = document.createElement("div");
    container.appendChild(h1);
    container.appendChild(buttonWithRef("User Details","#users/1"));
    container.appendChild(document.createElement('br'))
    container.appendChild(buttonWithRef("Boards","#users/1/boards"));

    return container;
}
export default HomePage;