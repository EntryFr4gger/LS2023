package pt.isel.ls.tasks.domain

import kotlinx.datetime.LocalDate

/**
 *
 * */
data class Card(
    val id: Int,
    val name: String,
    val description: String,
    val dueDate: LocalDate?,
    val boardId: Int,
    val listId: Int?
)