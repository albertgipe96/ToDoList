plugins {
    alias(libs.plugins.todolist.jvm.library)
}

dependencies {
    implementation(projects.core.domain)
}