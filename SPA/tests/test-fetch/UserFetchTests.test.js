import {CreateUserFetch} from "../../static-content/components/api/fetch/users/CreateUserFetch";
import {GetAllUsersNotInBoard} from "../../static-content/components/api/fetch/users/GetAllUsersNotInBoard.js";
import {GetUserDetailsFetch} from "../../static-content/components/api/fetch/users/GetUserDetailsFetch";
import {LoginUserFetch} from "../../static-content/components/api/fetch/users/LoginUserFetch";
import {GetUserBoardsFetch} from "../../static-content/components/api/fetch/users/GetUserBoardsFetch";

const bearerToken = "Bearer 9f1e3d11-8c18-4cd7-93fc-985c4794cfd9"
const userId = 1
jest.mock("../../../SPA/static-content/components/utils/storage/get-token.js", () => ({
    getUserToken: jest.fn(() => bearerToken),
}));

jest.mock("../../../SPA/static-content/components/utils/storage/get-user.js", () => ({
    getUser: jest.fn(() => userId),
}));

global.fetch = jest.fn().mockResolvedValue({
    ok: true,
    status: 200,
    json: jest.fn().mockResolvedValue({}),
});

describe("Users Fetch Tests", () => {

    it("Create User Fetch", async () => {
        const name = "User123";
        const email = "newUser@gmail.com";
        const password = "minhapass123";

        await CreateUserFetch(name,email,password);

        expect(fetch).toHaveBeenCalledWith(`users/`, {
            method: "POST",
            body: JSON.stringify({ name: name,email:email,password:password }),
        });
    });

    it("Get All Users Fetch", async () => {
        const boardId = 1;

        await GetAllUsersNotInBoard(boardId);

        expect(fetch).toHaveBeenCalledWith(`users/${userId}`, {
            method: "POST",
            headers: { Authorization: bearerToken },
            body: JSON.stringify({ id: boardId}),
        });
    });

    it("Get User Boards Fetch", async () => {
        const userId = 1;
        const skip = 1;
        const limit = 1;
        await GetUserBoardsFetch(userId,skip,limit);

        expect(fetch).toHaveBeenCalledWith(`users/${userId}/boards?skip=${skip}&limit=${limit}`, {
            headers: { Authorization: bearerToken }
        });
    });

    it("Get User Details Fetch", async () => {
        const userId = 1;

        await GetUserDetailsFetch(userId);

         expect(fetch).toHaveBeenCalledWith(`users/${userId}`);
    });

    it("Login User Fetch", async () => {

        const email = "newUser@gmail.com";
        const password = "minhapass123";

        await LoginUserFetch(email,password);

        expect(fetch).toHaveBeenCalledWith(`users/login`, {
            method: "POST",
            body: JSON.stringify({ email:email,password:password})
        });
    });
});
