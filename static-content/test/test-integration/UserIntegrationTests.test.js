import {GetUserDetailsFetch} from "../../components/api/fetch/users/GetUserDetailsFetch";
let bearerToken = "Bearer 9f1e3d11-8c18-4cd7-93fc-985c4794cfd9"
let userId = 1
jest.mock("../../../static-content/components/utils/get-token.js", () => ({
    getUserToken: jest.fn(() => bearerToken),
}));

jest.mock("../../../static-content/components/utils/get-user.js", () => ({
    getUser: jest.fn(() => userId),
}));

describe("Users Integration Tests", () => {

    it("Get User Details Integrated", async () => {
        const response = await GetUserDetailsFetch(userId);
        const json = await response.json()

        // Verify if the response contains a user ID and user name
        expect(json).toHaveProperty("id");
        expect(json).toHaveProperty("name");
        expect(json.id).toEqual(userId);

    });
});
