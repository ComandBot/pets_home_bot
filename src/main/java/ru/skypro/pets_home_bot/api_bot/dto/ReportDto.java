package ru.skypro.pets_home_bot.api_bot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.pets_home_bot.api_bot.model.Owner;
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
        Owner owner = report.getOwner();
        if (owner == null) {
            this.petUserId = -1;
            this.petId = -1;
            return;
        }
        this.petUserId = owner.getOwnerId().getPetUser().getId();
        this.petId = owner.getOwnerId().getPet().getId();
    }
}
