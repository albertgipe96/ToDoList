plugins {
    alias(libs.plugins.todolist.android.library.compose)
}

android {
    namespace = "com.development.tasks.ui"
}

dependencies {
    implementation(projects.tasks.domain)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}