package com.kg.findmyage.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.kg.findmyage.R
import com.kg.findmyage.viewmodel.AgeViewModel
import kotlinx.android.synthetic.main.activity_find_my_age.*

class FindMyAgeActivity : AppCompatActivity(){

    private lateinit var viewModel: AgeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_my_age)

        viewModel = ViewModelProviders.of(this).get(AgeViewModel::class.java)
        observeViewModel()

        btn_find_age.setOnClickListener {
            viewModel.findAge(et_birth_year.text.toString())
        }
    }

    private fun observeViewModel() {
        observeLoaderChange()
        observeAgeTextChange()
        observeValidationErrorChange()
        observeAgeViewChange()
    }

    private fun observeAgeViewChange() {
        viewModel.showAgeView.observe(this, Observer { isShowAgeView ->
            if(isShowAgeView) {
                tv_age.visibility = View.VISIBLE
            } else {
                tv_age.visibility = View.GONE
            }
        })
    }

    private fun observeValidationErrorChange() {
        viewModel.showValidationError.observe(this, Observer { isShowError ->
            if(isShowError) {
                et_birth_year.error = getString(R.string.validation_error_message)
            }
        })
    }

    private fun observeAgeTextChange() {
        viewModel.age.observe(this, Observer { personAge ->
            tv_age.visibility = View.VISIBLE
            tv_age.text = getString(R.string.age_text, personAge)
        })
    }

    private fun observeLoaderChange() {
        viewModel.showLoader.observe(this, Observer { isShowLoader ->
            if(isShowLoader) {
                progress_bar.visibility = View.VISIBLE
            } else {
                progress_bar.visibility = View.GONE
            }
        })
    }
}
