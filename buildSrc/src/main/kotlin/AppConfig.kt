import org.gradle.api.JavaVersion

object AppConfig {
    const val namespace = "com.swensonhe.weatherapp"
    const val compileSdk = 33
    const val minSdk = 24
    const val targetSdk = 33
    const val versionCode = 1
    const val versionName = "0.0.0"
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    val javaVersion = JavaVersion.VERSION_1_8
    const val jvmTarget = "1.8"
    const val ProdBaseUrl = "http://api.weatherapi.com/v1/"
    const val TestBaseUrl = "http://api.weatherapi.com/v1/"
}