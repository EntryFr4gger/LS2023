function NavBar() {
    const nav = document.createElement("nav");
    nav.style.display = "flex";
    nav.style.backgroundColor = "#f0f0f0";
    nav.style.padding = "10px";

    const ul = document.createElement("ul");
    ul.style.listStyle = "none";
    ul.style.display = "flex";
    ul.style.gap = "10px";
    ul.style.margin = "0";

    const liHome = document.createElement("li");
    liHome.style.margin = "0";
    const aHome = document.createElement("a");
    aHome.setAttribute("href", "#");
    aHome.textContent = "Tasks App";
    liHome.appendChild(aHome);
    ul.appendChild(liHome);

    const liUser = document.createElement("li");
    liUser.style.margin = "0";
    const aUser = document.createElement("a");
    //aUser.setAttribute("href", "#users/1");
    aUser.textContent = "User";
    //liUser.appendChild(aUser);
    //ul.appendChild(liUser);

    nav.appendChild(ul);
    return nav

}

export default NavBar;