export function Router() {
    let defaultHandeler = null

    router.handlers = [];

    function router(state) {
            return router.handleRoute(state);
    }

    router.handleRoute = function (state) {
        const rotueWithDetails = this.getRouteHandler(state)
        if(rotueWithDetails === undefined){
            return defaultHandeler(state)
        }
        console.log(rotueWithDetails.handler)
        return rotueWithDetails.handler(state)
    }

    router.getRouteHandler = function(path,pathParamsP = {},queryParamsP = {}){
        let pathParams = pathParamsP
        let queryParams = queryParamsP
        for (const handler of this.handlers) {
            if (path === "" || path === "/")
                return {
                    handler: handler.handler,
                    pathParams:pathParams,
                    queryParams: queryParams
                }

            let newPath = isPath(handler.path, path);

            if(newPath == null){
                continue
            }

            pathParams = Object.assign(newPath.pathParams,pathParams)
            queryParams = newPath.queryParams
            if(newPath === ""){
                return {
                    handler: handler.handler,
                    pathParams:pathParams,
                    queryParams: queryParams
                }
            }
            else{
                const newReturn = handler.handler.getRouteHandler(newPath.newPath,newPath.pathParams,newPath.queryParams)
                return {
                    handler: newReturn.handler,
                    pathParams:newReturn.pathParams,
                    queryParams: newReturn.queryParams
                }
            }

        }
        return undefined
    }

    router.addRouteHandler = function (path, handler){
        this.handlers.push({path, handler})
    }
    router.addDefaultNotFoundRouteHandler = function(defaultH) {
        defaultHandeler = defaultH
    }
    return router;
}
export default Router()


//Problema: Como as rotas estão defenidas, o path desaparece antes de aparecer o parametro do path...

function isPath(handlerPath, path) {
    if (!path.startsWith(handlerPath) || handlerPath ==="/") {
        return null
    }
    const pathParams = {};
    const queryParams = {};
    const query = path.split("?")
    const pathSlice = path.split("/").slice(1); // remove the first empty string
    let newPath = path.split("/").slice(3).join("/");

    if (query.length>1) {
        const queries = query[1].split("&")
        console.log(queries)
        for (const pair of queries) {
            const [name, value] = pair.split("=");
            queryParams[name] = value;
        }
        newPath = query[0]
    }
    if(pathSlice.length>1){
        const pathParamName = handlerPath.split(":")[1]
        if (pathParamName) {
            pathParams[pathParamName] = pathSlice[1].split("?")[0]
        }
    }

    return { newPath, pathParams, queryParams };
}
