plugins {
    id(Plugins.androidApplication)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
    id(Plugins.kotlinParcelize)
    id(Plugins.kt_lint) version Versions.KT_LINT
}

android {
    namespace = ProjectProperties.APPLICATION_ID
    compileSdk = 33

    defaultConfig {
        applicationId = ProjectProperties.APPLICATION_ID
        minSdk = ProjectProperties.MIN_SDK_VERSION
        targetSdk = ProjectProperties.TARGET_SDK_VERSION
        versionCode = ProjectProperties.VERSION_CODE
        versionName = ProjectProperties.VERSION_NAME

        testInstrumentationRunner = ProjectProperties.TEST_RUNNER
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName(ProjectProperties.APPLICATION_BUILD_TYPE) {
            isMinifyEnabled = ProjectProperties.IS_MINIFY_ENABLE
            proguardFiles(getDefaultProguardFile(ProjectProperties.PROGUARD_NAME), ProjectProperties.PROGUARD_FILE)
        }
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = ProjectProperties.BUILD_FEATURE_COMPOSE
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.COMPOSE_VERSION
    }
    packagingOptions {
        resources {
            excludes += ProjectProperties.EXCLUDES
        }
    }
}

dependencies {
    implementation(AndroidX.CORE_KTX)
    implementation(AndroidX.LIFECYCLE_KTX)
    implementation(Compose.ACTIVITY_COMPOSE)
    implementation(Compose.NAVIGATION_COMPOSE)
    implementation(Compose.UI_COMPOSE)
    implementation(Compose.UI_TOOLING_PREVIEW)
    implementation(Compose.MATERIAL_COMPOSE)
    implementation(Compose.NAVIGATION_COMPOSE)
    implementation(project(mapOf(ProjectProperties.PATH to ProjectProperties.PROJECT_PATH)))
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.activity:activity-compose:1.5.1")
    implementation(platform("androidx.compose:compose-bom:2022.10.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    // Retrofit2
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:okhttp:4.10.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.10.0")
    // Coroutine
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    testImplementation(UnitTest.JUNIT)
    androidTestImplementation(AndroidTest.ANDROID_JUNIT)
    androidTestImplementation(AndroidTest.ESPRESSO_CORE)
    androidTestImplementation(Compose.UI_TEST_JUNIT4)
    androidTestImplementation(platform("androidx.compose:compose-bom:2022.10.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation(Compose.UI_TOOLING)
    debugImplementation(Compose.UI_TEST_MANIFEST)

    implementation(project(ProjectProperties.PROJECT_PATH))
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}
