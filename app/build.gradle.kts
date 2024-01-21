plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "mazen.abdelgawad.news"
    compileSdk = 34

    defaultConfig {
        applicationId = "mazen.abdelgawad.news"
        minSdk = 21
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementLifecycleExtensions()
    implementNavigationComponent()
    implementRecyclerView()
    implementGlide()
    implementCoroutines()
    implementRetrofit()
    implementRoom()
    implementHilt()
    implementShimmer()
}

fun DependencyHandlerScope.implementLifecycleExtensions() {
    val lifecycleExtensionsVersion = "2.7.0"
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleExtensionsVersion")
    implementation("androidx.lifecycle:lifecycle-process:$lifecycleExtensionsVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleExtensionsVersion")
}

fun DependencyHandlerScope.implementNavigationComponent() {
    val navVersion = "2.7.6"
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")
}

fun DependencyHandlerScope.implementRecyclerView() {
    val recyclerViewVersion = "1.3.2"
    val cardViewVersion = "1.0.0"
    implementation("androidx.recyclerview:recyclerview:$recyclerViewVersion")
    implementation("androidx.cardview:cardview:$cardViewVersion")
}

fun DependencyHandlerScope.implementGlide() {
    val glideVersion = "4.16.0"
    implementation("com.github.bumptech.glide:glide:$glideVersion")
    annotationProcessor("com.github.bumptech.glide:compiler:$glideVersion")
}

fun DependencyHandlerScope.implementCoroutines() {
    val coroutinesVersion = "1.6.4"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")
}

fun DependencyHandlerScope.implementRetrofit() {
    val retrofitVersion = "2.9.0"
    val moshiVersion = "1.14.0"
    val okhttpVersion = "5.0.0-alpha.2"

    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")

    implementation("com.squareup.moshi:moshi:$moshiVersion")
    implementation("com.squareup.moshi:moshi-kotlin:$moshiVersion")
    implementation("com.squareup.retrofit2:converter-moshi:$retrofitVersion")

    implementation("com.squareup.okhttp3:okhttp:$okhttpVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:$okhttpVersion")
}

fun DependencyHandlerScope.implementRoom() {
    val roomVersion = "2.6.1"
    implementation("androidx.room:room-runtime:$roomVersion")
    annotationProcessor("androidx.room:room-compiler:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
}

fun DependencyHandlerScope.implementHilt() {
    val hiltVersion = "2.50"
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-android-compiler:$hiltVersion")
}

fun DependencyHandlerScope.implementShimmer() {
    implementation("com.facebook.shimmer:shimmer:0.5.0")
}