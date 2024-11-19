plugins {
    alias(libs.plugins.todolist.android.library)
}

android {
    namespace = "com.development.tasks.data"
}

dependencies {
    implementation(projects.tasks.domain)
    implementation(projects.core.storage)
    implementation(projects.core.domain)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    testImplementation(libs.mockk.testing)
    testImplementation(libs.coroutines.testing)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}