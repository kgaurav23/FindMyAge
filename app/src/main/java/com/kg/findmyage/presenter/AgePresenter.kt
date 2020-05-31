package com.kg.findmyage.presenter

import com.kg.findmyage.interfaces.IPresenterContract
import com.kg.findmyage.interfaces.IViewContract
import kotlinx.coroutines.*
import java.util.*

class AgePresenter(private val viewContract: IViewContract) : IPresenterContract {

    override fun findAge(birthYear: String) {
        viewContract.hideAgeView()

        if (birthYear.isBlank()) {
            viewContract.showValidationError()
            return
        }

        viewContract.showProgressBar()

        CoroutineScope(Dispatchers.IO).launch {
            delay(2000)
            val age = Calendar.getInstance().get(Calendar.YEAR) - Integer.parseInt(birthYear)

            withContext(Dispatchers.Main) {
                viewContract.hideProgressBar()

                if(age > 0) {
                    viewContract.showAgeOnScreen(age)
                } else {
                    viewContract.showValidationError()
                }
            }
        }
    }
}