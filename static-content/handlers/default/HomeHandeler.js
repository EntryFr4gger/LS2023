import HomePage from "../../pages/HomePage.js";


async function HomeHandeler(state) {
    return await HomePage(state)
    /*const id = state.params.id;
    if (isNaN(id))
        throw new LogError("Invalid param id");

    const user = await apiFetch(`/users/${id}`);

    const {skip, limit} = getQuerySkipLimit(state.query, 0, 5);
    const activitiesData = await apiFetch(`/users/${id}/activities?skip=${skip}&limit=${limit}`);

    activitiesData.skip = skip;
    activitiesData.limit = limit;

    return User(
        state,
        {
            ...user,
            activitiesData
        }
    );*/
}

export default HomeHandeler;