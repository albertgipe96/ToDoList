# DOCUMENTATION

## BUILD-LOGIC

- Convention Plugins for different module types. Same modules will have the same base configuration.
  - AndroidApplicationConventionPlugin: App module
  - AndroidLibraryComposeConventionPlugin: Android modules using compose (ui modules)
  - AndroidLibraryConventionPlugin: Android modules without compose (data modules)
  - JvmLibraryConventionPlugin: Java/Kotlin modules (domain modules)
  - RoomConventionPlugin: Android modules using Room (storage module)

## ARCHITECTURE - MVVM/MVI

- Inside each feature module (tasks, core), divided in 3 layers: data, domain and ui.
  - Ui: Module with view features. Including Ui in Jetpack Compose and a ViewModel for each view to manage
        what is send to the screen to display through uiState and to handle user interactions
  - Domain: Module with business logic (non android references)
  - Data: Module to retrieve data from different sources

## FEATURES

- Tasks
  - tasks:ui
    - ToDoListScreen: Show tasks list
    - AddTaskScreen: Form to add task
    - TaskDetailScreen: Show task details and mark it as completed
  - tasks:domain
    - Use Cases
      - GetAllTasks: Retrieve user tasks list
      - GetTaskById: Retrieve a specific task
      - MarkTaskAsCompleted: Update a specific task to done
      - StoreNewTask: Update the user tasks list with a new one
    - Repositories
      - TaskRepository: All related to CRUD tasks (interface)
  - tasks:data
    - TaskRepositoryImpl: TaskRepository implementation using a local data source
    - Data Sources
      - LocalTaskDataSource: All related to CRUD tasks locally (interface)
      - RoomLocalTaskDataSource: LocalTaskDataSource implementation using Room

- Core
  - core:ui
    - Navigation: Needed to access NavRoutes to navigate correctly
    - Theme: Colors and Typographies
    - Ui: Common components
  - core:domain
    - Model: Common domain models
    - Result: Common response models
  - core:storage
    - All related to local storage using Room Database

# DEPENDENCY INJECTION
- Hilt
  - Each module is responsible of injecting its components

# TESTING
- Unit Testing
  - RoomLocalTaskDataSourceTest: Tests correct behavior in retrieving and storage of the local data source
  - MarkTaskAsCompletedTest: Tests correct behavior in updating task to done