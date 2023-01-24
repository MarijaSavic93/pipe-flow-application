package com.example.pipeflow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pipeflow.models.Pipe;
import com.example.pipeflow.models.PipeFlow;
import com.example.pipeflow.models.fluidflow.FlowType;
import com.example.pipeflow.models.fluidflow.FluidFlowKeyValue;
import com.example.pipeflow.models.fluidflow.model.FluidFlow;
import com.example.pipeflow.models.fluidflow.model.FluidFlowFactory;
import com.example.pipeflow.models.fluids.Temperature;
import com.example.pipeflow.models.fluids.model.WaterCharacteristics;
import com.example.pipeflow.utility.StringNumberOperations;
import com.example.pipeflow.validators.PipeValidator;
import com.example.pipeflow.validators.RangeValidatable;
import com.example.pipeflow.validators.TemperatureValidator;

public class MainActivity extends AppCompatActivity implements View.OnFocusChangeListener, View.OnClickListener {
    EditText supplyTemperatureEdit, returnTemperatureEdit, heatCapacityEdit, volumeFlowM3HEdit, volumeFlowM3SEdit, volumeFlowLHEdit,
             volumeFlowLSEdit, massFlowKgHEdit, massFlowKgSEdit, outerDiameterEdit, wallThicknessEdit, roughnessEdit;
    TextView fluidVelocityResultTv, pressureDropResultTv, specificHeatCapacityResultTv, densityResultTv, viscosityResultTv,
             frictionCoefficientResultTv, heatCapacityTv, volumeFlowM3HTv, volumeFlowM3STv, volumeFlowLHTv,
             volumeFlowLSTv, massFlowKgHTv, massFlowKgSTv;
    Button calculateButton, clearButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init(){
        supplyTemperatureEdit = findViewById(R.id.supply_temp_edit);
        returnTemperatureEdit = findViewById(R.id.return_temp_edit);
        heatCapacityEdit = findViewById(R.id.heat_capacity_edit);
        volumeFlowM3HEdit = findViewById(R.id.volume_flow_m3h_edit);
        volumeFlowM3SEdit = findViewById(R.id.volume_flow_m3s_edit);
        volumeFlowLHEdit = findViewById(R.id.volume_flow_lh_edit);
        volumeFlowLSEdit = findViewById(R.id.volume_flow_ls_edit);
        massFlowKgHEdit = findViewById(R.id.mass_flow_kgh_edit);
        massFlowKgSEdit = findViewById(R.id.mass_flow_kgs_edit);
        outerDiameterEdit = findViewById(R.id.outer_diameter_edit);
        wallThicknessEdit = findViewById(R.id.wall_thickness_edit);
        roughnessEdit = findViewById(R.id.roughness_edit);

        fluidVelocityResultTv = findViewById(R.id.fluid_velocity_result_tv);
        pressureDropResultTv = findViewById(R.id.pressure_drop_result_tv);
        specificHeatCapacityResultTv = findViewById(R.id.specific_heat_capacity_result_tv);
        densityResultTv = findViewById(R.id.density_result_tv);
        viscosityResultTv = findViewById(R.id.viscosity_result_tv);
        frictionCoefficientResultTv = findViewById(R.id.friction_coefficient_result_tv);

        heatCapacityTv = findViewById(R.id.heat_capacity_tv);
        volumeFlowM3HTv = findViewById(R.id.volume_flow_m3h_tv);
        volumeFlowM3STv = findViewById(R.id.volume_flow_m3s_tv);
        volumeFlowLHTv = findViewById(R.id.volume_flow_lh_tv);
        volumeFlowLSTv = findViewById(R.id.volume_flow_ls_tv);
        massFlowKgHTv = findViewById(R.id.mass_flow_kgh_tv);
        massFlowKgSTv = findViewById(R.id.mass_flow_kgs_tv);

        calculateButton = findViewById(R.id.calc_btn);
        calculateButton.setOnClickListener(this);

        clearButton = findViewById(R.id.clear_btn);
        clearButton.setOnClickListener(this);

        supplyTemperatureEdit.setOnFocusChangeListener(this);
        returnTemperatureEdit.setOnFocusChangeListener(this);
        heatCapacityEdit.setOnFocusChangeListener(this);
        volumeFlowM3HEdit.setOnFocusChangeListener(this);
        volumeFlowM3SEdit.setOnFocusChangeListener(this);
        volumeFlowLHEdit.setOnFocusChangeListener(this);
        volumeFlowLSEdit.setOnFocusChangeListener(this);
        massFlowKgHEdit.setOnFocusChangeListener(this);
        massFlowKgSEdit.setOnFocusChangeListener(this);
        outerDiameterEdit.setOnFocusChangeListener(this);
        wallThicknessEdit.setOnFocusChangeListener(this);
        roughnessEdit.setOnFocusChangeListener(this);
    }

