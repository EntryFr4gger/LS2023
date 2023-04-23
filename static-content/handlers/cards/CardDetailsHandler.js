import CardDetailsPage from "../../pages/cards/CardDetailsPage.js";


async function CardDetailsHandler(state) {
    const id = state.pathParams["card_id"];
    if (isNaN(id))
        throw ("Invalid param id");

    const cardRes = await fetch(state.path,{
        headers: {Authorization: state.token}
    });

    state.body = await cardRes.json()

    return CardDetailsPage(state)
}

export default CardDetailsHandler;
