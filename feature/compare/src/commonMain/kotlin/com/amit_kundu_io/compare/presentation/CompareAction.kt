/**
 * CompareAction.kt
 *
 * Author      : Amit Kundu
 * Created On  : 16/08/2026
 *
 * Description :
 * Part of the project codebase. This file contributes to the overall
 * functionality and follows standard coding practices and architecture.
 *
 * Notes :
 * Ensure changes are consistent with project guidelines and maintain
 * code readability and quality.
 */

package com.amit_kundu_io.compare.presentation

sealed interface CompareAction {

    data class UsernameAChanged(val value: String) : CompareAction
    data class UsernameBChanged(val value: String) : CompareAction
    data object Compare : CompareAction

}