    private void setHeatCapacityInvisible(){
        heatCapacityEdit.setText("");

        heatCapacityTv.setVisibility(View.GONE);
        heatCapacityEdit.setVisibility(View.GONE);

    }
    private void setHeatCapacityVisible(){
        heatCapacityTv.setVisibility(View.VISIBLE);
        heatCapacityEdit.setVisibility(View.VISIBLE);
    }

    private void setVolumeFlowM3HInvisible(){
        volumeFlowM3HEdit.setText("");

        volumeFlowM3HTv.setVisibility(View.GONE);
        volumeFlowM3HEdit.setVisibility(View.GONE);
    }
    private void setVolumeFlowM3HVisible(){
        volumeFlowM3HTv.setVisibility(View.VISIBLE);
        volumeFlowM3HEdit.setVisibility(View.VISIBLE);
    }

    private void setVolumeFlowM3SInvisible(){
        volumeFlowM3SEdit.setText("");

        volumeFlowM3STv.setVisibility(View.GONE);
        volumeFlowM3SEdit.setVisibility(View.GONE);
    }
    private void setVolumeFlowM3SVisible(){
        volumeFlowM3STv.setVisibility(View.VISIBLE);
        volumeFlowM3SEdit.setVisibility(View.VISIBLE);
    }

    private void setVolumeFlowLHInvisible(){
        volumeFlowLHEdit.setText("");

        volumeFlowLHTv.setVisibility(View.GONE);
        volumeFlowLHEdit.setVisibility(View.GONE);
    }
    private void setVolumeFlowLHVisible(){
        volumeFlowLHTv.setVisibility(View.VISIBLE);
        volumeFlowLHEdit.setVisibility(View.VISIBLE);
    }

    private void setVolumeFlowLSInvisible(){
        volumeFlowLSEdit.setText("");

        volumeFlowLSTv.setVisibility(View.GONE);
        volumeFlowLSEdit.setVisibility(View.GONE);
    }
    private void setVolumeFlowLSVisible(){
        volumeFlowLSTv.setVisibility(View.VISIBLE);
        volumeFlowLSEdit.setVisibility(View.VISIBLE);
    }

    private void setMassFlowKgHInvisible(){
        massFlowKgHEdit.setText("");

        massFlowKgHTv.setVisibility(View.GONE);
        massFlowKgHEdit.setVisibility(View.GONE);
    }
    private void setMassFlowKgHVisible(){
        massFlowKgHTv.setVisibility(View.VISIBLE);
        massFlowKgHEdit.setVisibility(View.VISIBLE);
    }

    private void setMassFlowKgSInvisible(){
        massFlowKgSEdit.setText("");

        massFlowKgSTv.setVisibility(View.GONE);
        massFlowKgSEdit.setVisibility(View.GONE);
    }
    private void setMassFlowKgSVisible(){
        massFlowKgSTv.setVisibility(View.VISIBLE);
        massFlowKgSEdit.setVisibility(View.VISIBLE);
    }

