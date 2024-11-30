plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.devtools.ksp)
    alias(libs.plugins.jetbrains.kotlin.plugin.compose)
    alias(libs.plugins.google.dagger.hilt.android)
}

android {
    namespace = "hu.bme.aut.android.receptes_konyv"
    compileSdk = 34

    defaultConfig {
        applicationId = "hu.bme.aut.android.receptes_konyv"
        minSdk = 26
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
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)

    //Compose Bill of Materials
    implementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(platform(libs.androidx.compose.bom))
    debugImplementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    //Lifecycle
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    //Kotlin Extensions DateTime - LocalDate
    implementation(libs.kotlinx.datetime)

    //Compose Navigation
    implementation(libs.androidx.navigation.compose)

    //Material Icons
    implementation(libs.androidx.material.icons.extended)

    // Room
    implementation(libs.androidx.room.runtime)
    debugImplementation(libs.ui.tooling)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    // To use java.time lib
    coreLibraryDesugaring(libs.desugar.jdk.libs)

    // Hilt
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    ksp(libs.hilt.compiler)

    //Preferences
    implementation("androidx.datastore:datastore-preferences:1.0.0")
}