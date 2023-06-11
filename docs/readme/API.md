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
