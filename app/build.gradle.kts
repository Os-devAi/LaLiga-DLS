plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.nexusdev.laligadls"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.nexusdev.laligadls"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.6.0"))
    // FirebaseUI for Firebase Auth
    implementation("com.firebaseui:firebase-ui-auth:8.0.2")
    // Import the BoM for the Firebase platform
    implementation(platform("com.google.firebase:firebase-bom:32.2.3"))
    // Declare the dependency for the Cloud Firestore library
    implementation("com.google.firebase:firebase-firestore-ktx:24.9.1")
    // Declare the dependency for the Realtime Database library
    implementation("com.google.firebase:firebase-database-ktx:20.3.0")
    // Declare the dependencies for the Firebase Cloud Messaging and Analytics libraries
    implementation("com.google.firebase:firebase-messaging:23.3.1")
    implementation("com.google.android.gms:play-services-auth:20.7.0")
    implementation ("com.google.firebase:firebase-storage:20.0.1")
    implementation("com.google.firebase:firebase-firestore:24.9.1")


    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    //Volley
    implementation("com.android.volley:volley:1.2.1")

    //Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")

    //ImageGif
    implementation("pl.droidsonroids.gif:android-gif-drawable:1.2.28")

    //Blurry Library
    implementation("io.coil-kt:coil:2.4.0")

    //MaterialDesign
    implementation("com.google.android.material:material:1.11.0-alpha02")
    implementation("androidx.appcompat:appcompat:1.7.0-alpha03")

    //para pedir permisos
    implementation("androidx.activity:activity-ktx:1.9.1")
    implementation("androidx.fragment:fragment-ktx:1.8.2")
}