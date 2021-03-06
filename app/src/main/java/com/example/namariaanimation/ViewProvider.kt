package com.example.namariaanimation

import android.view.View
import android.view.View.NO_ID
import android.view.ViewStub
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import java.lang.ref.WeakReference
import kotlin.reflect.KProperty

/**
 * Copied from https://github.com/matheus-corregiari/arch-toolkit
 */

fun <T : View> viewProvider(@IdRes idRes: Int, @IdRes parentRes: Int = NO_ID, viewStubAutoInflate: Boolean = false) =
    ViewProviderDelegate<T>(
        parentRes = parentRes,
        idRes = idRes,
        required = true,
        viewStubAutoInflate = viewStubAutoInflate
    )

fun <T : View?> optionalViewProvider(@IdRes idRes: Int, @IdRes parentRes: Int = NO_ID, viewStubAutoInflate: Boolean = false) =
    ViewProviderDelegate<T?>(
        parentRes = parentRes,
        idRes = idRes,
        required = false,
        viewStubAutoInflate = viewStubAutoInflate
    )

class ViewProviderDelegate<out T>(
    @IdRes private val parentRes: Int = NO_ID,
    @IdRes private val idRes: Int,
    private val required: Boolean,
    private val viewStubAutoInflate: Boolean = false
) {

    private var weakView: WeakReference<View>? = null

    private var view: View?
        get() = weakView?.get()
        set(value) {
            weakView = if (value == null) null else WeakReference(value)
        }

    operator fun getValue(thisRef: AppCompatActivity, property: KProperty<*>): T {
        view?.let { if (!it.isAttachedToWindow) view = null }
        return findView(property) {
            if (parentRes != NO_ID) {
                thisRef.findViewById<View>(parentRes).findViewById(idRes)
            } else {
                thisRef.findViewById(idRes)
            }
        }
    }

    operator fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        view?.let { if (!it.isAttachedToWindow) view = null }
        return findView(property) {
            if (parentRes != NO_ID) {
                thisRef.view?.findViewById<View>(parentRes)?.findViewById(idRes)
            } else {
                thisRef.view?.findViewById(idRes)
            }
        }
    }

    operator fun getValue(thisRef: View, property: KProperty<*>): T {
        return findView(property) {
            if (parentRes != NO_ID) {
                thisRef.findViewById<View>(parentRes).findViewById(idRes)
            } else {
                thisRef.findViewById(idRes)
            }
        }
    }

    operator fun getValue(thisRef: RecyclerView.ViewHolder, property: KProperty<*>): T {
        return findView(property) {
            if (parentRes != NO_ID) {
                thisRef.itemView.findViewById<View>(parentRes).findViewById(idRes)
            } else {
                thisRef.itemView.findViewById(idRes)
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun inflateIfIsViewStub(view: View?) = if (view is ViewStub) view.inflate() else view

    @Suppress("UNCHECKED_CAST")
    private inline fun findView(property: KProperty<*>, crossinline initializer: () -> View?): T {
        view = if (viewStubAutoInflate) inflateIfIsViewStub(view ?: initializer.invoke())
        else view ?: initializer.invoke()
        if (required && view == null) {
            throw IllegalStateException("View ID $idRes for '${property.name}' not found.")
        }
        return view as T
    }
}