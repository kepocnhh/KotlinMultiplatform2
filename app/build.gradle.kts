repositories {
    google()
    mavenCentral()
}

plugins {
    kotlin("multiplatform")
    id("com.android.application")
    id("org.jetbrains.compose") version Version.compose
}

kotlin {
    jvm()
    androidTarget()
    sourceSets {
        getByName("jvmMain") {
            kotlin.srcDirs("src/main/kotlin")
        }
        getByName("androidMain") {
            kotlin.srcDirs("src/android/main/kotlin")
            dependencies {
                implementation(compose.foundation)
                implementation("androidx.appcompat:appcompat:1.7.0")
            }
        }
        create("androidPhone") {
            kotlin.srcDirs("src/android/phone/kotlin")
        }
        create("androidWatch") {
            kotlin.srcDirs("src/android/watch/kotlin")
        }
    }
}

android {
    namespace = "org.kepocnhh.km" // todo
    compileSdk = Version.Android.compileSdk

    defaultConfig {
        applicationId = namespace
        minSdk = Version.Android.minSdk
        targetSdk = Version.Android.targetSdk
        versionCode = 1
        versionName = "0.0.$versionCode"
    }

    compileOptions.targetCompatibility = JavaVersion.toVersion(Version.jvmTarget)

    buildTypes {
        getByName("debug") {
            applicationIdSuffix = ".$name"
            versionNameSuffix = "-$name"
            isMinifyEnabled = false
            isShrinkResources = false
            manifestPlaceholders["appName"] = "${rootProject.name} $name"
        }
    }

    productFlavors {
        "device".also { dimension ->
            flavorDimensions += dimension
            create("phone") {
                this.dimension = dimension
            }
            create("watch") {
                this.dimension = dimension
                applicationIdSuffix = ".$name"
                versionNameSuffix = ".$name"
            }
        }
    }

    sourceSets {
        getByName("main") {
            kotlin.srcDirs("src/android/$name/kotlin")
            res.srcDirs("src/android/$name/res")
            manifest.srcFile("src/android/$name/AndroidManifest.xml")
        }
        getByName("phone") {
            kotlin.srcDirs("src/android/$name/kotlin")
            res.srcDirs("src/android/$name/res")
            manifest.srcFile("src/android/$name/AndroidManifest.xml")
        }
        getByName("watch") {
            kotlin.srcDirs("src/android/$name/kotlin")
            res.srcDirs("src/android/$name/res")
            manifest.srcFile("src/android/$name/AndroidManifest.xml")
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions.kotlinCompilerExtensionVersion = "1.5.14"
}

androidComponents.onVariants { variant ->
    val output = variant.outputs.single()
    check(output is com.android.build.api.variant.impl.VariantOutputImpl)
    output.outputFileName = listOf(
        rootProject.name,
        android.defaultConfig.versionName!!,
        variant.flavorName!!,
        variant.buildType!!,
        android.defaultConfig.versionCode!!.toString(),
    ).joinToString(separator = "-", postfix = ".apk")
}
