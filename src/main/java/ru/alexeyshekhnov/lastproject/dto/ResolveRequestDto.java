package ru.alexeyshekhnov.lastproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResolveRequestDto {
    private Long taskId;
    private String answer;
}