## Server

#### Server Layer:

When a request is first received by our server we route it to its given Http Handler via the tasks API routes, that
contains all route handlers.
We also add a logger to it so that we can examine all the requests made in the server.

#### API Layer:

When it arrives, depending on the route, it can pass through a Authorisation filter where it searches for a Bearer
token, to ensure that the request that was made should be allowed. If that is the case it places the id of the token
owner into a Request context so that it can be later used by the services in case it is needed for authentication.
Now that our request has reached its method we get all the information we need to preform the required operation and
then call a designated services method that can preform the operation.

#### Service Layer:

The service layer is divided into sections, one for each of the main code elements (users, tokens, boards, lists and
cards). Only its API section can access to the corresponding Service layer.Then received parameters are
ran through validity checks and cleaned up into the domain objects when required before the services instance a
connection in order to communicate with the database

### Connection Management

For our connections we have a method included in the data storage interface called run. The run function provides a
higher-order function that helps you access a database by encapsulating a database connection, error handling, and
resource management. It instances a connection and gives us a transaction scope where we can run code that calls the
database methods, to alter it. If something goes wrong it also rollbacks or if went accordingly it crashes.

### Data Access

We made a module for each table in the database to make sure every query was logically separated.
Additionally, because have a unit of work, our queries for a certain route are handeled inside a single connection,
that is only refreshed when we make a new call to the API

### Error Handling/Processing

For our error handling we elected to use error propagation where we have a function called `errorCatcher` is ran every
time we call a route. Its job is to catch any error that happens during processing of requests, and returning a response
saying there was an error without crashing the server.
Because of this our errors are detailed and tailor-made for every situation.
