package com.rappi.core.presentation.model

import com.rappi.core.domain.model.DomainMovie

sealed interface MovieHorizontalView: DomainMovie {
    override val posterPath: String
}