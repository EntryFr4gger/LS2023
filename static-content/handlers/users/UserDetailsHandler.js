import UserDetailsPage from "../../pages/users/UserDetailsPage.js";


async function UserDetailsHandler(state) {
    const id = state.pathParams["user_id"];
    if (isNaN(id))
        throw ("Invalid param id");

    const response = await fetch(state.path);

    state.body = await response.json()


    return UserDetailsPage(state)
}

export default UserDetailsHandler;
