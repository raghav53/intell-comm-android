package com.intell.comm.views.homeViews.account

import android.content.Intent
import android.widget.EditText
import com.intell.comm.R
import com.intell.comm.base.event.EditTextValueChangeEvent
import com.intell.comm.base.views.BaseFragment
import com.intell.comm.databinding.FragmentAccountBinding
import com.intell.comm.views.commonModules.CommonModuleActivity
import com.intell.comm.views.preHomeViews.PreHomeActivity

class AccountFragment : BaseFragment<FragmentAccountBinding, AccountViewModel>(
    R.layout.fragment_account,
    AccountViewModel::class.java
) {

    var search = ""

    override fun onCreateView() {

        viewModel.baseClick.observe(viewLifecycleOwner) { view ->
            when (view?.id ?: 0) {

                R.id.constraint_profile -> {
                    openCommonPage(0)

                }
                R.id.constraint_security_pin -> {
                    openCommonPage(1)

                } R.id.constraint_change_password -> {
                openCommonPage(2)

                }
                R.id.constraint_logout -> {
                    startNewActivity(Intent(requireContext(), PreHomeActivity::class.java), true)
                }
            }
        }

        viewModel.editTextValue.observe(
            viewLifecycleOwner,
            object : EditTextValueChangeEvent.EditTextObserver {
                override fun onEditTextReceived(editText: EditText) {
                    when (editText.id) {
                        R.id.et_search -> {
                            search = editText.text.toString().trim()
                        }
                    }
                }

            })

    }

    private fun openCommonPage(key:Int) {
        val i = Intent(requireContext(), CommonModuleActivity::class.java)
        i.putExtra("key", key)
        startActivity(i)
//        startNewActivity(Intent(requireContext(), CommonModuleActivity::class.java), false)
    }







}