package com.example.pipeflow.models.fluids.model;

import androidx.annotation.NonNull;

import com.example.pipeflow.models.fluids.Temperature;

public abstract class FluidCharacteristics {
    private final Temperature temperature;

    public FluidCharacteristics(@NonNull Temperature temperature){
        this.temperature = temperature;
    }

    public Temperature getTemperature(){
        return this.temperature;
    }

    public abstract double density();
    public abstract double specificHeatCapacity();
    public abstract double viscosity();
}
