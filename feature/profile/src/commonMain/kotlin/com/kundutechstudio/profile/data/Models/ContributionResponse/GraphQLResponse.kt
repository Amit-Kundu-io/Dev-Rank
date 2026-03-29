package com.kundutechstudio.profile.data.Models.ContributionResponse

import kotlinx.serialization.Serializable

@Serializable
data class GraphQLResponse(
    val data: Data
)

@Serializable
data class Data(
    val user: User
)

@Serializable
data class User(
    val contributionsCollection: ContributionsCollection
)

@Serializable
data class ContributionsCollection(
    val contributionCalendar: ContributionCalendar
)

@Serializable
data class ContributionCalendar(
    val weeks: List<Week>
)

@Serializable
data class Week(
    val contributionDays: List<Day>
)

@Serializable
data class Day(
    val date: String,
    val contributionCount: Int
)