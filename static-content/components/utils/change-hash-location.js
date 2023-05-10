export function changeHashLocation(location) {
    const formatedLocation = location.substring(1);
    const currentHash = window.location.hash.replace("#", "");

    if (currentHash !== formatedLocation)
        window.location.hash = formatedLocation;
    else
        window.dispatchEvent(new HashChangeEvent("hashchange"));
}
