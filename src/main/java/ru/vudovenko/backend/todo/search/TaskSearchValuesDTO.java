package ru.vudovenko.backend.todo.search;

import java.util.Date;

/**
 * DTO с возможными значениями, по которым можно искать задачи + значения сортировки
 * <p>
 * <i>такие же названия должны быть у объекта на frontend</i>
 */
public record TaskSearchValuesDTO(String title, Integer completed, Long priorityId, Long categoryId, String email,
                                  Date dateFrom, Date dateTo,
                                  Integer pageNumber, Integer pageSize,
                                  String sortColumn, String sortDirection) {
}
