package vch.com.exchange.ui.pb

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import vch.com.exchange.model.NBUModel
import vch.com.exchange.model.PBModel
import vch.com.exchange.repository.Repository

class NBUViewModel(private val repository: Repository) : ViewModel() {
    val models: MutableLiveData<Response<List<NBUModel>>> = MutableLiveData()

    fun getAll(date: String) {
        viewModelScope.launch {
            val response = repository.nbuGetAll(date)
            models.value = response
        }
    }
}

class ExchangeNBUViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NBUViewModel::class.java)) {
            return NBUViewModel(repository) as T
        }
        throw IllegalArgumentException("__Unknown View Model Class__")
    }
}