    private void setInvisibleForHeatCapacity(){
        setVolumeFlowM3HInvisible();
        setVolumeFlowM3SInvisible();
        setVolumeFlowLHInvisible();
        setVolumeFlowLSInvisible();
        setMassFlowKgHInvisible();
        setMassFlowKgSInvisible();
    }
    private void setInvisibleForVolumeFlowM3H(){
        setHeatCapacityInvisible();
        setVolumeFlowM3SInvisible();
        setVolumeFlowLHInvisible();
        setVolumeFlowLSInvisible();
        setMassFlowKgHInvisible();
        setMassFlowKgSInvisible();
    }
    private void setInvisibleForVolumeFlowM3S(){
        setHeatCapacityInvisible();
        setVolumeFlowM3HInvisible();
        setVolumeFlowLHInvisible();
        setVolumeFlowLSInvisible();
        setMassFlowKgHInvisible();
        setMassFlowKgSInvisible();
    }
    private void setInvisibleForVolumeFlowLH(){
        setHeatCapacityInvisible();
        setVolumeFlowM3HInvisible();
        setVolumeFlowM3SInvisible();
        setVolumeFlowLSInvisible();
        setMassFlowKgHInvisible();
        setMassFlowKgSInvisible();
    }
    private void setInvisibleForVolumeFlowLS(){
        setHeatCapacityInvisible();
        setVolumeFlowM3HInvisible();
        setVolumeFlowM3SInvisible();
        setVolumeFlowLHInvisible();
        setMassFlowKgHInvisible();
        setMassFlowKgSInvisible();
    }
    private void setInvisibleForMassFlowKgH(){
        setHeatCapacityInvisible();
        setVolumeFlowM3HInvisible();
        setVolumeFlowM3SInvisible();
        setVolumeFlowLHInvisible();
        setVolumeFlowLSInvisible();
        setMassFlowKgSInvisible();
    }
    private void setInvisibleForMassFlowKgS(){
        setHeatCapacityInvisible();
        setVolumeFlowM3HInvisible();
        setVolumeFlowM3SInvisible();
        setVolumeFlowLHInvisible();
        setVolumeFlowLSInvisible();
        setMassFlowKgHInvisible();
    }
    private void setAllFlowsVisible(){
        setHeatCapacityVisible();
        setVolumeFlowM3HVisible();
        setVolumeFlowM3SVisible();
        setVolumeFlowLHVisible();
        setVolumeFlowLSVisible();
        setMassFlowKgHVisible();
        setMassFlowKgSVisible();
    }

    private void setVisibilityHeatCapacity(String string){
        boolean isEmpty = new StringNumberOperations().isEmpty(string);
        if(!isEmpty){
            setInvisibleForHeatCapacity();
        }else{
            setAllFlowsVisible();
        }
    }
    private void setVisibilityVolumeFlowM3H(String string){
        boolean isEmpty = new StringNumberOperations().isEmpty(string);
        if(!isEmpty){
            setInvisibleForVolumeFlowM3H();
        }else{
            setAllFlowsVisible();
        }
    }
    private void setVisibilityVolumeFlowM3S(String string){
        boolean isEmpty = new StringNumberOperations().isEmpty(string);
        if(!isEmpty){
            setInvisibleForVolumeFlowM3S();
        }else{
            setAllFlowsVisible();
        }
    }
    private void setVisibilityVolumeFlowLH(String string){
        boolean isEmpty = new StringNumberOperations().isEmpty(string);
        if(!isEmpty){
            setInvisibleForVolumeFlowLH();
        }else{
            setAllFlowsVisible();
        }
    }
    private void setVisibilityVolumeFlowLS(String string){
        boolean isEmpty = new StringNumberOperations().isEmpty(string);
        if(!isEmpty){
            setInvisibleForVolumeFlowLS();
        }else{
            setAllFlowsVisible();
        }
    }
    private void setVisibilityMassFlowKgH(String string){
        boolean isEmpty = new StringNumberOperations().isEmpty(string);
        if(!isEmpty){
            setInvisibleForMassFlowKgH();
        }else{
            setAllFlowsVisible();
        }
    }
    private void setVisibilityMassFlowKgS(String string){
        boolean isEmpty = new StringNumberOperations().isEmpty(string);
        if(!isEmpty){
            setInvisibleForMassFlowKgS();
        }else{
            setAllFlowsVisible();
        }
    }

