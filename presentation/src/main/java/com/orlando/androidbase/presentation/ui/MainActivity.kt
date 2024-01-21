package com.orlando.androidbase.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.orlando.androidbase.R
import com.orlando.androidbase.databinding.ActivityMainBinding
import com.orlando.androidbase.presentation.extensions.click
import com.orlando.androidbase.presentation.extensions.gone
import com.orlando.androidbase.presentation.extensions.visible
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var navController: NavController? = null
    private var searchViewConfig = SearchViewConfig()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpNavController()
    }

    fun showProgress() {
        if (!binding.progressBar.isVisible) {
            binding.progressBar.visible()
        }
    }

    fun showProgressV2() {
        binding.progressBar.visible()
    }

    private fun setUpNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    fun hideProgress() {
        if (binding.progressBar.isVisible) {
            binding.progressBar.gone()
        }
    }


    fun shouldShowProgress(isLoading: Boolean) {
        if (isLoading) {
            showProgress()
        } else {
            hideProgress()
        }
    }

    private fun changeTitleToolbar(title: String) {
        binding.toolbarLayout.toolbarTitle.text = title
    }

    private fun showToolbar(showToolbar: Boolean) {
        if (showToolbar) {
            binding.toolbarLayout.root.visible()
        } else {
            binding.toolbarLayout.root.gone()
        }
    }

    private fun setOnBackButton(clickOnBack: (() -> Unit)?) = with(binding) {
        val clickOnBackButton = if (clickOnBack == null) {
            {
                navController?.popBackStack()
            }
        } else {
            {
                clickOnBack()
            }
        }
        toolbarLayout.toolbarBack.click {
            clickOnBackButton()
        }
    }

    fun showSearchView(config: SearchViewConfig) {
        searchViewConfig = config
        setUpSearchView()
    }

    private fun setUpSearchView() = with(binding) {
        searchView.isVisible = searchViewConfig.showSearchView
        searchView.gravity = View.TEXT_ALIGNMENT_CENTER
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchViewConfig.onQueryTextSubmit(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                searchViewConfig.onQueryTextChange(newText)
                return false
            }
        })
    }

    fun setToolbarConfiguration(configuration: ToolbarConfiguration) {
        setOnBackButton(configuration.clickOnBack)
        changeTitleToolbar(configuration.toolbarTitle)
        showToolbar(configuration.showToolbar)
    }

    data class ToolbarConfiguration(
        val showToolbar: Boolean = false,
        val clickOnBack: (() -> Unit)? = null,
        val toolbarTitle: String = ""
    )

    data class SearchViewConfig(
        val hintText: String = "Search",
        val showSearchView: Boolean = false,
        val showConfigIcon: Boolean = false,
        val onMenuItemActionExpand: () -> Unit = {},
        val onMenuItemActionCollapse: () -> Unit = {},
        val onQueryTextSubmit: (query: String) -> Unit = {},
        val onQueryTextChange: (newText: String) -> Unit = {},
        val clickOnSearchIcon: () -> Unit = {},
        val clickOnConfigIcon: () -> Unit = {}
    )

}