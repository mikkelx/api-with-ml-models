package com.example.simodelservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ModelResultDto {
    private String m1_class;
    private float m1_confidence;
    private String m2_class;
    private float m2_confidence;
    private String m3_class;
    private float m3_confidence;
    private String stackingClass;
    private float stackingConfidence;
    private String votedClass;
    private float votedConfidence;
}
