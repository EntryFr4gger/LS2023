import {CreateUserFetch} from "../../static-content/components/api/fetch/users/CreateUserFetch";
import {CreateBoardFetch} from "../../static-content/components/api/fetch/boards/CreateBoardFetch";
import {CreateListFetch} from "../../static-content/components/api/fetch/lists/CreateListFetch";
import {CreateCardFetch} from "../../static-content/components/api/fetch/cards/CreateCardFetch";
import {GetCardDetailsFetch} from "../../static-content/components/api/fetch/cards/GetCardDetailsFetch";
import {MoveCardFetch} from "../../static-content/components/api/fetch/cards/MoveCardFetch";
import {DeleteCardFetch} from "../../static-content/components/api/fetch/cards/DeleteCardsFetch";

jest.mock("../../../SPA/static-content/components/utils/storage/get-token.js", () => ({
    getUserToken: jest.fn(() => global.BToken),
}));

jest.mock("../../../SPA/static-content/components/utils/storage/get-user.js", () => ({
    getUser: jest.fn(() => global.UID),
}));


const userName = "MrBest4"
const email = "MrBest4@isel.pt "
const password = "007erag"

const BName = "BName4"
const BDescription = "BDescription4"

const LName = "LName4"
const LName2 = "LName5"

const CName = "CName3"
const CName2 = "CName4"
const CDescription = "CDescription3"
describe("Cards Integration Tests", () => {
    beforeAll(async () => {
        const response = await CreateUserFetch(userName, email, password);
        global.BToken = "Bearer " + response.token;
        global.UID = response.id;
        const board = await CreateBoardFetch(BName, BDescription);
        global.BoardID = board.id;
        const list = await CreateListFetch(LName, global.BoardID);
        global.ListID.push(list.id);
        const list2 = await CreateListFetch(LName2, global.BoardID);
        global.ListID.push(list.id);
        const card = await CreateCardFetch(CName, CDescription, global.BoardID, global.ListID[0]);
        global.CardID.push(card.id);
        const card2 = await CreateCardFetch(CName2, CDescription, global.BoardID, global.ListID[0]);
        global.CardID.push(card2.id);

    })

    it("Get detailed information of a list", async () => {
        const response = await GetCardDetailsFetch(global.CardID[0])
        expect(response.id).toEqual(global.CardID[0])
        expect(response.name).toEqual(CName)
        expect(response.description).toEqual(CDescription)
    });

    it("Moves a card given a new location", async () => {
        const response = await MoveCardFetch(global.CardID[0], global.ListID[1])
        expect(response.message).toEqual("Card moved")
        expect(response.sucess).toBe(true)
    });

    it("Delete a card given its ID", async () => {
        const response = await DeleteCardFetch(global.CardID[0])
        expect(response.message).toEqual("Card deleted")
        expect(response.sucess).toBe(true)
    });
});
