plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}
android {
    compileSdkVersion(30)
    buildToolsVersion = "29.0.3"

    defaultConfig {
        applicationId = "com.afterapps.wett"
        targetSdkVersion(30)
        minSdkVersion(23)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            val apiKey: String = project.property("OPEN_WEATHER_API_KEY") as String
            buildConfigField("String", "OPEN_WEATHER_API_KEY", apiKey)
        }
    }

    androidExtensions {
        isExperimental = true
    }

    buildFeatures {
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    //Kotlin
    implementation(Libs.kotlin_stdlib)

    //Androidx Core
    implementation(Libs.core_ktx)

    //Androidx AppCompat
    implementation(Libs.appcompat)

    //Material Component
    implementation(Libs.material)

    //Constraint layout
    implementation(Libs.constraintlayout)

    //Navigation
    implementation(Libs.navigation_fragment_ktx)
    implementation(Libs.navigation_ui_ktx)

    //Network
    implementation(Libs.retrofit)
    implementation(Libs.logging_interceptor)
    implementation(Libs.moshi_kotlin)
    implementation(Libs.converter_moshi)

    //Threading
    implementation(Libs.kotlinx_coroutines_core)
    implementation(Libs.kotlinx_coroutines_android)

    //Life Cycle
    implementation(Libs.lifecycle_viewmodel_ktx)
    implementation(Libs.lifecycle_livedata_ktx)
    implementation(Libs.liveevent)

    //Database
    implementation(Libs.room_runtime)
    implementation(Libs.room_ktx)
    kapt(Libs.room_compiler)

    //Dependency Injection
    implementation(Libs.koin_androidx_viewmodel)

    //Android Iconics
    implementation(Libs.iconics_views)
    implementation(Libs.weather_icons_typeface)

    //Worker
    implementation(Libs.work_runtime_ktx)

    //Image Loading
    implementation(Libs.coil)

    testImplementation(Libs.junit_junit)
    androidTestImplementation(Libs.androidx_test_ext_junit)
    androidTestImplementation(Libs.espresso_core)
}