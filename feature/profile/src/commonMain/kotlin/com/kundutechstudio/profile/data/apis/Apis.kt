package com.kundutechstudio.profile.data.apis

import com.kundutechstudio.profile.data.Models.ContributionResponse.GraphQLResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody



class Apis(
    private val client: HttpClient
) {
    private companion object {
        const val GITHUB_GRAPHQL_URL = "https://api.github.com/graphql"
    }

    suspend fun getProfile(
        username: String? = null,
        token: String? = null,
        from: String,
        to: String
    ): GraphQLResponse {



        val target = if (username.isNullOrBlank()) {
            "viewer"
        } else {
            "user(login: \"$username\")"
        }

        val query = """
            query {
                $target {
                    ...UserProfile
                }
            }

            fragment UserProfile on User {
                id
                login
                name
                avatarUrl
                bio
                createdAt
                url
                company
                location
                websiteUrl

                followers {
                    totalCount
                }

                following {
                    totalCount
                }

                repositories(
                    first: 5
                    ownerAffiliations: OWNER
                    orderBy: {
                        field: UPDATED_AT
                        direction: DESC
                    }
                ) {
                    totalCount

                    nodes {
                        name
                        description
                        stargazerCount
                        forkCount
                        updatedAt
                        isPrivate

                        primaryLanguage {
                            name
                        }
                    }
                }

                contributionsCollection(
                    from: "$from"
                    to: "$to"
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
                                date
                                contributionCount
                            }
                        }
                    }
                }
            }
        """.trimIndent()

        return client.post(GITHUB_GRAPHQL_URL) {

            token?.takeIf { it.isNotBlank() }
                ?.let {
                    header("Authorization", "Bearer $it")
                }

            header("Accept", "application/vnd.github+json")
            header("Content-Type", "application/json")
            setBody(mapOf("query" to query))

        }.body()
    }


}