package com.assignment.shaadi.ui.view.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.assignment.shaadi.R
import com.assignment.shaadi.databinding.FragmentMainBinding
import com.assignment.shaadi.other.hide
import com.assignment.shaadi.other.show
import com.assignment.shaadi.ui.view.adapter.MatchInvitationAdapter
import com.assignment.shaadi.ui.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainFragment(private val matchInvitationAdapter: MatchInvitationAdapter) :
	Fragment(R.layout.fragment_main) {

	companion object {
		public const val TAG = "MainFragment"
	}

	private var _binding: FragmentMainBinding? = null
	private val binding get() = _binding!!

	private val viewModel: MainViewModel by navGraphViewModels(R.id.nav_graph) {
		defaultViewModelProviderFactory
	}


	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {

		_binding = FragmentMainBinding.inflate(inflater, container, false)
		return binding.root
	}


	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		viewModel.getMatches()
		subscribeObserver()
		setupRecyclerView()
		clickHandler()

	}

	private fun setupRecyclerView() {
		binding.recyclerView.apply {
			adapter = matchInvitationAdapter
			layoutManager = LinearLayoutManager(requireContext())
		}
	}

	private fun clickHandler() {
		matchInvitationAdapter.setOnMatchAttemptedListener { matchInvitation, invitation_status ->
			viewModel.onMatchAttempted(matchInvitation, invitation_status)
		}
	}

	private fun subscribeObserver() {

		viewModel.matches.observe(viewLifecycleOwner, { matchInvitations ->
			binding.gpNoData.hide()
			binding.recyclerView.show()
			matchInvitationAdapter.matchInvitations = matchInvitations.map { it.copy() }
		})

		viewModel.error.observe(viewLifecycleOwner, { errorMsg ->
			showErrorSnackBar(errorMsg)
			if (matchInvitationAdapter.matchInvitations.isNullOrEmpty()) {
				binding.gpNoData.show()
				binding.recyclerView.hide()
			}
		})

		viewModel.loading.observe(viewLifecycleOwner, { isLoading ->
			isLoading?.let {
				if (it) {
					showProgress()
				} else {
					hideProgress()
				}
			}
		})
	}

	private fun showErrorSnackBar(msg: String) {
		Snackbar.make(
			requireActivity().rootLayout,
			msg,
			Snackbar.LENGTH_LONG
		).apply {
			this.view.setBackgroundColor(Color.RED)
		}.setAction("TRY AGAIN") {
			viewModel.getMatches()
		}.show()
	}

	private fun showProgress() {
		binding.progressbar.show()
		binding.gpNoData.hide()
		binding.recyclerView.hide()
	}

	private fun hideProgress() {
		binding.progressbar.hide()
	}

	override fun onDestroy() {
		super.onDestroy()
		_binding = null
	}


}
