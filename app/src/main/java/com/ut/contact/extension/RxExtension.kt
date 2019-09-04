package com.ut.contact.extension

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver

/**
 * Created by Atia on 2019-09-03
 */

fun <T> DisposableSingleObserver<T>.disposedBy(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}

fun Disposable.disposedBy(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}