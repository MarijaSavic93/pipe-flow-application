package com.example.pipeflow.models;

public class Pipe {
    private final double outerDiameter, wallThickness, roughness;

    public Pipe(double outerDiameter, double wallThickness, double roughness){
        this.outerDiameter = outerDiameter;
        this.wallThickness = wallThickness;
        this.roughness = roughness;
    }

    public double getRoughness(){
        return this.roughness;
    }
    public double getWallThickness(){ return this.wallThickness; }
    public double getOuterDiameter(){
        return this.outerDiameter;
    }

    public double innerPipeDiameterInMeters(){
        return (outerDiameter - 2 * wallThickness) / 1000;
    }
    public double pipeArea(){
        double du = innerPipeDiameterInMeters();

        return (Math.pow(du, 2) * Math.PI) / 4;
    }
}
