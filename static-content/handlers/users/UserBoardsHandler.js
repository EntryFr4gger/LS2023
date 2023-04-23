import BoardsPage from "../../pages/boards/BoardsPage.js";


async function UserBoardsHandler(state) {
    const id = state.pathParams["user_id"];
    if (isNaN(id))
        throw ("Invalid param id");



    const response = await fetch(state.path,{
        headers: {Authorization: 'Bearer 9f1e3d11-8c18-4cd7-93fc-985c4794cfd9'}
    });

    state.body = await response.json()

    return BoardsPage(state)
}

export default UserBoardsHandler;