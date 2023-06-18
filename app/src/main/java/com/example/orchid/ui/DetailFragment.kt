package com.example.orchid.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.orchid.R
import com.example.orchid.databinding.FragmentDetailBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val mainViewModel : MainViewModel by activityViewModels()
    private val args : DetailFragmentArgs by navArgs()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.getProductFromId(args.productId)
        observeForProduct()
    }

    private fun observeForProduct() {
        mainViewModel.product.onEach { product ->
            binding.name = product.name
            binding.rating = "Rating = + ${product.rating}"
            binding.price = product.price + "$"
            binding.desc = product.description
            Glide.with(requireContext()).load(product.image_link).into(binding.detailImage)
        }
        .flowWithLifecycle(viewLifecycleOwner.lifecycle)
        .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}