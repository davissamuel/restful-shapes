package com.davis.restfulshapes.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Map;

@Entity
@DiscriminatorValue("circle")
public class Circle extends Shape{
    private Integer radius;

    public Integer getRadius() {
        return radius;
    }

    public void setRadius(Integer radius) {
        this.radius = radius;
    }

    public void setArea() {
        super.setArea(radius * radius * Math.PI);
    }

    public void setPerimeter() {
        super.setPerimeter(2 * Math.PI * radius);
    }

    // Converts a Map<String, String> to a Circle entity
    public Circle toCircle(Map<String, String> map) {
        if(map == null) {
            return null;
        }
        Circle circle = new Circle();

        circle.setRadius(Integer.valueOf(map.get("radius")));

        if (map.containsKey("area")) {
            circle.setArea(Double.parseDouble(map.get("area")));
        }

        if (map.containsKey("perimeter")) {
            circle.setPerimeter(Double.parseDouble(map.get("perimeter")));
        }
        return circle;
    }
}
