apply {
    from("$rootDir/ui-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.coreUi))
    "implementation"(project(Modules.trendsDomain))
    "implementation"(Coil.coil)
}