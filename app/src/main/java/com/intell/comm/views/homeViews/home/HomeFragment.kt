package com.intell.comm.views.homeViews.home

import android.content.Intent
import android.view.View
import android.widget.EditText
import androidx.core.view.marginLeft
import androidx.viewpager.widget.ViewPager
import com.intell.comm.BR
import com.intell.comm.R
import com.intell.comm.base.event.EditTextValueChangeEvent
import com.intell.comm.base.model.BaseModel
import com.intell.comm.base.views.BaseFragment
import com.intell.comm.base.views.adapter.BaseRecyclerViewAdapter
import com.intell.comm.base.views.adapter.BaseViewPagerAdapter
import com.intell.comm.base.views.adapter.OnItemClickListener
import com.intell.comm.databinding.AdapterCategoriesBinding
import com.intell.comm.databinding.FragmentHomeBinding
import com.intell.comm.databinding.PagerHomePageBinding
import com.intell.comm.views.clientsModule.ClientsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewModel>(
        R.layout.fragment_home,
        HomeViewModel::class.java
    ) {

    var search = ""

    override fun onCreateView() {
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

        setSwipeCardView()
        setCategoryList()

    }

    private fun setSwipeCardView() {
        val swipeAdapter = BaseViewPagerAdapter<BaseModel, PagerHomePageBinding>(
            R.layout.pager_home_page,
            BR.model,
            object : OnItemClickListener<BaseModel> {
                override fun onItemClick(v: View?, m: BaseModel, position: Int) {

                }
            },
            isPosition = true
        )
        binding.viewPager.adapter = swipeAdapter
        binding.dotsIndicator.setViewPager(binding.viewPager)
        swipeAdapter.updateList(getSwipeList())
        binding.viewPager.pageMargin = 14// both side show card margin

    }

    private fun setCategoryList() {
        val categoryAdapter = BaseRecyclerViewAdapter<BaseModel, AdapterCategoriesBinding>(
            R.layout.adapter_categories,
            BR.model,
            object : OnItemClickListener<BaseModel> {
                override fun onItemClick(v: View?, m: BaseModel, position: Int) {
                    if (position == 1) {
                        startNewActivity(
                            Intent(requireContext(), ClientsActivity::class.java),
                            isAnim = true
                        )
                    }
                }
            },
            isPosition = true
        )

        binding.rvCategory.adapter = categoryAdapter
        categoryAdapter.updateList(getSwipeList())
    }


    private fun getSwipeList(): List<BaseModel> {
        val list = ArrayList<BaseModel>()
        list.add(BaseModel(string1 = "Appointments"))
        list.add(BaseModel(string1 = "Clients"))
        list.add(BaseModel(string1 = "Medication"))
        list.add(BaseModel(string1 = "Referrals"))
        list.add(BaseModel(string1 = "ICMAIL"))
        list.add(BaseModel(string1 = "Files"))
        return list
    }
}