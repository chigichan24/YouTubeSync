package com.cyder.android.syncpod.view.activity

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.cyder.android.syncpod.R
import com.cyder.android.syncpod.databinding.ActivitySignInBinding
import com.cyder.android.syncpod.view.helper.setUpSnackbar
import com.cyder.android.syncpod.viewmodel.SignInActivityViewModel
import javax.inject.Inject


/**
 * Created by chigichan24 on 2018/01/17.
 */

class SignInActivity : BaseActivity() {
    @Inject lateinit var viewModel: SignInActivityViewModel
    private lateinit var binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getComponent().inject(this)
        bindViewModel(viewModel)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)
        binding.viewModel = viewModel

        viewModel.callback = setUpSnackbar()
    }

    override fun onDestroy() {
        viewModel.callback = null
        super.onDestroy()
    }


    companion object {
        fun createIntent(context: Context): Intent = Intent(context, SignInActivity::class.java)
    }
}