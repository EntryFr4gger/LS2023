import {CreateUserFetch} from "../../static-content/components/api/fetch/users/CreateUserFetch";
import {CreateBoardFetch} from "../../static-content/components/api/fetch/boards/CreateBoardFetch";
import {AddUserToBoardFetch} from "../../static-content/components/api/fetch/boards/AddUserToBoardFetch";
import {GetBoardListsFetch} from "../../static-content/components/api/fetch/boards/GetBoardListsFetch";
import {GetBoardDetailsFetch} from "../../static-content/components/api/fetch/boards/GetBoardDetailsFetch";
import {CreateListFetch} from "../../static-content/components/api/fetch/lists/CreateListFetch";
import {GetBoardUsersFetch} from "../../static-content/components/api/fetch/boards/GetBoardUsersFetch";
import {CreateCardFetch} from "../../static-content/components/api/fetch/cards/CreateCardFetch";
import {SearchBoardsFetch} from "../../static-content/components/api/fetch/boards/SearchBoardsFetch";
import {DeleteBoardFetch} from "../../static-content/components/api/fetch/boards/DeleteBoardFetch";

jest.mock("../../../SPA/static-content/components/utils/storage/get-token.js", () => ({
    getUserToken: jest.fn(() => global.BToken),
}));

jest.mock("../../../SPA/static-content/components/utils/storage/get-user.js", () => ({
    getUser: jest.fn(() => global.UID),
}));


const userName = "MrBest2"
const email = "MrBest2@isel.pt "
const password = "007erag"

const BName = "BName2"
const BDescription = "BDescription2"
const LName = "LName2"

const CName = "CName2"
const CDescription = "CDescription2"
describe("Board Integration Tests", () => {
    beforeAll(async () => {
        const response = await CreateUserFetch(userName, email, password);
        global.BToken = "Bearer " + response.token;
        global.UID = response.id;
        const board = await CreateBoardFetch(BName, BDescription);
        global.BoardID = board.id;
        const list = await CreateListFetch(LName, global.BoardID);
        global.ListID.push(list.id)
        const card = await CreateCardFetch(CName, CDescription, global.BoardID, global.ListID[0]);
        global.CardID.push(card.id);

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
        expect(response.lists).toEqual([{id: global.ListID[0], name: LName, boardId: global.BoardID}])
    });

    it("Get the list with the users of a board", async () => {
        const response = await GetBoardUsersFetch(global.BoardID)
        expect(response.users).toEqual([{id: global.UID, name: userName, email: email, password: ""}])
    });

    it("Search for the name of the board in the database.", async () => {
        const response = await SearchBoardsFetch(BName, 0, 10)
        expect(response.boards).toEqual([{id: global.BoardID, name: BName, description: BDescription}])
    });

    it("Delete a board", async () => {
        const response = await DeleteBoardFetch(global.BoardID)
        expect(response.message).toEqual("Board Deleted")
        expect(response.sucess).toBe(true)
    });
});
