import HomePage from "../../pages/_shared/HomePage.js";


async function HomeHandler(state) {
    return await HomePage(state)
}

export default HomeHandler;