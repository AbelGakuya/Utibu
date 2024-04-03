//import org.jetbrains.kotlin.gradle.internal.kapt.incremental.UnknownSnapshot.classpath

//import org.jetbrains.kotlin.gradle.internal.kapt.incremental.UnknownSnapshot.classpath

//import org.jetbrains.kotlin.gradle.internal.kapt.incremental.UnknownSnapshot.classpath

// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
//    ext {
//        compose_version = '1.1.0'
//    }
    dependencies {
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.48")
        // classpath ("com.google.dagger:hilt-android-gradle-plugin:2.40.5")
    }
}


plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
}