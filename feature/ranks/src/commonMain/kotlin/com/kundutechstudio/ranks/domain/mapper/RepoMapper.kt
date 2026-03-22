package com.kundutechstudio.ranks.domain.mapper

import com.kundutechstudio.ranks.data.models.RepoResponse.TopStarredRepoItem
import com.kundutechstudio.ranks.domain.dao.RepoItemDAO
import com.kundutechstudio.theme.Helpers.Helpers.formatStars
import com.kundutechstudio.theme.Helpers.Helpers.getLanguageColor
import com.kundutechstudio.theme.Helpers.Helpers.getTrendingType

fun TopStarredRepoItem.toRepoItem(rank: Int): RepoItemDAO {
    return RepoItemDAO(
        name = name.orEmpty(),
        owner = owner?.login.orEmpty(),
        description = description.orEmpty(),
        stars = formatStars(stars ?: 0),
        language = language.orEmpty(),
        langColor = getLanguageColor(language),
        rank = rank,
        trendingLabel = null,
        trendingType = getTrendingType(stars ?: 0)
    )
}