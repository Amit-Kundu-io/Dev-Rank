/**
 * DeveloperApi.kt
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

package com.amit_kundu_io.compare.data.apis

import com.amit_kundu_io.compare.data.models.GitHubDeveloperResponse.GitHubDeveloperResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import com.amit_kundu_io.compare.data.models.GraphQLRequest
import com.amit_kundu_io.compare.data.models.GraphQLVariables


class DeveloperApi(
    private val client: HttpClient,
) {

    suspend fun getDeveloper(
        username: String,
        token: String,
        from: String,
        to: String,
    ): GitHubDeveloperResponse {

        val query = """
            query GetDeveloper(
                ${'$'}username: String!
                ${'$'}from: DateTime!
                ${'$'}to: DateTime!
            ) {

                user(login: ${'$'}username) {

                    id
                    login
                    name
                    avatarUrl
                    bio

                    followers {
                        totalCount
                    }

                    following {
                        totalCount
                    }

                    repositories(
                        first: 100
                        ownerAffiliations: OWNER
                        privacy: PUBLIC
                    ) {

                        totalCount

                        nodes {
                            name
                            stargazerCount
                            forkCount

                            isArchived
                            isFork

                            createdAt
                            updatedAt

                            primaryLanguage {
                                name
                            }
                        }
                    }

                    contributionsCollection(
                        from: ${'$'}from
                        to: ${'$'}to
                    ) {

                        totalCommitContributions

                        totalIssueContributions

                        totalPullRequestContributions

                        totalPullRequestReviewContributions

                        totalRepositoryContributions

                        totalRepositoriesWithContributedCommits

                        totalRepositoriesWithContributedIssues

                        totalRepositoriesWithContributedPullRequests

                        totalRepositoriesWithContributedPullRequestReviews

                        contributionCalendar {

                            totalContributions

                            weeks {
                                contributionDays {
                                    contributionCount
                                    date
                                }
                            }
                        }
                    }
                }
            }
        """.trimIndent()

        return client.post(
            "https://api.github.com/graphql"
        ) {

            contentType(ContentType.Application.Json)

            headers.append(
                "Authorization",
                "Bearer $token"
            )

            setBody(
                GraphQLRequest(
                    query = query,
                    variables = GraphQLVariables(
                        username = username,
                        from = from,
                        to = to,
                    )
                )
            )

        }.body()
    }
}