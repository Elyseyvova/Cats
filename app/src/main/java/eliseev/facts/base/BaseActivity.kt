package eliseev.facts.base

import android.os.Bundle
import eliseev.facts.utils.extensions.hide
import eliseev.facts.utils.extensions.show
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import splitties.toast.toast

abstract class BaseActivity(layoutId: Int) : MvpAppCompatActivity(layoutId), BaseView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUI()
    }

    override fun showLoading() {
        if (!isFinishing) {
            contentView.isRefreshing = true
        }
    }

    override fun hideLoading() {
        contentView.isRefreshing = false
    }

    override fun showContent() {
        hideLoading()
        emptyView?.hide()
    }

    override fun showEmpty() {
        hideLoading()
        emptyView?.show()
    }

    override fun showMessage(message: String) {
        hideLoading()
        toast(message)
    }

    open fun setupUI() = Unit
}
