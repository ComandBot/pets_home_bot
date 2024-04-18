package ru.skypro.pets_home_bot.api_bot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.pets_home_bot.api_bot.model.Report;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDto {
    private int id;
    private String diet;
    private String condition;
    private String behaviour;
    private LocalDateTime dateTime;
    private boolean isViewed;
    private int petUserId;
    private int petId;

    public ReportDto(Report report) {
        this.id = report.getId();;
        this.diet = report.getDiet();
        this.condition = report.getCondition();
        this.behaviour = report.getBehaviour();
        this.dateTime = report.getDateReport();
        this.isViewed = report.isViewed();
        this.petUserId = report.getOwner().getOwnerId().getPetUser().getId();
        this.petId = report.getOwner().getOwnerId().getPet().getId();
    }
}
