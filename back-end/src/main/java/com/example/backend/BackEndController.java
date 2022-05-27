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
        // we substitute characters for / and ^, expect ! and _ for both respectively
        equation = equation.replaceAll("_", "^");
        equation = equation.replaceAll("!", "+");
        ExpressionNode derivative = derivativeCalculator.simplify(derivativeCalculator.TestDerivative(equation));
        return derivativeCalculator.toLatex(derivative);
    }
    // http://localhost:8080/derive/?equation=3
}
