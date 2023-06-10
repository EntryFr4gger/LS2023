import {userIdStorage} from "../../components/utils/get-user.js";
import {hashChangeLoc} from "../../components/utils/hash-change-loc.js";
import {LoginUserFetch} from "../../components/api/fetch/users/LoginUserFetch.js";
import LoginPage from "../../pages/users/LoginPage.js";
import {userTokenStorage} from "../../components/utils/get-token.js";
import {DisableAttribute} from "../../components/utils/disable-attribute.js";

async function LoginHandler() {

    async function loginUser(event) {
        event.preventDefault()

        const email = document.getElementById("register-email").value
        const password = document.getElementById("register-password").value

        DisableAttribute(event.target[3])

        const response =
            await LoginUserFetch(email, password)

        const json = await response.json()

        if(response.ok){
            localStorage.setItem(userIdStorage, json.id)
            localStorage.setItem(userTokenStorage, `Bearer ${json.token}`)

            hashChangeLoc(`#`)
        }
        else{
            alert(json.error)
        }
    }

    return LoginPage(loginUser)
}

export default LoginHandler;