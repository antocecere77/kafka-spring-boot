package com.antocecere77.kafkaproducer.entity;

import com.antocecere77.kafkaproducer.json.CustomLocalDateSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDate;

public class Employee {

    @JsonProperty("employee_id")
    private String employee;

    private String name;

    @JsonProperty("birth_date")
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    private LocalDate birthDate;

    public Employee(String employee, String name, LocalDate birthDate) {
        this.employee = employee;
        this.name = name;
        this.birthDate = birthDate;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
