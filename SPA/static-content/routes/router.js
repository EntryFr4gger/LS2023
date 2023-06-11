export function Router() {
    let defaultHandeler = null

    // Array to store route handlers
    router.handlers = [];

    function router(state) {
        return router.handleRoute(state);
    }

    // Function to handle the route based on the state
    router.handleRoute = function (state) {
        const rotueWithDetails = this.getRouteHandler(state.path)
        if (rotueWithDetails === undefined) {
            return defaultHandeler(state)
        }
        state.queryParams = rotueWithDetails.queryParams
        state.pathParams = rotueWithDetails.pathParams
        state.bodyParams = {}
        return rotueWithDetails.handler(state)
    }

    // Function to get the appropriate route handler for a given path
    router.getRouteHandler = function (path, pathParamsP = {}, queryParamsP = {}) {
        let pathParams = pathParamsP
        let queryParams = queryParamsP
        for (const handler of this.handlers) {
            if (path === "" || path === "/")
                return {
                    handler: handler.handler,
                    pathParams: pathParams,
                    queryParams: queryParams
                }

            let newPath = isPath(handler.path, path);

            if (newPath == null) {
                continue
            }

            pathParams = Object.assign(newPath.pathParams, pathParams)
            queryParams = newPath.queryParams
            if (newPath.newPath === "") {
                return {
                    handler: handler.handler,
                    pathParams: pathParams,
                    queryParams: queryParams
                }
            } else {
                const newReturn = handler.handler.getRouteHandler(newPath.newPath, newPath.pathParams, newPath.queryParams)
                return {
                    handler: newReturn.handler,
                    pathParams: newReturn.pathParams,
                    queryParams: newReturn.queryParams
                }
            }

        }
        return undefined
    }

    // Function to add a route handler to the router
    router.addRouteHandler = function (path, handler) {
        this.handlers.push({path, handler})
    }

    // Function to set the default not found route handler
    router.addDefaultNotFoundRouteHandler = function (defaultH) {
        defaultHandeler = defaultH
    }

    return router;
}

export default Router()

function isPath(handlerPath, path) {
    // Check if the path does not start with the initial part of the handlerPath or if the handlerPath is "/"
    if (!path.startsWith(handlerPath.split(":")[0]) || handlerPath === "/") {
        return null
    }
    const pathParams = {};
    const queryParams = {};
    const query = path.split("?")
    const pathSlice = path.split("/").slice(1); // remove the first empty string
    let newPath = path.replace(handlerPath, '')

    // Check if the length of pathSlice is not equal to the length of the initial part of handlerPath and if the path does not start with handlerPath
    if (pathSlice.length !== handlerPath.split("/").slice(1).length && !path.startsWith(handlerPath)) {
        return null
    }

    // Extract query parameters from the path
    if (query.length > 1) {
        const queries = query[1].split("&")
        console.log(queries)
        for (const pair of queries) {
            const [name, value] = pair.split("=");
            queryParams[name] = value;
        }
    }

    // Extract path parameter from the pathSlice
    if (pathSlice.length >= 1) {
        const pathParamName = handlerPath.split(":")[1]
        if (pathParamName) {
            pathParams[pathParamName.split("/")[0]] = pathSlice[0].split("?")[0]
            newPath = ""
        }
    }

    return {newPath, pathParams, queryParams};
}
