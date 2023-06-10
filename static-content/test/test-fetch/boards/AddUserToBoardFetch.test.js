import {AddUserToBoardFetch} from "../../../components/api/fetch/boards/AddUserToBoardFetch";

jest.mock("../../../components/utils/get-token.js", () => ({
    getUserToken: jest.fn(() => "Bearer 9f1e3d11-8c18-4cd7-93fc-985c4794cfd9"),
}));

describe("AddUserToBoardFetch", () => {
    it("should make a POST request to add a user to a board", async () => {
        global.fetch = jest.fn().mockResolvedValue({
            // Mock the response object
            ok: true,
            status: 200, // Mock the status code
            json: jest.fn().mockResolvedValue({ /* Mock response data */}),
        });

        const boardId = "1";
        const userId = "3";

        const response = await AddUserToBoardFetch(boardId, userId);

        // Verify that fetch was called with the correct arguments
        expect(fetch).toHaveBeenCalledWith(`boards/${boardId}/users`, {
            method: "POST",
            headers: {Authorization: "Bearer 9f1e3d11-8c18-4cd7-93fc-985c4794cfd9"},
            body: JSON.stringify({id: userId}),
        });

        // Assert that the response status is 200
        expect(response.status).toBe(200);

        const json = await response.json();

        // Optionally, you can also assert on the response data or other behavior
    });
});
