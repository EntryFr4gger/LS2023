export function hashChangeLoc(location) {
    if (window.location.hash.replace("#", "") !== location.substring(1))
        window.location.hash = location.substring(1);
    else
        window.dispatchEvent(new HashChangeEvent("hashchange"));
}
