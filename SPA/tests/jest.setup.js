require('node-fetch');


const originalFetch = jest.requireActual('node-fetch');

global.fetch = jest.fn().mockImplementation((url, options) => {
    const modifiedUrl = `http://localhost:9000/${url}`;
    return originalFetch(modifiedUrl, options);
});

global.alert = jest.fn().mockImplementation((string) => {
    console.log(string)
});