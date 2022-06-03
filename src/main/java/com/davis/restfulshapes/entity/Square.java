package com.davis.restfulshapes.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Map;

@Entity
@DiscriminatorValue("square")
public class Square extends Shape{
    private Integer side;

    public Integer getSide() {
        return side;
    }

    public void setSide(Integer side) {
        this.side = side;
    }

    public void setArea() {
        super.setArea(side * side);
    }

    public void setPerimeter() {
        super.setPerimeter(4 * side);
    }

    // Converts a Map<String, String> to a Square entity
    public Square toSquare(Map<String, String> map) {
        if(map == null) {
            return null;
        }
        Square square = new Square();

        square.setSide(Integer.valueOf(map.get("side")));

        if (map.containsKey("area")) {
            square.setArea(Double.parseDouble(map.get("area")));
        }

        if (map.containsKey("perimeter")) {
            square.setPerimeter(Double.parseDouble(map.get("perimeter")));
        }
        return square;
    }
}
