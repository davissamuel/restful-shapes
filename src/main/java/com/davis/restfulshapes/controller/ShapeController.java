package com.davis.restfulshapes.controller;

import com.davis.restfulshapes.entity.Circle;
import com.davis.restfulshapes.entity.Square;
import com.davis.restfulshapes.repositories.CircleRepository;
import com.davis.restfulshapes.repositories.ShapeRepository;
import com.davis.restfulshapes.repositories.SquareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.davis.restfulshapes.entity.Shape;

import java.util.List;
import java.util.Map;

@RestController
public class ShapeController {
    @Autowired
    private ShapeRepository shapeRepository;

    @Autowired
    private CircleRepository circleRepository;

    @Autowired
    private SquareRepository squareRepository;

    @PostMapping("create")
    public ResponseEntity<Shape> createShape(@RequestBody Map<String, String> shapeMap) {
        if (shapeMap.containsKey("type")) {
            if (shapeMap.get("type").equalsIgnoreCase("circle")) {
                // Create a Circle entity
                // Asynchronously set the area and perimeter
                Circle circle = new Circle();
                circle = circle.toCircle(shapeMap);
                circle.setArea();
                circle.setPerimeter();

                // Save the newly created Circle entity to the database
                return ResponseEntity.ok(circleRepository.save(circle));
            } else if (shapeMap.get("type").equalsIgnoreCase("square")) {
                // Create a Square entity
                // Asynchronously set the area and perimeter
                Square square = new Square();
                square = square.toSquare(shapeMap);
                square.setArea();
                square.setPerimeter();

                // Save the newly created Square entity to the database
                return ResponseEntity.ok(squareRepository.save(square));
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @PostMapping("update/{id}")
    public ResponseEntity<Shape> updateShape(@PathVariable Integer id, @RequestBody Map<String, String> shapeMap) {
        // Retrieve the Shape entity if it exists
        Shape shape = shapeRepository.findById(id).isPresent() ? shapeRepository.findById(id).get() : null;
        if (shape == null || shapeMap.isEmpty()) {
            return null;
        }

        // Sets the area if provided
        if (shapeMap.containsKey("area")) {
            shape.setArea(Double.parseDouble(shapeMap.get("area")));
        }
        // Sets the perimeter if provided
        if (shapeMap.containsKey("perimeter")) {
            shape.setPerimeter(Double.parseDouble(shapeMap.get("perimeter")));
        }

        // Checks whether a Shape is a Square/Circle
        // Sets the radius/perimeter if provided
        // Saves the Square/Circle
        if (shape.getType().equalsIgnoreCase("circle")) {
            Circle circle = (Circle) circleRepository.findById(id).get();
            // If there is an update to the radius, update the radius, set the new area, and set the new perimeter
            if (shapeMap.containsKey("radius")) {
                circle.setRadius(Integer.valueOf(shapeMap.get("radius")));
                circle.setArea();
                circle.setPerimeter();
            }

            // Save the newly updated Circle entity to the database
            return ResponseEntity.ok(circleRepository.save(circle));
        } else if (shape.getType().equalsIgnoreCase("square")) {
            Square square = (Square) squareRepository.findById(id).get();
            // If there is an update to the side, update the side, set the new area, and set the new perimeter
            if (shapeMap.containsKey("side")) {
                square.setSide(Integer.valueOf(shapeMap.get("side")));
                square.setArea();
                square.setPerimeter();
            }

            // Save the newly updated Square entity to the database
            return ResponseEntity.ok(squareRepository.save(squareRepository.save(square)));
        } else {
            return null;
        }
    }

    @PostMapping("delete/{id}")
    public String deleteShape(@PathVariable Integer id) {
        if (shapeRepository.findById(id).isPresent()) {
            shapeRepository.deleteById(id);
            return "Shape Deleted";
        } else {
            return "Shape ID not found";
        }
    }

    @GetMapping("list")
    public ResponseEntity<List<Shape>> getAllShape() {
        return ResponseEntity.ok(shapeRepository.findAll());
    }

    @GetMapping("list/{id}")
    public ResponseEntity<Shape> getShape(@PathVariable Integer id) {
        return ResponseEntity.ok(shapeRepository.findById(id).isPresent() ? shapeRepository.findById(id).get() : null);
    }

    @GetMapping("area/{id}")
    public Double getArea (@PathVariable Integer id) {
        // Retrieve the Shape entity if it exists
        Shape shape = shapeRepository.findById(id).isPresent() ? shapeRepository.findById(id).get() : null;

        // If the Shape entity already has an area, return that value
        if (shape == null) {
            return null;
        } else if (shape.getArea() != 0) {
            return shape.getArea();
        }

        if (shape.getType().equalsIgnoreCase("circle")) {
            // Retrieves the Circle entity, sets the area, and saves the Circle entity in the database
            Circle circle = (Circle) circleRepository.findById(id).get();
            circle.setArea();
            circleRepository.save(circle);

            return circle.getArea();
        } else if (shape.getType().equalsIgnoreCase("square")) {
            // Retrieves the Square entity, sets the area, and saves the Square entity in the database
            Square square = (Square) squareRepository.findById(id).get();
            square.setArea();
            squareRepository.save(square);

            return square.getArea();
        } else {
            return null;
        }
    }

    @GetMapping("perimeter/{id}")
    public Double getPerimeter (@PathVariable Integer id) {
        // Retrieve the Shape entity if it exists
        Shape shape = shapeRepository.findById(id).isPresent() ? shapeRepository.findById(id).get() : null;

        // If the Shape entity already has an perimeter, return that value
        if (shape == null) {
            return null;
        } else if (shape.getPerimeter() != 0) {
            return shape.getPerimeter();
        }

        if (shape.getType().equalsIgnoreCase("circle")) {
            // Retrieves the Circle entity, sets the perimeter, and saves the Circle entity in the database
            Circle circle = (Circle) circleRepository.findById(id).get();
            circle.setPerimeter();
            circleRepository.save(circle);

            return circle.getPerimeter();
        } else if (shape.getType().equalsIgnoreCase("square")) {
            // Retrieves the Square entity, sets the perimeter, and saves the Square entity in the database
            Square square = (Square) squareRepository.findById(id).get();
            square.setPerimeter();
            squareRepository.save(square);

            return square.getPerimeter();
        } else {
            return null;
        }

    }


}
