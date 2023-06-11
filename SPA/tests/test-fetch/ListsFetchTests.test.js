import {CreateListFetch} from "../../static-content/components/api/fetch/lists/CreateListFetch";
import {DeleteListFetch} from "../../static-content/components/api/fetch/lists/DeleteListFetch";
import {GetListCardsFetch} from "../../static-content/components/api/fetch/lists/GetListCardsFetch";
import {GetListDetailsFetch} from "../../static-content/components/api/fetch/lists/GetListDetailsFetch";
import {RepositionCardFetch} from "../../static-content/components/api/fetch/lists/RepositionCardFetch";

const bearerToken = "Bearer 9f1e3d11-8c18-4cd7-93fc-985c4794cfd9"
jest.mock("../../../SPA/static-content/components/utils/storage/get-token.js", () => ({
    getUserToken: jest.fn(() => bearerToken),
}));
global.fetch = jest.fn().mockResolvedValue({
    ok: true,
    status: 200,
    json: jest.fn().mockResolvedValue({}),
});

describe("Lists Fetch Tests", () => {

    it("Create List Fetch", async () => {
        const name = "New List";
        const boardId = 1;

        await CreateListFetch(name, boardId);

        expect(fetch).toHaveBeenCalledWith(`lists/`, {
            method: "POST",
            headers: {Authorization: bearerToken},
            body: JSON.stringify({name: name, boardId: boardId}),
        });
    });

    it("Delete List Fetch", async () => {
        const listId = 1;

        await DeleteListFetch(listId);

        expect(fetch).toHaveBeenCalledWith(`lists/${listId}`, {
            method: "DELETE",
            headers: {Authorization: bearerToken}
        });
    });

    it("Get List Cards Fetch", async () => {
        const listId = 1;

        await GetListCardsFetch(listId);

        expect(fetch).toHaveBeenCalledWith(`lists/${listId}/cards`, {
            headers: {Authorization: bearerToken}
        });
    });

    it("Get List Details Fetch", async () => {
        const listId = 1;

        await GetListDetailsFetch(listId);

        expect(fetch).toHaveBeenCalledWith(`lists/${listId}`, {
            headers: {Authorization: bearerToken}
        });
    });

    it("Reposition Card Fetch", async () => {
        const listId = 1;
        const cardId = 1;
        const cix = 1;

        await RepositionCardFetch(listId, cardId, cix);

        expect(fetch).toHaveBeenCalledWith(`lists/${listId}/cards`, {
            method: "PUT",
            headers: {Authorization: bearerToken},
            body: JSON.stringify({cardId: cardId, cix: cix}),
        });
    });
});
