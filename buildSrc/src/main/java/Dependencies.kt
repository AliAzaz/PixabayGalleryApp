object CoroutinesDeps {
    const val coroutineCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${DependenciesVersions.coroutinesVersion}"
    const val coroutineAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${DependenciesVersions.coroutinesVersion}"
}

object Photos {
    const val glidePhoto = "com.github.bumptech.glide:glide:${DependenciesVersions.glideVersion}"
    const val glideCompilerPhoto =
        "com.github.bumptech.glide:compiler:${DependenciesVersions.glideVersion}"
}

object Recycler {
    const val recycler =
        "androidx.recyclerview:recyclerview:${DependenciesVersions.recyclerview_version}"
}

object OtherDeps {
    const val multiStateView =
        "com.github.Kennyc1012:MultiStateView:${DependenciesVersions.multiStateViewVersion}"
    const val flexBox =
        "com.google.android:flexbox:${DependenciesVersions.flexBox}"
}

object Network {
    const val retrofit = "com.squareup.retrofit2:retrofit:${DependenciesVersions.retrofitVersion}"
    const val retrofitConverter =
        "com.squareup.retrofit2:converter-gson:${DependenciesVersions.retrofitVersion}"
    const val gson = "com.google.code.gson:gson:${DependenciesVersions.gsonVersion}"
    const val okHTTP =
        "com.squareup.okhttp3:logging-interceptor:${DependenciesVersions.okhttpVersion}"
}

object Lifecycle {
    const val lifecycleExt =
        "androidx.lifecycle:lifecycle-extensions:${DependenciesVersions.lifecycleExtVersion}"
    const val viewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${DependenciesVersions.lifecycleVersion}"
    const val lifecycleRuntime =
        "androidx.lifecycle:lifecycle-runtime-ktx:${DependenciesVersions.lifecycleVersion}"
    const val lifecycleOwner =
        "androidx.lifecycle:lifecycle-process:${DependenciesVersions.lifecycleVersion}"
    const val liveData =
        "androidx.lifecycle:lifecycle-livedata-ktx:${DependenciesVersions.lifecycleVersion}"
}

object Layouts {
    const val sdp =
        "com.intuit.sdp:sdp-android:${DependenciesVersions.sdpVersion}"
    const val constraint =
        "androidx.constraintlayout:constraintlayout:${DependenciesVersions.constraintVersion}"
    const val material =
        "com.google.android.material:material:${DependenciesVersions.materialVersion}"
}

object AndroidApp {
    const val appCompat =
        "androidx.appcompat:appcompat:${DependenciesVersions.supportLibraryVersion}"
    const val coreKTX = "androidx.core:core-ktx:${DependenciesVersions.ktxVersion}"
    const val stringLANG = "commons-lang:commons-lang:${DependenciesVersions.stringlangVersion}"
    const val fragment = "androidx.fragment:fragment-ktx:${DependenciesVersions.fragmentVersion}"
    const val legacySupport =
        "androidx.legacy:legacy-support-v4:${DependenciesVersions.legacySupportVersion}"
}

object Testing {
    const val junit = "junit:junit:${DependenciesVersions.junitVersion}"
    const val mockWebServer =
        "com.squareup.okhttp3:mockwebserver:${DependenciesVersions.okhttpVersion}"
    const val coroutine =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${DependenciesVersions.coroutineTestVersion}"
    const val mockito =
        "org.mockito:mockito-core:${DependenciesVersions.mockitoVersion}"
    const val mockk =
        "io.mockk:mockk:${DependenciesVersions.mockkVersion}"
    const val androidArcCore =
        "androidx.arch.core:core-testing:${DependenciesVersions.androidArcCoreVersion}"
}

object AndroidTesting {
    const val junitImpl = "androidx.test.ext:junit:${DependenciesVersions.junitImplVersion}"
    const val expresso =
        "androidx.test.espresso:espresso-core:${DependenciesVersions.expressoVersion}"
    const val mockito =
        "org.mockito:mockito-android:${DependenciesVersions.mockitoAndroidVersion}"
    const val mockk =
        "io.mockk:mockk-android:${DependenciesVersions.mockkAndroidVersion}"
    const val junit =
        "androidx.test.ext:junit:${DependenciesVersions.junitAndroidVersion}"
}

object DI {
    const val dagger = "com.google.dagger:dagger:${DependenciesVersions.daggerVersion}"
    const val daggerAndroid =
        "com.google.dagger:dagger-android:${DependenciesVersions.daggerVersion}"
    const val daggerAndroidSupport =
        "com.google.dagger:dagger-android-support:${DependenciesVersions.daggerVersion}"
    const val daggerCompilerKapt =
        "com.google.dagger:dagger-compiler:${DependenciesVersions.daggerVersion}"
    const val daggerAndroidKapt =
        "com.google.dagger:dagger-android-processor:${DependenciesVersions.daggerVersion}"
}

object Navigation {
    const val navigationUI =
        "androidx.navigation:navigation-ui-ktx:${DependenciesVersions.navigationVersion}"
    const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${DependenciesVersions.navigationVersion}"
}
