package com.example.backend;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class BackEndController {
    private final DerivativeCalculator derivativeCalculator;

    BackEndController() {
        this.derivativeCalculator = new DerivativeCalculator('x');
    }

    @GetMapping("/derive/{equation}")
    String derive(@PathVariable String equation) {
        // TODO: substitute characters for /
        ExpressionNode derivative = derivativeCalculator.TestDerivative(equation);
        return derivativeCalculator.toString(derivative);
    }
}
