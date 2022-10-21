package com.rappi.core.presentation.model

sealed class Languages {
    object EnUs: Languages() {
        public override val name: String = "en-US"
    }
    object EsES: Languages() {
        public override val name: String = "es-ES"
    }
    internal abstract val name: String
}
