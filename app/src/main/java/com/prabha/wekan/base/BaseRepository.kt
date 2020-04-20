package com.prabha.wekan.base

import android.util.Log
import com.prabha.wekan.network.NetworkCallback
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

open class BaseRepository() {

    fun makeNetworkCall(observable: Observable<*>, networkCallback: NetworkCallback, retryCount: Int = 3, moduleId: String = "") {

        val disposable = CompositeDisposable()
        val delayDurationList = getDelayDurationList(retryCount)
        val observableDurationList = Observable.fromIterable(delayDurationList)

        val disposableObserver = observable
                .retryWhen {
                    it.zipWith(observableDurationList, zipperFunction(retryCount))
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<Any>() {
                    override fun onNext(response: Any) {
                        networkCallback.onSuccess(response)
                        disposable.dispose()
                    }
                    override fun onError(throwable: Throwable) {
                        handle500Error(throwable)
                        networkCallback.onError(throwable)
                        disposable.dispose()
                    }
                    override fun onComplete() {
                        Log.e("BaseRepository", "Retry completed moduleId $moduleId retry count $retryCount")
                        disposable.dispose()
                    }
                })
        disposable.add(disposableObserver)
    }

    private fun zipperFunction(retryCount: Int): BiFunction<Throwable, Long, Unit> {
        return BiFunction { throwable: Throwable, duration: Long ->
            if (throwable is HttpException && throwable.code() in 500..599 && retryCount > 0) {
                Thread.sleep(duration)
                if (duration >= retryCount * 1000L) {
                    throw throwable
                }
            } else{
                throw throwable
            }
        }
    }

    private fun handle500Error(throwable: Throwable) {
        if (throwable is HttpException && throwable.code() in 500..599) {
            Log.d("module_id","-"+throwable.code().toString())
        }
    }

    private fun getDelayDurationList(retryCount: Int): MutableList<Long> {
        val list: MutableList<Long> = arrayListOf()
        if (retryCount > 0) {
            for (i in 1..retryCount) {
                list.add(i * 1000L)
            }
        } else {
            list.add(1000L)
        }
        return list
    }



}