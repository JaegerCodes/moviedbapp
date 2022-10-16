apply {
    from("$rootDir/ui-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.coreUi))
    "implementation"(project(Modules.trendsPresentation))
    "implementation"(project(Modules.recommendationsPresentation))
    "implementation"(project(Modules.upcomingPresentation))
}