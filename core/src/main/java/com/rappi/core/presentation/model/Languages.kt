package com.rappi.core.presentation.model

sealed class Languages {

    object EnUs: Languages() {
        public override val name: String = "en-US"
    }
    internal abstract val name: String
}