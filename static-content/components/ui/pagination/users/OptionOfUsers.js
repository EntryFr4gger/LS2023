import {option} from "../../../dom/domTags.js";

export async function OptionOfUsers(user) {
    return await option({"data-value": `${user.id}`}, `${user.email}`)
}