/**
 * OAuthCallback.kt
 *
 * Author      : Amit Kundu
 * Created On  : 15/08/2026
 *
 * Description :
 * Part of the project codebase. This file contributes to the overall
 * functionality and follows standard coding practices and architecture.
 *
 * Notes :
 * Ensure changes are consistent with project guidelines and maintain
 * code readability and quality.
 */

package com.kundutechstudio.auth.data.oauth


object OAuthCallbackReceiver {

    private var callback: ((String) -> Unit)? = null

    fun register(callback: (String) -> Unit) {
        this.callback = callback
    }

    fun onCallback(url: String) {
        callback?.invoke(url)
    }

    fun clear() {
        callback = null
    }
}