
import {CreateUserFetch} from "../../static-content/components/api/fetch/users/CreateUserFetch";
import {CreateBoardFetch} from "../../static-content/components/api/fetch/boards/CreateBoardFetch";
import {AddUserToBoardFetch} from "../../static-content/components/api/fetch/boards/AddUserToBoardFetch";
import {GetBoardListsFetch} from "../../static-content/components/api/fetch/boards/GetBoardListsFetch";
import {GetBoardDetailsFetch} from "../../static-content/components/api/fetch/boards/GetBoardDetailsFetch";
import {CreateListFetch} from "../../static-content/components/api/fetch/lists/CreateListFetch";
import {GetBoardUsersFetch} from "../../static-content/components/api/fetch/boards/GetBoardUsersFetch";
import {GetBoardCardsArchivedFetch} from "../../static-content/components/api/fetch/boards/GetBoardCardsArchivedFetch";
import {CreateCardFetch} from "../../static-content/components/api/fetch/cards/CreateCardFetch";

jest.mock("../../../SPA/static-content/components/utils/storage/get-token.js", () => ({
    getUserToken: jest.fn(() => global.BToken),
}));

jest.mock("../../../SPA/static-content/components/utils/storage/get-user.js", () => ({
    getUser: jest.fn(() => global.UID),
}));


const userName = "MrBest"
const email= "MrBest@isel.pt "
const password = "007erag"

const BName = "BName"
const BDescription = "BDescription"
const LName = "LName"

const CName = "CName"
const CDescription = "CDescription"
describe("Users Integration Tests", () => {
    beforeAll(async () => {
        const response = await CreateUserFetch(userName, email, password);
        global.BToken = "Bearer " + response.token;
        global.UID = response.id;
        const board = await CreateBoardFetch(BName,BDescription);
        global.BoardID = board.id;
        const list = await CreateListFetch(LName,global.BoardID);
        global.ListID = list.id;
        // const card = await CreateCardFetch(CName,CDescription,global.BoardID,global.ListID);
        // global.CardID = card.id;

    })

    it("Adds a user to a board", async () => {
        const response = await AddUserToBoardFetch(global.BoardID, global.UID);
        expect(response.message).toEqual("User added");
        expect(response.sucess).toBe(true)
    });

    it(" Get the detailed information of a board", async () => {
        const response = await GetBoardDetailsFetch(global.BoardID)
        expect(response.id).toEqual(global.BoardID)
        expect(response.name).toEqual(BName)
        expect(response.description).toEqual(BDescription)
    });

    it(" Get the detailed information of a board", async () => {
        const response = await GetBoardListsFetch(global.BoardID)
        expect(response.lists).toEqual([{id: global.ListID, name: LName, boardId: global.BoardID}])
    });

    it("Get the list with the users of a board", async () => {
        const response = await GetBoardUsersFetch(global.BoardID)
        expect(response.users).toEqual([{id:global.UID, name : userName, email : email, password: " "}])
    });
    //
    // it("Gets the list of a cards for a given board", async () => {
    //     const response = await GetBoardCardsArchivedFetch(global.BoardID)
    //     expect(response.cards).toEqual([{
    //         id: global.ListID, name : CName, description : CDescription, boardId: global.BoardID, listId: global.ListID}])
    // });
});
