/**
 * HashChangeLoc is a function that changes the hash part of the URL and dispatches a hashchange event.
 * If the provided location is different from the current hash, it updates the hash.
 * Otherwise, it dispatches a hashchange event.
 *
 * @param {string} location - The new location to set as the hash part of the URL.
 */
export function hashChangeLoc(location) {
    if (window.location.hash.replace("#", "") !== location.substring(1))
        window.location.hash = location.substring(1);
    else
        window.dispatchEvent(new HashChangeEvent("hashchange"));
}
