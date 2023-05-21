# Phase 3

## Introduction

For the first phase of the LS project (Tasks) we have designed and implemented 4 differente modules:

- Database: Contains two database implementations, one for a psql database and another one for a database stored in the
  code memory, including aswell the necessary interfaces to implement more.
- Domain: Contains the domain objects, used to represent the code structures
- Services: Contains the Business logic of the code.
- API: Contains a possible API implementation for the tasks application

## Authors

48315 - Rafael Costa - Dragoral
47539 - Bernardo Serra - bfmserra
48268 - Marçorio Fortes - marsul1

## Modeling the database

### Conceptual model ###

The following diagram holds the Entity-Relationship model for the information managed by the system.

![EA Model](https://github.com/isel-leic-ls/2223-2-LEIC41N-G01/blob/main/src/main/kotlin/pt/isel/ls/tasks/docs/EAModel.png?raw=true)
We highlight the following aspects:

* Cards has a weak relationship with list because, although a card always has to be part of a board it doesn't always
  need to be in a list.
* Since both cards and lists can only be in one board they have a one to many relationship

The conceptual model has the following restrictions:

* Each board has a unique name
* Each card must belong to a list and a board, except if it is archived.
* Each list must belong to a board and has a unique name within that board
* Although card can have a dueDate it doesn't require it

### Physical Model ###

Our database scheme was
defined [here](https://github.com/isel-leic-ls/2223-2-LEIC41N-G01/blob/main/src/main/sql/createSchema.sql)

![Physical Model](https://github.com/isel-leic-ls/2223-2-LEIC41N-G01/blob/main/src/main/kotlin/pt/isel/ls/tasks/docs/DBDiagram.png?raw=true)

We highlight the following aspects of this model:

* The `Cards` table has a `dueDate` column with a regular expression check to ensure it is a valid date/time string,
  which could be useful for tracking deadlines or scheduling tasks.
* The `Tokens` table is used to store only bearer tokens for users, and that is why it has only 36 chars of space
* The `Cards` table has both a `list_id` and a `board_id` because a card should belong in a list and always be part of a
  certain board

## Backend Organization

### Open-API Specification ###

In
our [Open-API](https://github.com/isel-leic-ls/2223-2-LEIC41N-G01/blob/main/src/main/kotlin/pt/isel/ls/tasks/docs/api.yaml)
specification, we highlight the following aspects:

- Currently it is not possible to access it via our API due to time constraints, but it is possible to test it with the
  necessary plugins
- Cors was enabled in order to call Open API executions

### Request Details

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
cards). Only the each API section only access to the corresponding Service layer. When here the received parameters are
ran through validity checks and cleaned up into the domain objects when required before the services instance a
connection in order to communicate with the database

### Connection Management

For our connections we have a method included in the data storage interface called run. The run function provides a
higher-order function that helps you access a database by encapsulating a database connection, error handling, and
resource management. It instances a connection and gives us a transaction scope where we can run code that calls the
database methods, to alter it. If something goes wrong it also roll backs or if went accordingly it crashes.

### Data Access

We made a module for each table in the database to make sure every query was logically separated.
In every query we used the Prepared statement feature so we could pre compile an SQL statement and then execute it
multiple times with different parameters. We then set the values of the parameters by calling set "Type"()

Some interesting SQL statements we used were made in the in the user data Postgre file, where we had to join several
data tables to validate requests like validateResquestList.

### Error Handling/Processing

For our error handling we elected to use error propagation where we have a function called `errorCatcher` is ran every
time we call a route. Its job is to catch any error that happens during processing of requests, and returning a response
saying there was an error without crashing the server.

## SPA Organization

### Layers

In a our SPA organisation consists of three main layers: pages, handlers, and the main app.

1. **Pages**: Represent the different views/screens in our application, responsible for rendering content and handling
   user interactions.

2. **Handlers**: Bridge between user actions and underlying logic/data manipulation, listening for user input and
   triggering appropriate actions or functions.

3. **Main App**: Entry point of your SPA, coordinates application flow, sets up routing, and manages shared state
   between components/pages.

These layers work together to provide the user interface and functionality of our SPA, organising and separating
concerns for easier development and maintenance.

### Main App

The main App of the SPA and includes the following functionality:

- Sets up the router and defines route handlers.
- Defines the `App` function for rendering components based on the current route.
- Registers event listeners for page loading and hash changes.
- Creates a state object based on the current hash.
- Handles rendering of components and error handling.
- Provides a `render` function to update the DOM.

Overall, the app sets up routing, handles component rendering, and manages state changes in the SPA.

### Handlers

The handlers in our codebase are essential for processing requests, interacting with APIs, and rendering components or
pages based on specific functionalities. They follow a standardized structure, extracting data from the `state` object
and performing validations. Handlers communicate with calls costum made fetch modules, which make API calls, handle
errors, and ensure a smooth user experience.

Our modular approach to handling functionalities enables code reusability and maintainability. By encapsulating specific
tasks within handlers, we can modify or extend functionalities without affecting other parts of the application.

Handlers play a crucial role in processing requests, interacting with APIs, and rendering components. They demonstrate
our ability to design and implement a robust system that effectively addresses user requirements.

### Pages

The pages section of our codebase is responsible for rendering the user interface and presenting views of our
application. Each page is designed using HTML-like tags from our custom `domTags` module. They incorporate event
handlers and callbacks for user interactions, promoting code readability and maintainability. Our pages demonstrate
proficiency in UI design, handling user interactions, and presenting information effectively.

## Critical Evaluation

There here are some functionalities that didn't make it due to time constraints:

* Cannot unarchive a card via the SPA
* Drag and drop of the cards
* Further testing on all API routes

We also hope to improve:

* General beauty of the website
* Clean up the API calls, and their inner workings
