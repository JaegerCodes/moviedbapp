apply {
    from("$rootDir/ui-module.gradle")
}
apply(plugin = "org.jetbrains.kotlin.android")

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.coreUi))
    "implementation"(project(Modules.movieDetailDomain))
    "implementation"(Coil.coil)
}