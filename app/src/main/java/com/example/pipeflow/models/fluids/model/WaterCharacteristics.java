package com.example.pipeflow.models.fluids.model;

import androidx.annotation.NonNull;

import com.example.pipeflow.models.fluids.Temperature;

public class WaterCharacteristics extends FluidCharacteristics {

    public WaterCharacteristics(@NonNull Temperature temperature) {
        super(temperature);
    }

    @Override
    public double density(){
        double t = getTemperature().averageTemperature();

        return 999.99399 + 0.04216485 * t - 0.007097451 * Math.pow(t, 2) + 0.00003509571 * Math.pow(t, 3) - 0.000000099037785 * Math.pow(t, 4);
    }

    @Override
    public double specificHeatCapacity(){
        double t = getTemperature().averageTemperature();

        return -0.000000003105179 * Math.pow(t, 5) + 0.000002002326 * Math.pow(t, 4) - 0.0004343752 * Math.pow(t, 3) + 0.05062776 * Math.pow(t, 2) - 2.385022 * t + 4214.812;
    }

    @Override
    public double viscosity(){
        double t = getTemperature().averageTemperature(),
               ni = -3.54056E-11 * Math.pow(t, 5) + 0.00000002298105 * Math.pow(t, 4) - 0.000005793728 * Math.pow(t, 3) + 0.0007254474 * Math.pow(t, 2) - 0.04841314 * t + 1.7368;

        return ni * 0.000001;
    }
}
