# SDFleet
Android app that displays a list of Shipments from a local JSON file using Compose, ViewModel and Unit Tests with JUnit, Mockito and Robolectric.

<img src="https://user-images.githubusercontent.com/8674414/208271751-ed1120e9-ab22-40c5-af81-1eed37265fd2.png" width="400" />

# App architecture
<img src="https://user-images.githubusercontent.com/8674414/208271967-51405fcb-25ce-4a07-bd98-e45e4a670dc6.png" width="300" />
The app uses Google's recommended MVVM architecture pattern. The app is a single module with a _package-by-layer_ approach.

- datasource: This package contains the classes responsibles for fetching the data from the JSON local file.
- di: This package contains classes to be used by Hilt to satisfy the app's classes dependencies.
- domain: This package contains the classes that perform the app's business logic (use cases).
- model: This package contains Kotlin data classes that represent data used by the app.
- repository: This lpackage contains the class that uses the datasource classes to provide a single source of truth for the app data.
- ui: This package contains all the user interface clases like Activities, Jetpack Compose components and ViewModels.
- utils: This package contains extension functions and helper functions for the app.

# Running the project

- Clone this project
- Open this project with **Android Studio Dolphin | 2021.3.1 Patch 1** _or superior_
- Trust the project
- Connect a device with "USB debugging" enabled or start an emulator
- Press "run"

# Running tests
- Clone this project
- Open this project with **Android Studio Dolphin | 2021.3.1 Patch 1** _or superior_
- Trust the project
- Left click (options + click on Mac) on the _me.alfredobejarano.sdfleet (test)_ folder in the **project navigation view**.
- Click the _'Run tests in...'_ option in the dropdown menu
