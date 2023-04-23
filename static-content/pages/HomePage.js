

async function HomePage(state) {
    const h1 = document.createElement("h1")
    const text = document.createTextNode("Home")
    h1.appendChild(text)
    return h1
}

export default HomePage;