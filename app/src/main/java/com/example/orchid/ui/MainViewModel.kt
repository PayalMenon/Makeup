package com.example.orchid.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.orchid.data.Product
import com.example.orchid.repository.NetworkProductHelper
import com.example.orchid.repository.ProductHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val productHelper: ProductHelper
) : ViewModel() {

    private val _productList: MutableSharedFlow<List<Product>> = MutableSharedFlow()
    val productList: SharedFlow<List<Product>> get() = _productList

    private val _product: MutableSharedFlow<Product> = MutableSharedFlow()
    val product: SharedFlow<Product> get() = _product

    fun fetchProductList() {
        viewModelScope.launch {
            _productList.emit(productHelper.fetchProductList())
        }
    }

    fun getProductFromId(productId: Int) {
        viewModelScope.launch {
            _product.emit(productHelper.fetchProduct(productId))
        }
    }
}