package app.erickalvz.domain.models


data class PagedCollection<T>(
    val items: List<T>,
    val currentPage: Int,
    val pageSize: Int
)