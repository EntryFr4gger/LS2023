import BoardUsersPage from "../../pages/boards/BoardUsersPage.js";


async function BoardUsersHandler(state) {
    const id = state.pathParams["board_id"];
    if (isNaN(id))
        throw ("Invalid param id");

    const response = await fetch(state.path,{
        headers: {Authorization: state.token}
    });

    state.body = await response.json()

    return BoardUsersPage(state)
}

export default BoardUsersHandler;
