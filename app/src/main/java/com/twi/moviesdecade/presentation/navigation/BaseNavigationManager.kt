package com.twi.moviesdecade.presentation.navigation


import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.twi.moviesdecade.presentation.base.BaseActivity
import java.lang.ref.WeakReference

/**
 * Created by Ehab on 6/13/2018.
 */

object BaseNavigationManager {

    lateinit var fragmentManager: androidx.fragment.app.FragmentManager

    private var currentActivity: WeakReference<BaseActivity<*>>? = null

    fun setCurrentActivity(baseActivity: BaseActivity<*>?) {
        currentActivity = WeakReference<BaseActivity<*>>(baseActivity)
    }

    fun replaceFragment(containerViewId: Int, fragment: Fragment, addToBackStack: Boolean) {
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(containerViewId, fragment)
        if (addToBackStack) transaction.addToBackStack(null)
        transaction.commit()
    }


    private fun addFragment(
        containerViewId: Int,
        fragment: Fragment,
        addToBackStack: Boolean,
        tag: String?
    ) {
        val transaction = fragmentManager.beginTransaction()
        if (tag != null) {
            transaction.add(containerViewId, fragment)
        } else {
            transaction.add(containerViewId, fragment, tag)
        }

        if (addToBackStack) transaction.addToBackStack(null)
        transaction.commitAllowingStateLoss()
    }

    fun showDialogFragment(fragment: DialogFragment, addToBackStack: Boolean, tag: String?) {
        val transaction = fragmentManager.beginTransaction()
        if (addToBackStack) transaction.addToBackStack(null)
        fragment.show(transaction, tag)
    }

    private fun addRootFragment(
        containerViewId: Int,
        fragment: Fragment,
        tag: String?
    ) {
        fragmentManager.popBackStackImmediate()
        addFragment(containerViewId, fragment, false, tag)
    }

}
