/**
 * CompareDevelopersUseCase.kt
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

package com.amit_kundu_io.compare.domain.use_case.GetDeveloperStatsUseCase



import com.amit_kundu_io.compare.domain.models.DeveloperComparisonModels.DeveloperComparison
import com.amit_kundu_io.compare.domain.models.DeveloperComparisonModels.DeveloperMetric
import com.amit_kundu_io.compare.domain.models.DeveloperComparisonModels.DeveloperWinner
import com.amit_kundu_io.compare.domain.models.DeveloperComparisonModels.MetricComparison
import com.amit_kundu_io.compare.domain.models.DeveloperStats
import com.kundutechstudio.network.res.NetworkResult
import com.kunduthchstudio.utility.Logger.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import kotlin.coroutines.cancellation.CancellationException
import kotlin.math.abs

class CompareDevelopersUseCase(
    private val getDeveloperStatsUseCase: GetDeveloperStatsUseCase,
    private val calculateDeveloperScoreUseCase: CalculateDeveloperScoreUseCase,
) {

    suspend operator fun invoke(
        usernameA: String,
        usernameB: String,
        token: String,
    ): Flow<NetworkResult<DeveloperComparison>> = flow {


        emit(NetworkResult.Loading)
        val result: NetworkResult<DeveloperComparison> = try {
            coroutineScope {

                // Launch both network calls concurrently
                val deferredA = async {
                    getDeveloperStatsUseCase(
                        username = usernameA,
                        token = token
                    )
                }

                val deferredB = async {
                    getDeveloperStatsUseCase(
                        username = usernameB,
                        token = token
                    )
                }

                val resultA = deferredA.await()
                val resultB = deferredB.await()

                val a = when (resultA) {
                    is NetworkResult.Success -> resultA.data
                    is NetworkResult.Error -> return@coroutineScope NetworkResult.Error(
                        resultA.message,
                        resultA.code,
                        resultA.cause
                    )

                    is NetworkResult.Loading -> return@coroutineScope NetworkResult.Error("Unexpected loading state")
                } ?: return@coroutineScope NetworkResult.Error("No data for $usernameA")

                val b = when (resultB) {
                    is NetworkResult.Success -> resultB.data
                    is NetworkResult.Error -> return@coroutineScope NetworkResult.Error(
                        resultB.message,
                        resultB.code,
                        resultB.cause
                    )

                    is NetworkResult.Loading -> return@coroutineScope NetworkResult.Error("Unexpected loading state")
                } ?: return@coroutineScope NetworkResult.Error("No data for $usernameB")

                // CPU-bound: normalization, scoring, metric building — no I/O below this point
                val comparison = withContext(Dispatchers.Default) {

                    val stars = normalize(a.totalStars.toDouble(), b.totalStars.toDouble())
                    val forks = normalize(a.totalForks.toDouble(), b.totalForks.toDouble())
                    val repositoryQuality = normalize(a.repositoryQuality, b.repositoryQuality)
                    val contributions = normalize(contributionValue(a), contributionValue(b))
                    val issuesPullRequests = normalize(issuePrValue(a), issuePrValue(b))
                    val followers = normalize(a.followers.toDouble(), b.followers.toDouble())
                    val reviews =
                        normalize(a.pullRequestReviews.toDouble(), b.pullRequestReviews.toDouble())
                    val activity = normalize(activityValue(a), activityValue(b))

                    val scoreA = calculateDeveloperScoreUseCase(
                        developer = a,
                        starsScore = stars.first,
                        forksScore = forks.first,
                        repositoryQualityScore = repositoryQuality.first,
                        contributionsScore = contributions.first,
                        issuesPullRequestsScore = issuesPullRequests.first,
                        followersScore = followers.first,
                        reviewsScore = reviews.first,
                        activityScore = activity.first,
                    )

                    val scoreB = calculateDeveloperScoreUseCase(
                        developer = b,
                        starsScore = stars.second,
                        forksScore = forks.second,
                        repositoryQualityScore = repositoryQuality.second,
                        contributionsScore = contributions.second,
                        issuesPullRequestsScore = issuesPullRequests.second,
                        followersScore = followers.second,
                        reviewsScore = reviews.second,
                        activityScore = activity.second,
                    )

                    val winner = when {
                        scoreA.totalScore > scoreB.totalScore -> DeveloperWinner.DEVELOPER_A
                        scoreB.totalScore > scoreA.totalScore -> DeveloperWinner.DEVELOPER_B
                        else -> DeveloperWinner.DRAW
                    }

                    val metrics = listOf(
                        metric(
                            DeveloperMetric.STARS,
                            a.totalStars.toDouble(),
                            b.totalStars.toDouble(),
                            stars,
                            0.20
                        ),
                        metric(
                            DeveloperMetric.FORKS,
                            a.totalForks.toDouble(),
                            b.totalForks.toDouble(),
                            forks,
                            0.10
                        ),
                        metric(
                            DeveloperMetric.REPOSITORY_QUALITY,
                            a.repositoryQuality,
                            b.repositoryQuality,
                            repositoryQuality,
                            0.15
                        ),
                        metric(
                            DeveloperMetric.CONTRIBUTIONS,
                            contributionValue(a),
                            contributionValue(b),
                            contributions,
                            0.20
                        ),
                        metric(
                            DeveloperMetric.ISSUES_PULL_REQUESTS,
                            issuePrValue(a),
                            issuePrValue(b),
                            issuesPullRequests,
                            0.10
                        ),
                        metric(
                            DeveloperMetric.FOLLOWERS,
                            a.followers.toDouble(),
                            b.followers.toDouble(),
                            followers,
                            0.05
                        ),
                        metric(
                            DeveloperMetric.PR_REVIEWS,
                            a.pullRequestReviews.toDouble(),
                            b.pullRequestReviews.toDouble(),
                            reviews,
                            0.10
                        ),
                        metric(
                            DeveloperMetric.ACTIVITY,
                            activityValue(a),
                            activityValue(b),
                            activity,
                            0.10
                        ),
                    )

                    DeveloperComparison(
                        developerA = scoreA,
                        developerB = scoreB,
                        winner = winner,
                        scoreDifference = abs(scoreA.totalScore - scoreB.totalScore),
                        metricResults = metrics,
                    )
                }

                NetworkResult.Success(comparison)
            }
        } catch (e: CancellationException) {
            throw e // never swallow cancellation — required for structured concurrency
        } catch (e: Exception) {
            NetworkResult.Error(message = e.message ?: "Comparison failed", cause = e)
        }

        emit(result)
    }


    private fun contributionValue(
        developer: DeveloperStats
    ): Double {

        return developer.commits * 0.60 + developer.totalContributions * 0.40
    }

    private fun issuePrValue(
        developer: DeveloperStats
    ): Double {

        return (developer.issues + developer.pullRequests).toDouble()
    }

    private fun activityValue(
        developer: DeveloperStats
    ): Double {

        return developer.activeDays * 0.5 +
                developer.activeWeeks * 2.0 +
                developer.longestStreak * 0.5
    }

    private fun normalize(
        a: Double,
        b: Double,
    ): Pair<Double, Double> {

        if (a <= 0 && b <= 0) {
            return 50.0 to 50.0
        }

        val total = a + b

        return (a / total * 100.0) to (b / total * 100.0)
    }

    private fun metric(
        metric: DeveloperMetric,
        rawA: Double,
        rawB: Double,
        normalized: Pair<Double, Double>,
        weight: Double,
    ): MetricComparison {

        val winner =
            when {
                rawA > rawB -> DeveloperWinner.DEVELOPER_A

                rawB > rawA -> DeveloperWinner.DEVELOPER_B

                else -> DeveloperWinner.DRAW
            }

        return MetricComparison(
            metric = metric,

            developerARawValue = rawA,
            developerBRawValue = rawB,

            developerAScore = normalized.first,
            developerBScore = normalized.second,

            weight = weight,

            winner = winner,
        )
    }
}