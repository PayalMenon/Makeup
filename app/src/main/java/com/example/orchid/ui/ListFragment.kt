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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.orchid.R
import com.example.orchid.data.Product
import com.example.orchid.databinding.FragmentListBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val mainViewModel: MainViewModel by activityViewModels()

    private lateinit var productAdapter : ProductAdapter
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeProductList()

        binding.productList.layoutManager = LinearLayoutManager(requireActivity())
        productAdapter = ProductAdapter { product : Product -> onProductItemClicked(product)}
        binding.productList.adapter = productAdapter
    }

    private fun observeProductList() {
        mainViewModel.productList.onEach { products ->
            productAdapter.setProducts(products)
        }
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onProductItemClicked(product: Product) {
        val direction = ListFragmentDirections.actionFirstFragmentToSecondFragment(productId = product.id)
        findNavController().navigate(direction)
    }
}