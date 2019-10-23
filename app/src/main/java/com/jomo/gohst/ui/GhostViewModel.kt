package com.jomo.gohst.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jomo.gohst.data.model.Ghost
import com.jomo.gohst.data.repository.GhostRepository
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import io.reactivex.disposables.Disposable
import io.reactivex.CompletableObserver
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import io.reactivex.functions.Action


class GhostViewModel @Inject constructor(
    private val ghostRepository: GhostRepository
): ViewModel() {

    private val ghostResult: MutableLiveData<List<Ghost>> = MutableLiveData()
    private val ghostError: MutableLiveData<String> = MutableLiveData()

    lateinit var disposableObserver: DisposableObserver<List<Ghost>>
    lateinit var disposableObserverAdd: DisposableObserver<Unit>

    fun ghostResult(): LiveData<List<Ghost>> { return ghostResult }

    fun ghostError(): LiveData<String> { return ghostError }

    fun loadGhosts() {

        disposableObserver = object : DisposableObserver<List<Ghost>>() {
            override fun onComplete() {
            }

            override fun onNext(t: List<Ghost>) {
                ghostResult.postValue(t)
            }

            override fun onError(e: Throwable) {
                ghostError.postValue(e.message)
            }

        }

        ghostRepository.getGhosts()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(disposableObserver)
    }

    fun addGhost(ghost: Ghost) {
        ghostRepository.insertGhost(ghost);

    }

    fun disposeElements() {
        if(null != disposableObserver && !disposableObserver.isDisposed) disposableObserver.dispose()
    }

}

