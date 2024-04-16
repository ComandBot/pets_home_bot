package ru.skypro.pets_home_bot.api_bot.model_api;

import jakarta.persistence.*;
import lombok.Data;
import ru.skypro.pets_home_bot.api_bot.model.Shelter;

import java.io.Serializable;
import java.util.Objects;
@Entity
public class ShelterInfoApi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String typeInfo;
    private String definition;
    private int shelterId;


    public ShelterInfoApi() {
    }

    public ShelterInfoApi(int id, String typeInfo, String definition, int shelterId) {
        this.typeInfo = typeInfo;
        this.definition = definition;
        this.shelterId = shelterId;
        this.id = id;
    }

    public String getTypeInfo() {
        return typeInfo;
    }

    public void setTypeInfo(String typeInfo) {
        this.typeInfo = typeInfo;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public int getShelterId() {
        return shelterId;
    }

    public void setShelterId(int shelterId) {
        this.shelterId = shelterId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShelterInfoApi that = (ShelterInfoApi) o;
        return id == that.id && shelterId == that.shelterId && Objects.equals(typeInfo, that.typeInfo) && Objects.equals(definition, that.definition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typeInfo, definition, shelterId);
    }

    @Override
    public String toString() {
        return "ShelterInfoApi{" +
                "typeInfo='" + typeInfo + '\'' +
                ", definition='" + definition + '\'' +
                ", shelterId=" + shelterId +
                '}';
    }
}
