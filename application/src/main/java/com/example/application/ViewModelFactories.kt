package com.example.application

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.feature.addapplication.AddApplicationViewModel
import com.example.feature.appcatalog.AppCatalogViewModel
import com.example.feature.applicationdetail.ApplicationDetailViewModel

class AppCatalogVmFactory(private val graph: AppGraph) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AppCatalogViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AppCatalogViewModel(graph.observeApps) as T
        }
        error("Unknown ViewModel class: $modelClass")
    }
}

class AddApplicationVmFactory(private val graph: AppGraph) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddApplicationViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddApplicationViewModel(graph.addApp) as T
        }
        error("Unknown ViewModel class: $modelClass")
    }
}

class ApplicationDetailVmFactory(
    private val graph: AppGraph,
    private val appId: Long,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ApplicationDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ApplicationDetailViewModel(appId, graph.observeDetail, graph.setInstalled) as T
        }
        error("Unknown ViewModel class: $modelClass")
    }
}
