package com.example.backend;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class BackEndController {
    private final DerivativeCalculator derivativeCalculator;

    BackEndController() {
        this.derivativeCalculator = new DerivativeCalculator('x');
    }

    @GetMapping("/derive")
    @ResponseBody
    String derive(@RequestParam String equation) {
        // TODO: substitute characters for /
        equation = equation.replaceAll("_", "^");
        equation = equation.replaceAll("!", "+");
        ExpressionNode derivative = derivativeCalculator.TestDerivative(equation);
        return derivativeCalculator.toString(derivative);
    }
    // http://localhost:8080/derive/?equation=3
}