    private void setValueInRange(String text, @NonNull EditText editText, @NonNull RangeValidatable validator){
        StringNumberOperations operations = new StringNumberOperations();
        double value = operations.returnDoubleFromText(text);
        int id = editText.getId();
        boolean isInRange = validator.validateRange(value);

        if (!isInRange) {
            if(id == R.id.outer_diameter_edit){
                setTextAndErrorMessage(((PipeValidator)validator).getErrorMessageOuterDiameter(), Double.toString(PipeValidator.OUTER_DIAMETER_MIN_VALUE), editText);
            }else if(id == R.id.return_temp_edit || id == R.id.supply_temp_edit){
                setTextAndErrorMessage(((TemperatureValidator)validator).getErrorMessage(), Double.toString(TemperatureValidator.TEMPERATURE_MAX_VALUE), editText);
            }
        }
    }

    private FluidFlowKeyValue getInsertedFlow(){
        StringNumberOperations operations = new StringNumberOperations();

        String heatCapacityText = operations.formatNumber(heatCapacityEdit.getText().toString()),
               volumeFlowM3HText = operations.formatNumber(volumeFlowM3HEdit.getText().toString()),
               volumeFlowM3SText = operations.formatNumber(volumeFlowM3SEdit.getText().toString()),
               volumeFlowLHText = operations.formatNumber(volumeFlowLHEdit.getText().toString()),
               volumeFlowLSText = operations.formatNumber(volumeFlowLSEdit.getText().toString()),
               massFlowKgHText = operations.formatNumber(massFlowKgHEdit.getText().toString()),
               massFlowKgSText = operations.formatNumber(massFlowKgSEdit.getText().toString());

        boolean isHeatCapacityEmpty = operations.isEmpty(heatCapacityText),
                isVolumeFlowM3HEmpty = operations.isEmpty(volumeFlowM3HText),
                isVolumeFlowM3SEmpty = operations.isEmpty(volumeFlowM3SText),
                isVolumeFlowLHEmpty = operations.isEmpty(volumeFlowLHText),
                isVolumeFlowLSEmpty = operations.isEmpty(volumeFlowLSText),
                isMassFlowKgHEmpty = operations.isEmpty(massFlowKgHText),
                isMassFlowKgSEmpty = operations.isEmpty(massFlowKgSText);
        if(!isHeatCapacityEmpty){
            return new FluidFlowKeyValue(FlowType.HEAT_CAPACITY, Double.parseDouble(heatCapacityText));
        }
        if(!isVolumeFlowM3HEmpty){
            return new FluidFlowKeyValue(FlowType.VOLUME_M3H, Double.parseDouble(volumeFlowM3HText));
        }
        if(!isVolumeFlowM3SEmpty){
            return new FluidFlowKeyValue(FlowType.VOLUME_M3S, Double.parseDouble(volumeFlowM3SText));
        }
        if(!isVolumeFlowLHEmpty){
            return new FluidFlowKeyValue(FlowType.VOLUME_LH, Double.parseDouble(volumeFlowLHText));
        }
        if(!isVolumeFlowLSEmpty){
            return new FluidFlowKeyValue(FlowType.VOLUME_LS, Double.parseDouble(volumeFlowLSText));
        }
        if(!isMassFlowKgHEmpty){
            return new FluidFlowKeyValue(FlowType.MASS_KGH, Double.parseDouble(massFlowKgHText));
        }
        if(!isMassFlowKgSEmpty){
            return new FluidFlowKeyValue(FlowType.MASS_KGS, Double.parseDouble(massFlowKgSText));
        }
        return new FluidFlowKeyValue(FlowType.HEAT_CAPACITY, 0);
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if(view instanceof EditText){
            EditText editText = (EditText) view;
            int id = editText.getId();

            if(b){
                if (id == R.id.heat_capacity_edit) {
                    setInvisibleForHeatCapacity();
                }else if (id == R.id.volume_flow_m3h_edit) {
                    setInvisibleForVolumeFlowM3H();
                }else if (id == R.id.volume_flow_m3s_edit) {
                    setInvisibleForVolumeFlowM3S();
                }else if (id == R.id.volume_flow_lh_edit) {
                    setInvisibleForVolumeFlowLH();
                }else if (id == R.id.volume_flow_ls_edit) {
                    setInvisibleForVolumeFlowLS();
                }else if (id == R.id.mass_flow_kgh_edit) {
                    setInvisibleForMassFlowKgH();
                }else if (id == R.id.mass_flow_kgs_edit) {
                    setInvisibleForMassFlowKgS();
                }
            }else{
                String text = editText.getText().toString();
                String formattedText = new StringNumberOperations().formatNumber(text);

                editText.setText(formattedText);

                if (id == R.id.heat_capacity_edit) {
                    setVisibilityHeatCapacity(formattedText);
                }else if (id == R.id.volume_flow_m3h_edit) {
                    setVisibilityVolumeFlowM3H(formattedText);
                }else if (id == R.id.volume_flow_m3s_edit) {
                    setVisibilityVolumeFlowM3S(formattedText);
                }else if (id == R.id.volume_flow_lh_edit) {
                    setVisibilityVolumeFlowLH(formattedText);
                }else if (id == R.id.volume_flow_ls_edit) {
                    setVisibilityVolumeFlowLS(formattedText);
                }else if (id == R.id.mass_flow_kgh_edit) {
                    setVisibilityMassFlowKgH(formattedText);
                }else if (id == R.id.mass_flow_kgs_edit) {
                    setVisibilityMassFlowKgS(formattedText);
                }else if (id == R.id.return_temp_edit || id == R.id.supply_temp_edit) {
                    setValueInRange(formattedText, editText, new TemperatureValidator());
                }else if (id == R.id.outer_diameter_edit) {
                    setValueInRange(formattedText, editText, new PipeValidator());
                }
            }
        }
    }

