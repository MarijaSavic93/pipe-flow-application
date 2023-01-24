package com.example.pipeflow.models.fluidflow.model;

import androidx.annotation.NonNull;

import com.example.pipeflow.models.fluids.model.FluidCharacteristics;

public class FluidFlowMassKgH extends FluidFlow {
    protected FluidFlowMassKgH(@NonNull FluidCharacteristics fluidCharacteristics) {
        super(fluidCharacteristics);
    }

    @Override
    protected void calculate(double value) {
        double density = getFluidCharacteristics().density(),
                specificHeatCapacity = getFluidCharacteristics().specificHeatCapacity(),
                deltaTemperature = getFluidCharacteristics().getTemperature().deltaTemperature();

        setMassFlowKgH(value);
        setVolumeFlowM3H(getMassFlowKgH() / density);
        setHeatCapacity(getVolumeFlowM3H() * density * specificHeatCapacity * deltaTemperature / 3600);
        setVolumeFlowM3S(getVolumeFlowM3H() / 3600);
        setVolumeFlowLH(getVolumeFlowM3H() * 1000);
        setVolumeFlowLS(getVolumeFlowLH() / 3600);
        setMassFlowKgS(getMassFlowKgH() / 3600);

    }

    @Override
    protected FluidFlowMassKgH init(double value) {
        calculate(value);
        return this;
    }
}
