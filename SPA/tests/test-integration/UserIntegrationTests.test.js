import {GetUserDetailsFetch} from "../../static-content/components/api/fetch/users/GetUserDetailsFetch";
import {CreateUserFetch} from "../../static-content/components/api/fetch/users/CreateUserFetch";
import {CreateBoardFetch} from "../../static-content/components/api/fetch/boards/CreateBoardFetch";

jest.mock("../../../SPA/static-content/components/utils/storage/get-token.js", () => ({
    getUserToken: jest.fn(() => global.BToken),
}));

jest.mock("../../../SPA/static-content/components/utils/storage/get-user.js", () => ({
    getUser: jest.fn(() => global.UID),
}));


const name = "MrBest"
const email= "MrBest@isel.pt "
const password = "007erag"

const BName = "BName"
const BDescription = "BDescription"

describe("Users Integration Tests", () => {
    beforeAll(async () => {
        const response = await CreateUserFetch(name, email, password);
        global.BToken = "Bearer " + response.token;
        global.UID = response.id;
        const board = await CreateBoardFetch(BName,BDescription);
        global.BoardID = board.id;
    })
    it("Get User Details Integrated", async () => {
        const response = await GetUserDetailsFetch(global.UID);
        expect(response.id).toEqual(global.UID);
        expect(response.name).toEqual(name);
        expect(response.email).toEqual(email);

    });
});
