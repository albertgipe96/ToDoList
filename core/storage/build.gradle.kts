plugins {
    alias(libs.plugins.todolist.android.library)
    alias(libs.plugins.todolist.room)
}

android {
    namespace = "com.development.core.storage"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}