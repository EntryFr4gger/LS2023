var assert = require('assert');
const {CreateUserFetch} = require("../../../components/api/fetch/users/CreateUserFetch.js");


describe('Fetch Tests', function () {
    let user1 = 0;
    const user2 = 0;

    before(function () {

    });

    describe('CreateUserFetchTest', function () {
        it('Create user Successfuly', async function () {
            const response = await CreateUserFetch("Mocha Test", "mochatest@test.pt", "1234")
            const json = await response.json()
            assert.ok(response.ok, json.error)
            user1 = json
        });
    });
});
