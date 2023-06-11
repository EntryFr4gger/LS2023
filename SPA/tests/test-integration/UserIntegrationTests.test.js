import {GetUserDetailsFetch} from "../../static-content/components/api/fetch/users/GetUserDetailsFetch";
let bearerToken = "Bearer 9f1e3d11-8c18-4cd7-93fc-985c4794cfd9"
let userId = 1
jest.mock("../../../SPA/static-content/components/utils/storage/get-token.js", () => ({
    getUserToken: jest.fn(() => bearerToken),
}));

jest.mock("../../../SPA/static-content/components/utils/storage/get-user.js", () => ({
    getUser: jest.fn(() => userId),
}));

describe("Users Integration Tests", () => {

    it("Get User Details Integrated", async () => {
        const response = await GetUserDetailsFetch(userId);

        // Verify if the response contains a user ID and user name
        expect(response).toHaveProperty("id");
        expect(response).toHaveProperty("name");
        expect(response.id).toEqual(userId);

    });
});
