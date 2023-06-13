package com.lemonsqueeze.lemonsqueezebe.model.entity.generic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponse {
    private Meta meta;
    private Object data = null;
}
