package ru.skypro.pets_home_bot.api_bot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    private int petUserId;
    private String message;
    private LocalDateTime dateReport;
}
