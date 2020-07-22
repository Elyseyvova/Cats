package eliseev.facts.base

import moxy.MvpView

interface BaseView : MvpView {

    fun showLoading()
    fun hideLoading()

    fun showContent()
    fun showEmpty()

    fun showMessage(message: String)
}
