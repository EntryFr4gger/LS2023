import {hashChangeLoc} from "../../components/utils/hash-change-loc.js";
import {LoginUserFetch} from "../../components/api/fetch/users/LoginUserFetch.js";
import LoginPage from "../../pages/users/LoginPage.js";
import {DisableAttribute} from "../../components/utils/disable-attribute.js";
import {setUser} from "../../components/utils/storage/set-user.js";
import {setToken} from "../../components/utils/storage/set-token.js";

/**
 * LoginHandler is an asynchronous function that handles the login functionality.
 *
 * @returns {HTMLElement} The login page component.
 */
async function LoginHandler() {

    /**
     * loginUser is an asynchronous function that handles the login process.
     *
     * @param {Event} event - The event object triggered by the login action.
     */
    async function loginUser(event) {
        event.preventDefault()

        const email = document.getElementById("register-email").value
        const password = document.getElementById("register-password").value

        DisableAttribute(event.target[2])

        const userInfo = await LoginUserFetch(email, password)


        if (userInfo) {
            setUser(userInfo.id)
            setToken(userInfo.token)

            hashChangeLoc(`#`)
        }
    }

    return LoginPage(loginUser)
}

export default LoginHandler;