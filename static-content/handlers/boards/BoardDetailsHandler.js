import BoardDetailsPage from "../../pages/boards/BoardDetailsPage.js";


async function BoardDetailsHandler(state) {
    const id = state.pathParams["board_id"];
    if (isNaN(id))
        throw ("Invalid param id");

    const boardRes = await fetch(state.path, {
        headers: {Authorization: state.token}
    });

    const listRes = await fetch(`boards/${id}/lists`, {
        headers: {Authorization: state.token}
    });

    state.body = await boardRes.json()
    state.body["lists"] = await listRes.json()

    return BoardDetailsPage(state)
}

export default BoardDetailsHandler;
