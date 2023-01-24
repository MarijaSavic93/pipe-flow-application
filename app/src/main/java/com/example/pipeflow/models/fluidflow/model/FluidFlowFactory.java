package com.example.pipeflow.models.fluidflow.model;

import androidx.annotation.NonNull;

import com.example.pipeflow.models.fluidflow.FluidFlowKeyValue;
import com.example.pipeflow.models.fluids.model.FluidCharacteristics;

public class FluidFlowFactory {

    public FluidFlow createFluidFlow(@NonNull FluidFlowKeyValue keyValue, @NonNull FluidCharacteristics fluidCharacteristics){

        switch(keyValue.getKey()){
            case HEAT_CAPACITY:
                return new FluidFlowHeatCapacity(fluidCharacteristics).init(keyValue.getValue());
            case VOLUME_M3H:
                return new FluidFlowVolumeM3H(fluidCharacteristics).init(keyValue.getValue());
            case VOLUME_M3S:
                return new FluidFlowVolumeM3S(fluidCharacteristics).init(keyValue.getValue());
            case VOLUME_LH:
                return new FluidFlowVolumeLH(fluidCharacteristics).init(keyValue.getValue());
            case VOLUME_LS:
                return new FluidFlowVolumeLS(fluidCharacteristics).init(keyValue.getValue());
            case MASS_KGH:
                return new FluidFlowMassKgH(fluidCharacteristics).init(keyValue.getValue());
            case MASS_KGS:
                return new FluidFlowMassKgS(fluidCharacteristics).init(keyValue.getValue());
            default:
                throw new IllegalArgumentException("Unknown type of fluid flow");
        }
    }
}
