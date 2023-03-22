import java.util.Properties

plugins {
    id("org.jetbrains.kotlin.android")
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.godminq.dogcat"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.godminq.dogcat"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 4
        versionName = "1.3"

        testInstrumentationRunner  = "androidx.test.runner.AndroidJUnitRunner"

//        buildConfigField("String", "THE_DOG_API_ACCESS_KEY", "\"" + getTheDogApiAccess() + "\"")
//        buildConfigField("String", "THE_CAT_API_ACCESS_KEY", "\"" + getTheCatApiAccess() + "\"")

//        buildConfigField("String", "THE_DOG_API_ACCESS_KEY", "\"${the_dog_api_access_key}\"")
//        buildConfigField("String", "THE_CAT_API_ACCESS_KEY", "\"${the_cat_api_access_key}\"")

        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").inputStream())
        buildConfigField("String", "THE_CAT_API_ACCESS_KEY",
            properties.getProperty("the_cat_api_access_key")
        )
        properties.load(project.rootProject.file("local.properties").inputStream())
        buildConfigField("String", "THE_DOG_API_ACCESS_KEY",
            properties.getProperty("the_dog_api_access_key")
        )

        // @InstallIn 무시 코드
        javaCompileOptions {
            annotationProcessorOptions {
                arguments["dagger.hilt.disableModulesHaveInstallInCheck"] = "true"
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"

        freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
        freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlinx.coroutines.FlowPreview"
    }
    buildFeatures {
        dataBinding = true
    }

}

dependencies {

    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    /*
       implementation(androidx.core:core-ktx:1.7.0)
       implementation(androidx.appcompat:appcompat:1.5.1)
       implementation(com.google.android.material:material:1.7.0)
       implementation(androidx.constraintlayout:constraintlayout:2.1.4)
       testImplementation(junit:junit:4.13.2)
       androidTestImplementation(androidx.test.ext:junit:1.1.3)
       androidTestImplementation(androidx.test.espresso:espresso-core:3.4.0)
        */

    // 추가한 부분

    kapt(libs.androidx.room.compiler)
    kapt(libs.hilt.android.compiler)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.paging.runtime.ktx)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.viewpager2)
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.material)
    implementation(libs.gson)
    implementation(libs.okhttp3.logging.interceptor)
    implementation(libs.retrofit2.converter.gson)
    implementation(libs.retrofit2)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.hilt.android)
    implementation(libs.androidx.profileinstaller)
    implementation(libs.androidx.tracing.ktx)

    // Compose
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.constraintlayout.compose)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.ui.viewbinding)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.runtime.livedata)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.material.compose.theme.adapter)
    implementation(libs.coil.compose)
    debugImplementation(libs.androidx.compose.ui.tooling)
}

//fun getTheDogApiAccess(): String? {
//    return project.findProperty("the_dog_api_access_key") as? String
//}
//fun getTheCatApiAccess(): String? {
//    return project.findProperty("the_cat_api_access_key") as? String
//}