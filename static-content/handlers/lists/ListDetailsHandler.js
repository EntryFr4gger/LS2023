import ListDetailsPage from "../../pages/lists/ListDetailsPage.js";


async function ListDetailsHandler(state) {
    const id = state.pathParams["list_id"];
    if (isNaN(id))
        throw ("Invalid param id");

    const listRes = await fetch(state.path, {
        headers: {Authorization: state.token}
    });

    const cardRes = await fetch(`lists/${id}/cards`, {
        headers: {Authorization: state.token}
    });

    state.body = await listRes.json()
    state.body["cards"] = await cardRes.json()

    return ListDetailsPage(state)
}

export default ListDetailsHandler;
