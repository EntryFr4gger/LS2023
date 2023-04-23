function buttonWithRef(text, href = "/#") {
    const checkButton = document.createElement("button");
    checkButton.innerHTML = text;
    checkButton.addEventListener("click", function () {
        window.location.href = href;
    });
    return checkButton
}

export default buttonWithRef;