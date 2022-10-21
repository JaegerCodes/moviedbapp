apply {
    from("$rootDir/ui-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.coreUi))
    "implementation"(project(Modules.upcomingDomain))
    "implementation"(project(Modules.movieDetailsPresentation))
    "implementation"(Coil.coil)
}