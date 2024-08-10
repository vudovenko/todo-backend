package ru.vudovenko.backend.todo.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Возможные значения, по которым можно искать приоритеты
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrioritySearchValuesDTO {

    private String title;
    private String email;
}
