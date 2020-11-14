package com.example.itunestrackviewer.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.itunestrackviewer.data.model.Item
import com.example.itunestrackviewer.data.repository.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Home View-model
 * View-model of ItemListActivity
 */
class ListViewModel(private val context : Context, private val repository: Repository) : ViewModel() {

    var featuredList : LiveData<List<Item>> = repository.getFeatured(context)
    var playList : LiveData<List<Item>> = repository.getPlayList(context)
    private val compositeDisposable = CompositeDisposable()

    fun fetchItems() {

        compositeDisposable.add(
            repository.getItems()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    loadToLocalDatabase(it.results)
                }, {
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    it.message?.let { it1 -> Log.e("failed ", it1) }
                })
        )
    }

    private fun loadToLocalDatabase(items: List<Item>) {
        repository.loadItems(context,items)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}