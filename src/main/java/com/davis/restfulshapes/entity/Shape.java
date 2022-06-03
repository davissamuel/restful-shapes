package com.davis.restfulshapes.entity;

import javax.persistence.*;

@Entity(name = "shapes")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public class Shape {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer shape_id;

    @Column(insertable = false, updatable = false)
    protected String type;

    private double area;

    private double perimeter;

    public String getType() {
        return type;
    }

    public Integer getShape_id() {
        return shape_id;
    }

    public void setShape_id(Integer shape_id) {
        this.shape_id = shape_id;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(double perimeter) {
        this.perimeter = perimeter;
    }
}
