# Summary
The Product Browser App is a Kotlin Multiplatform mobile application built using Compose Multiplatform. It allows users to browse and search products using the DummyJSON public API.

## Architecture
Clean architecture with three layers:

Data

Domain

Presentation

## Features
✔ Product list

✔ Product details

✔ Search functionality

✔ Search by Category functionality

✔ Android + iOS support

✔ Shared UI using Compose

## Technologies

* Kotlin Multiplatform

* Compose Multiplatform

* Ktor

* kotlinx.serialization

* Coroutines

* StateFlow



### Build and Run Android Application

To build and run the development version of the Android app, use the run configuration from the run widget
in your IDE’s toolbar or build it directly from the terminal:
- on macOS/Linux
  ```shell
  ./gradlew :composeApp:assembleDebug
  ```
- on Windows
  ```shell
  .\gradlew.bat :composeApp:assembleDebug
  ```

### Build and Run iOS Application

To build and run the development version of the iOS app, use the run configuration from the run widget
in your IDE’s toolbar or open the [/iosApp](./iosApp) directory in Xcode and run it from there.

---

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…