    private void clearErrorsFromValidatableEditTexts(){
        returnTemperatureEdit.setError(null);
        supplyTemperatureEdit.setError(null);
        outerDiameterEdit.setError(null);
        wallThicknessEdit.setError(null);
        roughnessEdit.setError(null);
    }
    private void clearResults(){
        pressureDropResultTv.setText("");
        fluidVelocityResultTv.setText("");
        specificHeatCapacityResultTv.setText("");
        densityResultTv.setText("");
        viscosityResultTv.setText("");
        frictionCoefficientResultTv.setText("");
    }
    private void clearAll(){
        clearErrorsFromValidatableEditTexts();
        setAllFlowsVisible();

        returnTemperatureEdit.setText("");
        supplyTemperatureEdit.setText("");
        heatCapacityEdit.setText("");
        massFlowKgHEdit.setText("");
        massFlowKgSEdit.setText("");
        volumeFlowLHEdit.setText("");
        volumeFlowLSEdit.setText("");
        volumeFlowM3HEdit.setText("");
        volumeFlowM3SEdit.setText("");
        roughnessEdit.setText("");
        outerDiameterEdit.setText("");
        wallThicknessEdit.setText("");

        clearResults();
    }

    private void setTextAndErrorMessage(String error, String text, @NonNull EditText editText){
        editText.setError(error);
        editText.setText(text);
    }

