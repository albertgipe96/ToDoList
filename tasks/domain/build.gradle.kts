plugins {
    alias(libs.plugins.todolist.jvm.library)
}

dependencies {
    implementation(projects.core.domain)

    implementation(libs.javax.inject)
    implementation(libs.kotlinx.coroutines.core)
}