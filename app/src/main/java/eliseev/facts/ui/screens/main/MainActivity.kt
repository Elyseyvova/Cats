package eliseev.facts.ui.screens.main

import eliseev.facts.R
import eliseev.facts.base.BaseActivity
import eliseev.facts.data.models.Fact
import eliseev.facts.ui.screens.main.adapter.FactsAdapter
import kotlinx.android.synthetic.main.activity_main.*
import moxy.ktx.moxyPresenter
import org.koin.java.KoinJavaComponent.get

class MainActivity : BaseActivity(R.layout.activity_main), MainContract.View {

    private val presenter by moxyPresenter { get(MainPresenter::class.java) }

    private val adapter = FactsAdapter()

    override fun setupUI() {
        contentView.setOnRefreshListener { presenter.onAddFact() }
        rvFacts.adapter = adapter
    }

    override fun showFacts(facts: List<Fact>) {
        adapter.setNewInstance(facts.toMutableList())
    }

    override fun addFact(fact: Fact) {
        adapter.addData(0, fact)
    }
}