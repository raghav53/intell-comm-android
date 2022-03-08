package com.intell.comm.views.clientsModule.diseaseRegister

import androidx.navigation.fragment.navArgs
import com.intell.comm.R
import com.intell.comm.base.views.BaseFragment
import com.intell.comm.databinding.FragmentDiseaseRegisterDetailsBinding
import com.intell.comm.views.clientsModule.ClientsActivity

class DiseaseRegisterDetailsFragment :
    BaseFragment<FragmentDiseaseRegisterDetailsBinding, DiseaseRegisterViewModel>(
        R.layout.fragment_disease_register_details,
        DiseaseRegisterViewModel::class.java
    ) {

    private val args: DiseaseRegisterDetailsFragmentArgs by navArgs()

    override fun onCreateView() {
        (requireActivity() as ClientsActivity).isBackArrowShow(true)
        (requireActivity() as ClientsActivity).isAddIconShow()
        (requireActivity() as ClientsActivity).isToolbarTitleShow(
            true,
            args.diseaseName + " Disease Details"
        )
    }

}


//Reason Of Encounter : Test
// Subjective : Test          Objective : Test
// Assessment : Test          Plan : Test
// Diagnosis : Acute gastroenteropathy
// Created By : Testing Business
// Created Date : Tue, Jan 25, 2022 12:25 PM