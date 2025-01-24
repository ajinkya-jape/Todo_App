# Todo App with Jetpack Compose and Room

This is a simple Todo App built using Jetpack Compose and Room Database, showcasing modern Android development practices like state management with ViewModel,
smooth navigation between screens, and persistent data storage.

### Features
- Add TODOs: Easily add new tasks to your list.
- Search TODOs: Quickly search through your TODO list using a search bar.
- View TODOs: Display all your tasks in a clean and modern UI.
- Local Storage: Store and retrieve TODO items using Room Database.
- Navigation: Seamless screen transitions with proper back-handling.

### Architecture
 - MVVM (Model-View-ViewModel): For separation of concerns and better testability.

### Key Libraries
- Jetpack Compose: For building responsive, declarative UIs.
- Room Database: For efficient and persistent local storage.
- StateFlow: For reactive and stateful data flow management.
- Coroutines: For managing asynchronous operations seamlessly.

### File Structure
- Database:
  - TodoDatabase: Handles database creation and configuration.
  - TodoDao: Interface for database operations like inserting and querying TODOs.

- Ui(Components):
  - MainScreen: Displays the list of TODOs with provides a search feature.
  - AddTodoScreen: Allows users to create new TODO items.
  - MainActivity: Entry point of the app.

- ViewModel
  - TodoViewModel: Manages app state, data flow, and business logic.
