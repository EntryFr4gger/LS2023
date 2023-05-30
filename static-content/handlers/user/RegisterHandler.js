import {hashChangeLoc} from "../../components/utils/hash-change-loc.js";
import {CreateUserFetch} from "../../components/api/fetch/users/CreateUserFetch.js";
import RegisterPage from "../../pages/users/RegisterPage.js";
import {userIdStorage} from "../../components/utils/get-user.js";

async function RegisterHandler() {

    async function registerUser(event) {
        event.preventDefault()
        const firstName = document.getElementById("register-first-name").value
        const lastName = document.getElementById("register-last-name").value
        const email = document.getElementById("register-email").value
        const password = document.getElementById("register-password").value

        const response =
            await CreateUserFetch(
                `${firstName} ${lastName}`,
                email,
                password
            )

        const json = await response.json()

        localStorage.setItem(userIdStorage, json.id)
        localStorage.setItem("userToken", `Bearer ${json.token}`)

        hashChangeLoc(`#`)
    }

    return RegisterPage(registerUser)
}

export default RegisterHandler;