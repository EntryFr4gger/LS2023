import {GetUserDetailsFetch} from "../../static-content/components/api/fetch/users/GetUserDetailsFetch";
import {CreateUserFetch} from "../../static-content/components/api/fetch/users/CreateUserFetch";

jest.mock("../../../SPA/static-content/components/utils/storage/get-token.js", () => ({
    getUserToken: jest.fn(() => global.BToken),
}));

jest.mock("../../../SPA/static-content/components/utils/storage/get-user.js", () => ({
    getUser: jest.fn(() => global.UID),
}));

const name = "MrBest1"
const email= "MrBest1@isel.pt "
const password = "007erag"

describe("Users Integration Tests", () => {
    beforeAll(async () => {
        const response = await CreateUserFetch(name, email, password);
        global.BToken = response.token
        global.UID = response.id
    })
    it("Get User Details Integrated", async () => {
        const response = await GetUserDetailsFetch(global.UID);
        expect(response.id).toEqual(global.UID);
        expect(response.name).toEqual(name);
        expect(response.email).toEqual(email);

    });
});
