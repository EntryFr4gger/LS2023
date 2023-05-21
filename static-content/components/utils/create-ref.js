export function createRef() {
    let resolve, reject;

    let promise = new Promise((_resolve, _reject) => {
        resolve = _resolve;
        reject = _reject;
    })

    promise.resolve = resolve;
    promise.reject = reject;

    return promise;
}