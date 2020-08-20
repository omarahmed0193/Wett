buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath (Libs.com_android_tools_build_gradle)
        classpath (Libs.kotlin_gradle_plugin)
    }
}

plugins {
    id("de.fayard.buildSrcVersions") version "0.7.0"
}

allprojects {
    repositories {
        google()
        jcenter()
        maven(url = "https://jitpack.io")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}