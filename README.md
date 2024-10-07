# FetchProject - Android Application

This project is an Android application that fetches a list of items from a remote API and displays them in a **RecyclerView**. The application follows the **MVVM (Model-View-ViewModel)** architectural pattern with **LiveData**, **ViewModel**, and **Repository** layers to ensure separation of concerns and maintainable code.

## Features
- Fetches a list of items from a remote JSON API.
- Displays the list in a RecyclerView, formatted with string resources.
- Uses ViewModel and LiveData for data management.
- ViewBinding is used to interact with views, ensuring type-safe access to UI elements.
- Follows clean architecture principles.

## Architectural Overview

The project is structured following the **MVVM (Model-View-ViewModel)** pattern, where:

- **Model**: Contains the business logic and data manipulation code (in this case, the use case and repository).
- **ViewModel**: Acts as the intermediary between the Model and the View, providing data to the View in a lifecycle-aware manner using LiveData.
- **View**: The UI layer (Activity) that observes the ViewModel for changes and updates the UI accordingly. In this case, the `RecyclerView` displays the list of items.

This structure ensures that:
- The **ViewModel** holds and manages UI-related data in a lifecycle-conscious way, ensuring that the UI always displays the most recent data.
- The **Repository** is responsible for abstracting the data sources (remote APIs), making the data-fetching logic reusable and easier to test.
- **LiveData** is used to observe data changes in a reactive way, ensuring that the UI stays in sync with data changes.

### Layered Breakdown

1. **Data Layer**:
   - **Repository**: The `ItemRepositoryImpl` class fetches the data from the API using Retrofit and provides it to the ViewModel through a clean interface.
   - **Retrofit**: Retrofit is used to make network requests and parse the JSON response into `Item` objects.
   
2. **Domain Layer**:
   - **Use Case**: The `GetItemsUseCase` class encapsulates the business logic. It filters and sorts the list of items fetched from the repository, removing items with blank or null names and sorting by `listId` and `name`.
   - **Model**: The `Item` data class represents the structure of the item object, including fields like `id`, `listId`, and `name`.

3. **Presentation Layer**:
   - **ViewModel**: The `ItemViewModel` class is responsible for fetching the list of items using the `GetItemsUseCase` and exposing the data as LiveData to the `MainActivity`. The ViewModel handles the business logic of when to fetch data, and updates the UI via LiveData.
   - **View (MainActivity)**: The `MainActivity` observes the LiveData provided by the `ItemViewModel` and updates the `RecyclerView` with the list of items whenever the data changes.
   - **Adapter (ItemAdapter)**: The `ItemAdapter` is responsible for binding the `Item` objects to the `RecyclerView` using ViewBinding and displaying the item data (formatted using string resources).

## Class Overview

### 1. `MainActivity`
- **Purpose**: The main UI component that displays the list of items fetched from the API in a RecyclerView.
- **Responsibilities**: 
  - Observes the `ItemViewModel` LiveData and updates the UI when the data changes.
  - Initializes and manages the `RecyclerView` and its adapter.
  - Uses ViewBinding to interact with the layout in a type-safe manner.

### 2. `ItemViewModel`
- **Purpose**: Manages the UI-related data and handles the interaction between the View and the domain logic.
- **Responsibilities**:
  - Calls the `GetItemsUseCase` to fetch and process the list of items.
  - Exposes the list of items via LiveData to ensure the View is updated reactively.
  - Ensures data is managed in a lifecycle-aware way, preventing issues such as memory leaks.

### 3. `ItemAdapter`
- **Purpose**: Binds the list of `Item` objects to the `RecyclerView` and formats the item data for display using string resources.
- **Responsibilities**:
  - Takes a list of items and a context (to access string resources).
  - Uses ViewBinding to bind the item views to the data.
  - Formats the item’s `listId` and `name` using Android string resources.

### 4. `GetItemsUseCase`
- **Purpose**: Encapsulates the business logic for fetching and processing the list of items.
- **Responsibilities**:
  - Retrieves the list of items from the repository.
  - Filters out any items with blank or null names.
  - Sorts the list of items by `listId` and `name` before passing it to the ViewModel.

### 5. `ItemRepositoryImpl`
- **Purpose**: Implements the data-fetching logic and interacts with the remote API using Retrofit.
- **Responsibilities**:
  - Provides the list of items to the `GetItemsUseCase`.
  - Uses Retrofit to make network calls and retrieve data from the provided API.
  - Decouples the data source from the rest of the app, making it easier to swap or mock data sources if needed.

### 6. `RetrofitInstance`
- **Purpose**: Provides a singleton instance of Retrofit to handle network requests.
- **Responsibilities**:
  - Configures Retrofit to use the base API URL and the Gson converter for parsing JSON responses.
  - Ensures that the network setup is centralized and easy to maintain.

### 7. `Item`
- **Purpose**: The data model representing an item in the list.
- **Responsibilities**:
  - Holds the `id`, `listId`, and `name` fields representing the data fetched from the API.
  - Used throughout the data, domain, and presentation layers as the standard data object.

## Conclusion

This project demonstrates a clean and modular approach to building an Android app using the MVVM architecture. By separating the responsibilities of data fetching, business logic, and UI management, the code is easier to maintain, test, and extend. The use of **ViewModel** and **LiveData** ensures that the app responds efficiently to data changes, while **Retrofit** simplifies network operations.
