package com.example.pmacademyandroid_troschij_metelov_project2.issueScreenFeature.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pmacademyandroid_troschij_metelov_project2.ClientApp
import com.example.pmacademyandroid_troschij_metelov_project2.Constants.Companion.USER_KEY
import com.example.pmacademyandroid_troschij_metelov_project2.R
import com.example.pmacademyandroid_troschij_metelov_project2.databinding.IssuesFragmentBinding
import com.example.pmacademyandroid_troschij_metelov_project2.issueScreenFeature.IssueReposResponse
import com.example.pmacademyandroid_troschij_metelov_project2.utils.ClientAppState
import com.example.pmacademyandroid_troschij_metelov_project2.utils.navigator.BaseFragment
import com.example.pmacademyandroid_troschij_metelov_project2.projectScreenFeature.UserProject
import kotlinx.coroutines.launch
import javax.inject.Inject

class IssuesScreenFragment : BaseFragment(R.layout.issues_fragment) {

    @Inject
    lateinit var viewModel: IssuesScreenViewModel
    private lateinit var userProject: UserProject
    private lateinit var binding: IssuesFragmentBinding
    private val issuesAdapter = IssuesAdapter()

    private fun onIssueClick() {
        navigation.showIssueScreen()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = IssuesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupDi()
        setupRecyclerView()
        viewModel.getIssues(userProject)
        setupLiveDataListener()
    }

    private fun setupDi() {
        val app = requireActivity().application as ClientApp
        app.getComponent().inject(this)
    }

    private fun setupRecyclerView() {
        binding.apply {
            rvIssues.adapter = issuesAdapter
            rvIssues.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun setupLiveDataListener() {
        viewModel.issuesLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is ClientAppState.Loading -> {
                }
                is ClientAppState.Data -> updateIssuesRecyclerView(it.data)
                is ClientAppState.Error -> {
                }
            }
        }
    }

    private fun updateIssuesRecyclerView(pagingData: PagingData<IssueReposResponse>) {
        viewModel.viewModelScope.launch {
            issuesAdapter.submitData(pagingData)
        }
    }

    companion object {
        fun newInstance(userProject: UserProject) =
            IssuesScreenFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(USER_KEY, userProject)
                }
            }
    }

}