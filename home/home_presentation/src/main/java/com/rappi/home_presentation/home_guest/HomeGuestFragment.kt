package com.rappi.home_presentation.home_guest

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rappi.home_presentation.R
import com.rappi.home_presentation.databinding.FragmentHomeGuestBinding
import dagger.hilt.android.AndroidEntryPoint

class HomeGuestFragment : Fragment() {
    private var _binding: FragmentHomeGuestBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeGuestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}