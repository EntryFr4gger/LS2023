import {CreateCardFetch} from "../../static-content/components/api/fetch/cards/CreateCardFetch";
import {DeleteCardFetch} from "../../static-content/components/api/fetch/cards/DeleteCardsFetch";
import {GetCardDetailsFetch} from "../../static-content/components/api/fetch/cards/GetCardDetailsFetch";
import {MoveCardFetch} from "../../static-content/components/api/fetch/cards/MoveCardFetch";

const bearerToken = "Bearer 9f1e3d11-8c18-4cd7-93fc-985c4794cfd9"
jest.mock("../../../SPA/static-content/components/utils/storage/get-token.js", () => ({
    getUserToken: jest.fn(() => bearerToken),
}));
global.fetch = jest.fn().mockResolvedValue({
    ok: true,
    status: 200,
    json: jest.fn().mockResolvedValue({}),
});

describe("Cards Fetch Tests", () => {

    it("Create Card Fetch", async () => {
        const name = "new Card";
        const description = "VeryDescriptions";
        const boardId = 1;
        const listId = 1;

        await CreateCardFetch(name, description,boardId,listId);

        expect(fetch).toHaveBeenCalledWith(`cards/`, {
            method: "POST",
            headers: { Authorization: bearerToken },
            body: JSON.stringify({ name: name, description:description,boardId:boardId,listId:listId }),
        });
    });

    it("Delete Card Fetch", async () => {
        const cardId = 1;

        await DeleteCardFetch(cardId);

        expect(fetch).toHaveBeenCalledWith(`cards/${cardId}`, {
            method: "DELETE",
            headers: { Authorization: bearerToken }
        });
    });

    it("Get Card Details Fetch", async () => {
        const cardId = 1;

        await GetCardDetailsFetch(cardId);

        expect(fetch).toHaveBeenCalledWith(`cards/${cardId}`, {
            headers: { Authorization: bearerToken }
        });
    });

    it("Move Card Fetch without list", async () => {
        const cardId = 1;

        await MoveCardFetch(cardId);

        expect(fetch).toHaveBeenCalledWith(`cards/${cardId}`, {
            method: "PUT",
            headers: { Authorization: bearerToken },
            body: JSON.stringify({ lid: null }),
        });
    });

    it("Move Card Fetch with list", async () => {
        const cardId = 1;
        const lid = 1;

        await MoveCardFetch(cardId,lid);

        expect(fetch).toHaveBeenCalledWith(`cards/${cardId}`, {
            method: "PUT",
            headers: { Authorization: bearerToken },
            body: JSON.stringify({ lid: lid }),
        });
    });



});
