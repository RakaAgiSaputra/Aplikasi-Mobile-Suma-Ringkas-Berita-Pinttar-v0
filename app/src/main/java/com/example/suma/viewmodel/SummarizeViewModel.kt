package com.example.suma.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.suma.data.ApiClient
import kotlinx.coroutines.launch

class SummarizeViewModel : ViewModel() {

    var urlInput by mutableStateOf("")
    var isLoading by mutableStateOf(false)
    var summaryResult by mutableStateOf("")

    fun fetchSummary(onResult: (String) -> Unit) {
        isLoading = true
        viewModelScope.launch {
            try {
                val response = ApiClient.apiService.getSummary(urlInput)
                if (response.isSuccessful) {
                    summaryResult = response.body().orEmpty()
                } else {
                    summaryResult = "Error: ${response.code()} ${response.message()}"
                }
            } catch (e: Exception) {
                summaryResult = "Exception: ${e.localizedMessage}"
            } finally {
                isLoading = false
                onResult(summaryResult)
            }
        }
    }
}
