package com.example.simodelservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResultDto {
    private String stackingClass;
    private float stackingConfidence;
    private String votedClass;
    private float votedConfidence;
}
