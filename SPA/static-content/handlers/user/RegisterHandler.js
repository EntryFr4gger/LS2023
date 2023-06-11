import {hashChangeLoc} from "../../components/utils/hash-change-loc.js";
import {CreateUserFetch} from "../../components/api/fetch/users/CreateUserFetch.js";
import RegisterPage from "../../pages/users/RegisterPage.js";
import {DisableAttribute} from "../../components/utils/disable-attribute.js";
import {setUser} from "../../components/utils/storage/set-user.js";
import {setToken} from "../../components/utils/storage/set-token.js";

/**
 * Handles the registration process.
 *
 * @returns {Function} The RegisterHandler function.
 */
async function RegisterHandler() {

    /**
     * Registers a user.
     *
     * @param {Event} event - The form submission event.
     */
    async function registerUser(event) {
        event.preventDefault()
        const firstName = document.getElementById("register-first-name").value
        const lastName = document.getElementById("register-last-name").value
        const email = document.getElementById("register-email").value
        const password = document.getElementById("register-password").value

        DisableAttribute(event.target[4])

        const userInfo =
            await CreateUserFetch(
                `${firstName} ${lastName}`,
                email,
                password
            )

        if (userInfo) {
            setUser(userInfo.id)
            setToken(userInfo.token)

            hashChangeLoc(`#`)
        }
    }

    return RegisterPage(registerUser)
}

export default RegisterHandler;