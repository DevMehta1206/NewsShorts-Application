import com.android.build.gradle.internal.packaging.defaultExcludes

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    kotlin("kapt")
//    alias(libs.plugins.daggerHilt)
    id("com.google.dagger.hilt.android")

}

android {
    namespace = "com.shot.newsshorts"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.shot.newsshorts"
        minSdk = 28
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
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
            excludes += "META-INF/gradle/incremental.annotation.processors"
        }
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // hilt
    kapt(libs.hiltCompiler)
    implementation(libs.hilt.android)
    kapt(libs.hiltAndroidCompiler)
    implementation(libs.hiltNavigationCompose)

    //navigation
    implementation(libs.androidx.navigation.compose)
//    retrofit
    implementation(libs.retrofit)
    //okhttp
    implementation(libs.okhttp)
    // gsonConvertor
    implementation(libs.converter.gson)
    //moshi
    implementation(libs.moshi.kotlin)
    //moshiConverter
    implementation(libs.converter.moshi)
    //loggingInterceptor
    implementation(libs.logging.interceptor)
    //courotines
    implementation (libs.kotlinx.coroutines.core)
    implementation (libs.kotlinx.coroutines.android)
    //splash screen
    implementation(libs.androidx.core.splashscreen)
    //coil
    implementation(libs.coil.compose)
    //room
    implementation(libs.androidx.room.runtime)
    kapt (libs.androidx.room.compiler)
    implementation (libs.androidx.room.paging)
    implementation (libs.androidx.room.ktx)

    //kotlinx serialization
    implementation (libs.kotlinx.serialization.json)
    //pagging
    implementation(libs.androidx.paging.compose)
     //local packages
    implementation(project(":utilities"))
}


kapt{
    correctErrorTypes = true
}