    private void hideKeyboard(@NonNull View view){
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(@NonNull View view) {
        View focusedView = getCurrentFocus();
        if (focusedView != null) {
            hideKeyboard(focusedView);
            focusedView.clearFocus();
        }

        int id = view.getId();

        if(id == R.id.clear_btn){
            clearAll();
        }
        if(id == R.id.calc_btn){
            clearResults();
            clearErrorsFromValidatableEditTexts();

            String returnTemperatureText = returnTemperatureEdit.getText().toString(),
                   supplyTemperatureText = supplyTemperatureEdit.getText().toString(),
                   outerDiameterText = outerDiameterEdit.getText().toString(),
                   wallThicknessText = wallThicknessEdit.getText().toString(),
                   roughnessText = roughnessEdit.getText().toString();

            StringNumberOperations operations = new StringNumberOperations();

            Temperature temperature = new Temperature(operations.returnDoubleFromText(supplyTemperatureText), operations.returnDoubleFromText(returnTemperatureText));
            Pipe pipe = new Pipe(operations.returnDoubleFromText(outerDiameterText), operations.returnDoubleFromText(wallThicknessText), operations.returnDoubleFromText(roughnessText));

            TemperatureValidator temperatureValidator = new TemperatureValidator(temperature);
            PipeValidator pipeValidator = new PipeValidator(pipe);

            boolean outerDiameterIsInRange = pipeValidator.validateRange(pipe.getOuterDiameter()),
                    temperatureValidated = temperatureValidator.validate(),
                    pipeValidated = pipeValidator.validate();

            if(outerDiameterIsInRange && temperatureValidated && pipeValidated){
                WaterCharacteristics waterCharacteristics = new WaterCharacteristics(temperature);

                FluidFlowKeyValue whichFlow = getInsertedFlow();
                FluidFlow waterFlow = new FluidFlowFactory().createFluidFlow(whichFlow, waterCharacteristics);

                PipeFlow pipeFlow = new PipeFlow(pipe, waterFlow);

                if(pipeFlow.velocity() > 1.5){
                    fluidVelocityResultTv.setTextColor(Color.RED);
                }else{
                    int defaultTextColor = pressureDropResultTv.getTextColors().getDefaultColor();
                    fluidVelocityResultTv.setTextColor(defaultTextColor);
                }

                setAllFlowsVisible();

                returnTemperatureEdit.setText(Double.toString(temperature.getReturnTemperature()));
                supplyTemperatureEdit.setText(Double.toString(temperature.getSupplyTemperature()));

                outerDiameterEdit.setText(Double.toString(pipe.getOuterDiameter()));
                wallThicknessEdit.setText(Double.toString(pipe.getWallThickness()));
                roughnessEdit.setText(Double.toString(pipe.getRoughness()));

                heatCapacityEdit.setText(Double.toString(waterFlow.getHeatCapacity()));
                volumeFlowM3HEdit.setText(Double.toString(waterFlow.getVolumeFlowM3H()));
                volumeFlowM3SEdit.setText(Double.toString(waterFlow.getVolumeFlowM3S()));
                volumeFlowLHEdit.setText(Double.toString(waterFlow.getVolumeFlowLH()));
                volumeFlowLSEdit.setText(Double.toString(waterFlow.getVolumeFlowLS()));
                massFlowKgHEdit.setText(Double.toString(waterFlow.getMassFlowKgH()));
                massFlowKgSEdit.setText(Double.toString(waterFlow.getMassFlowKgS()));

                fluidVelocityResultTv.setText(operations.setDecimalPlaces(pipeFlow.velocity(), 3));
                pressureDropResultTv.setText(operations.setDecimalPlaces(pipeFlow.pressureDrop(), 2));
                specificHeatCapacityResultTv.setText(operations.setDecimalPlaces(waterCharacteristics.specificHeatCapacity(), 2));
                densityResultTv.setText(operations.setDecimalPlaces(waterCharacteristics.density(), 2));
                viscosityResultTv.setText(Double.toString(waterCharacteristics.viscosity()));
                frictionCoefficientResultTv.setText(operations.setDecimalPlaces(pipeFlow.frictionCoefficient(), 4));
            }else{
                if(!temperatureValidated){
                    returnTemperatureEdit.setError(temperatureValidator.getErrorMessage());
                    supplyTemperatureEdit.setError(temperatureValidator.getErrorMessage());
                }
                if(!outerDiameterIsInRange){
                    setTextAndErrorMessage(pipeValidator.getErrorMessageOuterDiameter(), Double.toString(PipeValidator.OUTER_DIAMETER_MIN_VALUE), outerDiameterEdit);
                }
                if(!pipeValidated){
                    String outerDiameterError = pipeValidator.getErrorMessageOuterDiameter(),
                           wallThicknessError = pipeValidator.getErrorMessageWallThickness(),
                           roughnessError = pipeValidator.getErrorMessageRoughness();

                    if(!operations.isEmpty(outerDiameterError)){ outerDiameterEdit.setError(outerDiameterError); }
                    if(!operations.isEmpty(wallThicknessError)){ wallThicknessEdit.setError(wallThicknessError); }
                    if(!operations.isEmpty(roughnessError)){ roughnessEdit.setError(roughnessError); }
                }
            }
        }
    }
}