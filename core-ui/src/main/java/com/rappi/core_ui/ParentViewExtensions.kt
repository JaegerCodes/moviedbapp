package com.rappi.core_ui

import androidx.fragment.app.Fragment
import com.rappi.home_presentation.home_guest.HomeGuestFragment


fun Fragment.parentViewVisible(isVisible: Boolean) {
    val parentFragment = parentFragment?.parentFragment
    if (parentFragment is HomeGuestFragment) {
        parentFragment.setLoadingAnimationVisibility(isVisible)
    }
}
