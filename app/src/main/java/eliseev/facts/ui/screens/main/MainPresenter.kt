package eliseev.facts.ui.screens.main

import eliseev.facts.base.BasePresenter
import eliseev.facts.data.models.Fact
import eliseev.facts.data.result.FactResult
import eliseev.facts.repositories.facts.FactsRepository
import eliseev.facts.utils.extensions.letOnUI
import eliseev.facts.utils.extensions.runOnBg

class MainPresenter(
    private val repository: FactsRepository
) : BasePresenter<MainContract.View>(), MainContract.Presenter {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showEmpty()
    }

    override fun onAddFact() {
        runOnBg(viewState::showLoading) {
            repository.randomFact()
                .letOnUI(viewState::hideLoading) { result ->
                    when (result) {
                        is FactResult.Success -> proceedAddFactSuccess(result.fact)
                        is FactResult.Failure -> viewState.showMessage(result.error.toString())
                    }
                }
        }
    }

    private fun proceedAddFactSuccess(fact: Fact) {
        viewState.addFact(fact)
        viewState.showContent()
    }
}
