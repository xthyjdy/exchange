package vch.com.exchange.ui.pb

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import vch.com.exchange.model.PBModel
import vch.com.exchange.repository.Repository

/**
 * PBViewModel - view model for PBFragment class
 */
class PBViewModel(private val repository: Repository) : ViewModel() {
//    val modelSSS: MutableLiveData<Response<List<PBModel>>> = MutableLiveData()
    val model: MutableLiveData<Response<PBModel>> = MutableLiveData()

    /**
     * _ - get currency list from repository by date
     */
    fun getAll(date: String) {
        viewModelScope.launch {
            val response = repository.pbGetAll(date)
            model.value = response
        }
    }

    /**
     * Get All - get currency list from repository
     */
//    fun getAll() {
//        viewModelScope.launch {
//            val response = repository.pbGetAll()
//            modelSSS.value = response
//        }
//    }
}

/**
 * ExchangePBViewModelFactory - factory for create  PBViewModel instance
 */
class ExchangePBViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PBViewModel::class.java)) {
            return PBViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}