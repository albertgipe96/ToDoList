plugins {
    alias(libs.plugins.todolist.android.library.compose)
}

android {
    namespace = "com.development.tasks.ui"
}

dependencies {
    implementation(projects.tasks.domain)
    implementation(projects.core.domain)
    implementation(projects.core.ui)

    implementation(libs.androidx.navigation.compose)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}