/**
 * Reference is a function that creates a promise-based reference with resolve and reject functions.
 *
 * @returns {Promise} The promise-based reference with resolve and reject functions.
 */
export function reference() {
    let resolve, reject;

    let promise = new Promise((_resolve, _reject) => {
        resolve = _resolve;
        reject = _reject;
    })

    promise.resolve = resolve;
    promise.reject = reject;

    return promise;
}