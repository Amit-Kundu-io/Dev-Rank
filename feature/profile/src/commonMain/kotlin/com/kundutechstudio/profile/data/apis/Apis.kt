package com.kundutechstudio.profile.data.apis

import com.kundutechstudio.profile.data.Models.ContributionResponse.GraphQLResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class Apis(
    private val client: HttpClient
) {

    suspend fun getContributionGraph(
        username: String,
        token: String?
    ): GraphQLResponse {

        val query = """
            query {
              user(login: "$username") {
                contributionsCollection {
                  contributionCalendar {
                    weeks {
                      contributionDays {
                        date
                        contributionCount
                      }
                    }
                  }
                }
              }
            }
        """.trimIndent()

        return client.post("https://api.github.com/graphql") {
            token?.let {
                header("Authorization", "Bearer 42fd16372b618b9e217c")
            }

            header("Content-Type", "application/json")

            setBody(
                mapOf("query" to query)
            )
        }.body()
    }
}