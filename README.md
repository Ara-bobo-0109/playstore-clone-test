# Play Store Clone (Android)

## Overview

This project is a simplified **Google Play Store clone** built as part of a technical assessment.  
The application demonstrates **modern Android development practices**, including **Jetpack Compose**, **Clean Architecture**, and a **multi-module project structure**.

The app allows users to browse a list of apps, search by name, view app details, add new apps, and toggle install/uninstall status. All data is stored locally and persists across app restarts.

---

## Key Features

- 100% **Jetpack Compose UI**
- **Multi-module architecture**
- **Clean Architecture** (Presentation, Domain, Data)
- App list with partial text search
- App detail screen
- Add new app flow with input validation
- Install / Uninstall toggle (local state only)
- Local persistence using **Room**
- Reactive state handling with **Flow / StateFlow**
- Rounded rectangle app icons with randomly generated colors
- Proper loading, empty, and error UI states

---

## Architecture

The project follows **Clean Architecture** principles with a strict separation of concerns.

### Presentation Layer
- Jetpack Compose UI
- ViewModels using `StateFlow`
- Handles UI state and user interactions
- Depends only on the **Domain** layer

### Domain Layer
- Business logic
- Use cases
- Repository interfaces
- Pure Kotlin module (no Android dependencies)

### Data Layer
- Repository implementations
- Room database and DAO
- Entity ↔ Domain model mapping
- Implements repository interfaces defined in the Domain layer

This structure improves testability, scalability, and maintainability.

---

## Module Structure

```
root
│
├── application/               # App entry point, navigation, DI setup
│
├── core/
│   ├── domain/                # Domain models, repositories, use cases
│   └── data/                  # Room DB, DAOs, repository implementations
│
├── feature/
│   ├── appcatalog/            # App list & search feature
│   ├── addapplication/        # Add new app feature
│   └── applicationdetail/     # App detail & install/uninstall feature
│
└── build.gradle.kts
```

### Module Dependency Rules

- Feature modules depend **only** on the Domain module
- Data module implements interfaces defined in the Domain module
- Presentation layer does not depend directly on the Data layer
- Domain module has no Android dependencies

---

## Technologies & Libraries

- **Kotlin 1.9.24**
- **Java 17**
- **Android Gradle Plugin 8.5.2**
- **Jetpack Compose**
- **Compose BOM**
- **Compose Compiler 1.5.14**
- **Navigation Compose**
- **Room**
- **Kotlin Coroutines**
- **Flow / StateFlow**
- **Gradle Kotlin DSL**

---

## App Icon Implementation

Each app icon is displayed as a **rounded rectangle** with a **randomly generated color**.

- Color is generated when a new app is created
- Stored persistently in the local database
- Used consistently across list and detail screens
- Fully implemented using Jetpack Compose

---

## Local Persistence

- App metadata stored using **Room**
- Installation status stored locally
- State persists across app restarts
- UI updates reactively via `Flow`

---

## Build & Run Instructions

### Requirements
- Java 17
- Android SDK (API 34)
- Gradle (via wrapper)

### Build from Command Line

```bash
./gradlew clean assembleDebug
```

### APK Output Location

```
application/build/outputs/apk/debug/application-debug.apk
```

### Run in Android Studio (Optional)

1. Open the project in Android Studio
2. Sync Gradle
3. Run the `application` module on an emulator or physical device

---

## Notes & Implementation Decisions

- Compose BOM is used to manage Compose dependency versions consistently
- Kotlin and Compose Compiler versions are strictly aligned
- JVM target is explicitly set to Java 17 across all modules
- Configuration cache disabled during development for build stability
- Architecture prioritizes correctness, clarity, and scalability

---

## Potential Improvements

- Unit tests for domain use cases
- UI tests using Compose Testing APIs
- Paging support for large app lists
- Dark mode theming
- Improved animations and transitions
- Remote data source integration

---

## Conclusion

This project demonstrates a clean, scalable Android application using modern tools and best practices while fully satisfying the assignment requirements.

