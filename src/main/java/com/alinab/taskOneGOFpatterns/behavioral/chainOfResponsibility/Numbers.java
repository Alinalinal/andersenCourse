package com.alinab.taskOneGOFpatterns.behavioral.chainOfResponsibility;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
@Getter
public class Numbers {

    int numberOne;
    int numberTwo;
    String calculationRequest;

}
