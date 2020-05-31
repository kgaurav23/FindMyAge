package com.kg.findmyage.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.kg.findmyage.R
import com.kg.findmyage.interfaces.IViewContract
import com.kg.findmyage.presenter.AgePresenter
import kotlinx.android.synthetic.main.activity_find_my_age.*

class FindMyAgeActivity : AppCompatActivity(), IViewContract {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_my_age)

        val agePresenter = AgePresenter(this)

        btn_find_age.setOnClickListener {
            agePresenter.findAge(et_birth_year.text.toString())
        }
    }

    override fun showAgeOnScreen(age: Int) {
        tv_age.visibility = View.VISIBLE
        tv_age.text = getString(R.string.age_text, age)
    }

    override fun showValidationError() {
        et_birth_year.error = getString(R.string.validation_error_message)
    }

    override fun hideAgeView() {
        tv_age.visibility = View.GONE
    }

    override fun showProgressBar() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progress_bar.visibility = View.GONE
    }
}
