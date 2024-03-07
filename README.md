# Cat App

Cat App is a sample Android application built with Jetpack Compose for managing and viewing cat profiles. Users can browse through a list of cats, view detailed information about each cat, and update cat profiles.

## Author

Hildon Eduardo Lima de Paula  
Email: hildon.eduardo@gmail.com | hildon.eduardo.lima@gmail.com  
Experience: More than 12 years of experience in Android development, with expertise in Java, Kotlin, C, C++, PHP, CSS, JavaScript, and Android architecture. Worked with GloboSat, Motorola, Samsung, and financial banks.

## Features

- Display a list of cats with their basic information.
- View detailed information about each cat, including owner, tags, and creation/update dates.
- Update cat profiles with new information.

## WebService

- For this app all data was consumed from https://cataas.com

## Architecture

The app is built using the MVVM (Model-View-ViewModel) architecture pattern. MVVM separates the UI from the business logic, making the code more modular and easier to maintain. This architecture pattern also allows for easier testing and scalability.

## Dependencies

- [AndroidX Core KTX](https://developer.android.com/jetpack/androidx/releases/core): androidx.core:core-ktx:$androidxCore
- [AndroidX Lifecycle Runtime KTX](https://developer.android.com/jetpack/androidx/releases/lifecycle): androidx.lifecycle:lifecycle-runtime-ktx:$androidxLifeCycle
- [AndroidX Activity Compose](https://developer.android.com/jetpack/androidx/releases/activity): androidx.activity:activity-compose:$androidxActivityCompose
- [Jetpack Compose](https://developer.android.com/jetpack/compose): androidx.compose.ui:ui, androidx.compose.ui:ui-graphics, androidx.compose.ui:ui-tooling-preview, androidx.compose.material3:material3
- [Kotlin Coroutines Android](https://github.com/Kotlin/kotlinx.coroutines): org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutine
- [AndroidX Navigation Compose](https://developer.android.com/jetpack/androidx/releases/navigation): androidx.navigation:navigation-compose:$composeNavigation, androidx.navigation:navigation-runtime-ktx:$composeNavigation
- [Retrofit](https://square.github.io/retrofit/): com.squareup.retrofit2:retrofit:$retrofit, com.squareup.retrofit2:converter-gson:$retrofit
- [AndroidX Room](https://developer.android.com/jetpack/androidx/releases/room): androidx.room:room-runtime:$room, androidx.room:room-ktx:$room, androidx.room:room-compiler:$room
- [Dagger Hilt](https://dagger.dev/hilt/): com.google.dagger:hilt-android:$daggerHilt, androidx.hilt:hilt-navigation-compose:$daggerHiltNavigation, com.google.dagger:hilt-android-compiler:$daggerHiltAnnotationProcessor
- [Glide Landscapist](https://github.com/skydoves/Landscapist): com.github.skydoves:landscapist-glide:$landscapistGlide
- Testing dependencies: JUnit, Mockito, Kotlin Coroutines Test, Espresso, AndroidX Test

## Getting Started

To get started with Cat App, clone the repository and open it in Android Studio. Make sure you have the necessary dependencies installed and configured in your project.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
