package com.kg.findmyage.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import java.util.*

class AgeViewModel: ViewModel() {

    private val _age = MutableLiveData<Int>()
    val age: LiveData<Int>
        get() = _age

    private val _showLoader = MutableLiveData<Boolean>()
    val showLoader: LiveData<Boolean>
        get() = _showLoader

    private val _showAgeView = MutableLiveData<Boolean>()
    val showAgeView: LiveData<Boolean>
        get() = _showAgeView

    private val _showValidationError = MutableLiveData<Boolean>()
    val showValidationError: LiveData<Boolean>
        get() = _showValidationError

    fun findAge(birthYear: String) {
        _showLoader.value = true
        _showValidationError.value = false
        _showAgeView.value = false

        if (birthYear.isBlank()) {
            _showValidationError.value = true
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            delay(2000)
            val personAge = Calendar.getInstance().get(Calendar.YEAR) - Integer.parseInt(birthYear)

            withContext(Dispatchers.Main) {
                _showLoader.value = false

                if(personAge > 0) {
                    _age.value = personAge
                    _showAgeView.value = true
                } else {
                    _showValidationError.value = true
                }
            }
        }
    }
}