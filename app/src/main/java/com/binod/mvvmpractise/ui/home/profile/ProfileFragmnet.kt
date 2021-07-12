package com.binod.mvvmpractise.ui.home.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.binod.mvvmpractise.R

class ProfileFragmnet : Fragment() {

    companion object {
        fun newInstance() = ProfileFragmnet()
    }

    private lateinit var viewModel: ProfileFragmnetViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.profile_fragmnet_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileFragmnetViewModel::class.java)
        // TODO: Use the ViewModel
    }

}