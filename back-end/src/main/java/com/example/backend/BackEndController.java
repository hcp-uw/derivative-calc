package com.example.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
