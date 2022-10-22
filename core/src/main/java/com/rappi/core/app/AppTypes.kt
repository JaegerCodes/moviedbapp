package com.rappi.core.app

sealed class Languages {
    object EnUs: Languages() {
        public override val name: String = "en-US"
    }
    object EsES: Languages() {
        public override val name: String = "es-ES"
    }
    internal abstract val name: String
}


sealed class AndroidApps {
    object YouTube: AndroidApps() {
        public override val name: String = "youtube"
    }
    internal abstract val name: String
}

