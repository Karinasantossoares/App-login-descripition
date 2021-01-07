package com.example.app_description_apiary.extensions

import io.reactivex.Single
import io.reactivex.functions.BiConsumer
import java.net.UnknownHostException

fun <T> Single<T>.subscribeSafe(
    success: (T) -> Unit,
    error: (Throwable) -> Unit,
    finally: (() -> Unit)? = null
) =
    doFinally {finally?.invoke() }.subscribe { res, err ->
        if (err != null && err is UnknownHostException) {
            error.invoke(Throwable("Falha na comunicação"))
        } else if (err != null) {
            error.invoke(err)
        } else {
            success.invoke(res)
        }
    }