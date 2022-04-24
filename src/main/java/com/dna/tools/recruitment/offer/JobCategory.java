package com.dna.tools.recruitment.offer;

import lombok.Getter;

@Getter
public enum JobCategory {
    IT ("IT"), FOOD_AND_DRINKS("Food & Drinks"), OFFICE("Office"), COURIER("Courier"),
    SHOP_ASSISTANT("Shop assistant");

    private final String name;

    JobCategory(String name) {
        this.name = name;
    }

}
