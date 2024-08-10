package ru.vudovenko.backend.todo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vudovenko.backend.todo.entity.Priority;
import ru.vudovenko.backend.todo.search.PrioritySearchValuesDTO;
import ru.vudovenko.backend.todo.service.PriorityService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/priority")
public class PriorityController {

    private final PriorityService priorityService;

    @PostMapping("/all")
    public List<Priority> findAll(@RequestBody String email) {
        return priorityService.findAll(email);
    }


    @PostMapping("/add")
    public ResponseEntity<Priority> add(@RequestBody Priority priority) {

        // проверка на обязательные параметры
        if (priority.getId() != null && priority.getId() != 0) {
            // id создается автоматически в БД (autoincrement), поэтому его передавать не нужно, иначе может быть конфликт уникальности значения
            return new ResponseEntity("redundant param: id MUST be null",
                    HttpStatus.NOT_ACCEPTABLE);
        }

        // если передали пустое значение title
        if (priority.getTitle() == null || priority.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title",
                    HttpStatus.NOT_ACCEPTABLE);
        }

        // если передали пустое значение color
        if (priority.getColor() == null || priority.getColor().trim().length() == 0) {
            return new ResponseEntity("missed param: color",
                    HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(priorityService.add(priority));
    }


    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Priority priority) {
        // проверка на обязательные параметры
        if (priority.getId() == null || priority.getId() == 0) {
            return new ResponseEntity("missed param: id",
                    HttpStatus.NOT_ACCEPTABLE);
        }

        // если передали пустое значение title
        if (priority.getTitle() == null || priority.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title",
                    HttpStatus.NOT_ACCEPTABLE);
        }

        // если передали пустое значение color
        if (priority.getColor() == null || priority.getColor().trim().length() == 0) {
            return new ResponseEntity("missed param: color",
                    HttpStatus.NOT_ACCEPTABLE);
        }

        priorityService.update(priority);

        return new ResponseEntity(HttpStatus.OK); // просто отправляем статус 200 (операция прошла успешно)
    }

    // параметр id передаются не в BODY запроса, а в самом URL
    @PostMapping("/id")
    public ResponseEntity<Priority> findById(@RequestBody Long id) {
        Priority priority;
        try {
            priority = priorityService.findById(id);
        } catch (NoSuchElementException e) { // если объект не будет найден
            return new ResponseEntity("Priority with id " + id + " not found",
                    HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(priority);
    }

    // для удаления используем типа запроса put, а не delete, т.к. он позволяет передавать значение в body, а не в адресной строке
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {

        try {
            Priority priority = priorityService.findById(id);
            priorityService.deleteById(priority.getId());
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Priority with id " + id + " not found",
                    HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(HttpStatus.OK); // просто отправляем статус 200 (операция прошла успешно)
    }


    // поиск по любым параметрам PrioritySearchValues
    @PostMapping("/search")
    public ResponseEntity<List<Priority>> search(@RequestBody PrioritySearchValuesDTO prioritySearchValues) {

        // проверка на обязательные параметры
        if (prioritySearchValues.getEmail() == null || prioritySearchValues.getEmail().trim().isEmpty()) {
            return new ResponseEntity("missed param: email", HttpStatus.NOT_ACCEPTABLE);
        }

        List<Priority> priorities = priorityService
                .findByTitle(prioritySearchValues.getTitle(), prioritySearchValues.getEmail());

        return ResponseEntity.ok(priorities);
    }
}
