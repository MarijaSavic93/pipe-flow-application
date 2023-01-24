package com.example.pipeflow.models.fluidflow;

import androidx.annotation.NonNull;

public class FluidFlowKeyValue {
    private final FlowType key;
    private final double value;

    public FluidFlowKeyValue(@NonNull FlowType key, double value){
        this.key = key;
        this.value = value;
    }

    public FlowType getKey(){
        return this.key;
    }
    public double getValue(){
        return this.value;
    }
}
