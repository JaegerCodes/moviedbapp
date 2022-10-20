apply {
    from("$rootDir/ui-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.coreUi))
    "implementation"(project(Modules.recommendationsDomain))
    "implementation"(project(Modules.movieDetailsPresentation))
    "implementation"(Coil.coil)
}