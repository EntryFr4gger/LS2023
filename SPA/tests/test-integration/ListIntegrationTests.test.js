
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
import {GetListDetailsFetch} from "../../static-content/components/api/fetch/lists/GetListDetailsFetch";
import {GetListCardsFetch} from "../../static-content/components/api/fetch/lists/GetListCardsFetch";
import {DeleteListFetch} from "../../static-content/components/api/fetch/lists/DeleteListFetch";
import {RepositionCardFetch} from "../../static-content/components/api/fetch/lists/RepositionCardFetch";

jest.mock("../../../SPA/static-content/components/utils/storage/get-token.js", () => ({
    getUserToken: jest.fn(() => global.BToken),
}));

jest.mock("../../../SPA/static-content/components/utils/storage/get-user.js", () => ({
    getUser: jest.fn(() => global.UID),
}));


const userName = "MrBest3"
const email= "MrBest3@isel.pt "
const password = "007erag"

const BName = "BName3"
const BDescription = "BDescription3"

const LName = "LName3"

const CName = "CName3"
const CName2 = "CName4"
const CDescription = "CDescription3"

describe("List Integration Tests", () => {
    beforeAll(async () => {
        const response = await CreateUserFetch(userName, email, password);
        global.BToken = "Bearer " + response.token;
        global.UID = response.id;
        const board = await CreateBoardFetch(BName,BDescription);
        global.BoardID = board.id;
        const list = await CreateListFetch(LName,global.BoardID);
        global.ListID.push(list.id);
        const card = await CreateCardFetch(CName,CDescription,global.BoardID,global.ListID[0]);
        global.CardID.push(card.id);
        const card2 = await CreateCardFetch(CName2,CDescription,global.BoardID,global.ListID[0]);
        global.CardID.push(card2.id);
    })

    it("Get detailed information of a list", async () => {
        const response = await GetListDetailsFetch(global.ListID[0])
        expect(response.id).toEqual(global.ListID[0])
        expect(response.name).toEqual(LName)
        expect(response.boardId).toEqual(global.BoardID)
    });

    it("Get list of a cards", async () => {
        const response = await GetListCardsFetch(global.ListID[0])
        expect(response.cards)
            .toEqual([{id: global.CardID[0], name : CName, description : CDescription, boardId : global.BoardID, listId : global.ListID[0]},
                              {id: global.CardID[1], name : CName2, description : CDescription, boardId : global.BoardID, listId : global.ListID[0]}])
    });

    it("Moves a card given a new card position", async () => {
        const response = await RepositionCardFetch(global.ListID[0],global.CardID[1],1)
        expect(response.message).toEqual("Card Repositioned")
        expect(response.sucess).toBe(true)
    });

    it("Delete a list given its Id", async () => {
        const response = await DeleteListFetch(global.ListID[0])
        expect(response.message).toEqual("List Deleted")
        expect(response.sucess).toBe(true)
    });
});
