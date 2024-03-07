plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.hdsw.asimpleapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.hdsw.asimpleapp"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

val androidxCore = "1.12.0"
val androidxLifeCycle = "2.7.0"
val androidxActivityCompose = "1.8.2"
val composeNavigation = "2.7.7"
val lifecycleViewModel = "2.7.0"
val retrofit = "2.9.0"
val room = "2.6.1"
val daggerHilt = "2.49"
val daggerHiltNavigation = "1.2.0"
val daggerHiltAnnotationProcessor = "2.49"
val junit = "4.13.2"
val glide = "4.16.0"
val glideCompose = "1.0.0-beta01"
val mockito = "3.12.4"
val coroutine = "1.8.0"
val coroutineTest = "1.8.0"
val coil = "2.6.0"
val landscapistGlide = "2.3.2"

dependencies {

    implementation("androidx.core:core-ktx:$androidxCore")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$androidxLifeCycle")
    implementation("androidx.activity:activity-compose:$androidxActivityCompose")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    //coroutine
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleViewModel")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutine")

    //navigation
    implementation("androidx.navigation:navigation-compose:$composeNavigation")
    implementation("androidx.navigation:navigation-runtime-ktx:$composeNavigation")

    //retrofit
    implementation("com.squareup.retrofit2:retrofit:$retrofit")
    implementation("com.squareup.retrofit2:converter-gson:$retrofit")

    //room
    implementation("androidx.room:room-runtime:$room")
    implementation("androidx.room:room-ktx:$room")
    ksp("androidx.room:room-compiler:$room")

    //dagger-hilt
    implementation("com.google.dagger:hilt-android:$daggerHilt")
    implementation("androidx.hilt:hilt-navigation-compose:$daggerHiltNavigation")
    ksp("com.google.dagger:hilt-android-compiler:$daggerHiltAnnotationProcessor")

    //glide-landscapist
    implementation("com.github.skydoves:landscapist-glide:$landscapistGlide")

    testImplementation("junit:junit:$junit")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    testImplementation("org.mockito:mockito-core:$mockito")
    testImplementation("org.mockito:mockito-inline:$mockito")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutineTest")
    androidTestImplementation("androidx.arch.core:core-testing:2.2.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}