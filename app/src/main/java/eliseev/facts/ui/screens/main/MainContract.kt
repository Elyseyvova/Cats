package eliseev.facts.ui.screens.main

import eliseev.facts.base.BaseView
import eliseev.facts.data.models.Fact

interface MainContract {

    interface View : BaseView {
        fun showFacts(facts: List<Fact>)
        fun addFact(fact: Fact)
    }

    interface Presenter {
        fun onAddFact()
    }
}
