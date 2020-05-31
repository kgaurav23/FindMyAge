package com.kg.findmyage.interfaces

interface IViewContract {

    fun showAgeOnScreen(age: Int)

    fun showValidationError()

    fun hideAgeView()

    fun showProgressBar()

    fun hideProgressBar()
}