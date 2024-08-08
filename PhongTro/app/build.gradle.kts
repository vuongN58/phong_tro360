import org.gradle.configurationcache.extensions.capitalized
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.google.devtools.ksp)
    alias(libs.plugins.google.dagger.hilt)
}
apply(from = file("../auto_dimension.gradle"))

android {
    namespace = "ptit.vuong.phongtro"
    compileSdk = 34

    defaultConfig {
        applicationId = "ptit.vuong.phongtro"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(libs.androidx.legacy.support)
    implementation(libs.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.security.crypto.ktx)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.google.material)
    implementation(libs.androidx.multidex)

    // Navigation
    implementation(libs.androidx.navigation.common.ktx)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

    // Lifecycle
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.viewmodel.savedstate)
    implementation(libs.androidx.swiperefreshlayout)
    implementation(libs.androidx.recyclerview)

    // Hilt
    implementation(libs.google.dagger.hilt.android)
    implementation(libs.play.services.maps)
    implementation(libs.play.services.location)
    ksp(libs.google.dagger.hilt.android.compiler)

    // Coroutine
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)
    implementation(libs.coroutines.test)
    implementation(libs.coroutines.play.services)

    // Image loading
    implementation(libs.glide)

    // Lottie
    implementation(libs.lottie)

    // CameraX
    implementation(libs.bundles.androidx.camera)

    // QR Code Scanner, and Barcode Generator
    implementation(libs.google.mlkit.scan)
    implementation(libs.zxing)
    implementation(libs.androidx.multidex)

    implementation(libs.vcardParser)

    // Tab indicator
    implementation(libs.tbuonomo)

    // Timber
    implementation(libs.timber)

    // Utils
    implementation(libs.kotlinx.collections.immutable)

    // OKHttp
    implementation(libs.bundles.retrofit.okhttp3)
    implementation(libs.bundles.moshi)

    // PermissionX
    implementation(libs.permissionx)

    // Room Database
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    implementation(libs.work.manager.runtime)
    implementation(libs.work.manager.ktx)
    implementation(libs.stepview)

    // Debug
//  debugImplementation(libs.debug.leak)

    // Tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(libs.guava)

    implementation(libs.paging)
}