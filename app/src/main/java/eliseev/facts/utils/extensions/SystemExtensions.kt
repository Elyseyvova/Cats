package eliseev.facts.utils.extensions

import kotlinx.coroutines.*

suspend fun <T> T.letOnUI(onFinished: (() -> Unit)? = null, block: (T) -> Unit) = onUI {
    onFinished?.invoke()
    block.invoke(this)
}

suspend fun onUI(call: () -> Unit) {
    withContext(Dispatchers.Main) {
        call.invoke()
    }
}

fun <T> runOnBg(
    onStart: (() -> Unit)? = null,
    block: suspend CoroutineScope.() -> T
): Deferred<T> {
    onStart?.invoke()
    return CoroutineScope(Dispatchers.IO).async {
        block.invoke(this)
    }
}
