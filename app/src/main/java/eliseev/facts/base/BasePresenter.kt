package eliseev.facts.base

import moxy.MvpPresenter

abstract class BasePresenter<View : BaseView> : MvpPresenter<View>()
