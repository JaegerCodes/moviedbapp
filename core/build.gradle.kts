apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(Retrofit.okHttpLoggingInterceptor)
    "implementation"(Retrofit.retrofit)
    "implementation"(Retrofit.moshiConverter)

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

}