apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    
    "implementation"(project(Modules.trendsDomain))
    "implementation"(project(Modules.movieDetailsPresentation))

    "implementation"(Ui.material)
    "implementation"(Ui.navigationUiKtx)
    "implementation"(Ui.navigationFragmentKtx)
    "implementation"(Ui.constraintLayout)
    "implementation"(Ui.flexbox)
    "implementation"(Ui.lifecycleExtensions)
    "implementation"(Ui.lifecycleViewModelKtx)
    "implementation"(Ui.lifecycleLivedataKtx)
    "implementation"(Ui.lifecycleRuntimeKtx)
    "implementation"(Lottie.lottie)
    "implementation"(Coil.coil)

}