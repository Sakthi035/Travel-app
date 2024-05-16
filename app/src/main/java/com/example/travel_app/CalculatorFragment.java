package com.example.travel_app;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.text.DecimalFormat;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalculatorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalculatorFragment extends Fragment {

    Activity context;

    private static final char add = '+';
    private static final char sub = '-';
    private static final char prod = '*';
    private static final char div = '/';
    private static final char mod = '%';
    private char curSymbol = '0';
    private double firstVal = Double.NaN;
    private double secondVal;
    private TextView inDisp, outDisp;
    private DecimalFormat decForm;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        context = getActivity();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);
        return view;
    }

    public void onStart() {

        super.onStart();

        decForm = new DecimalFormat("#.#####");
        inDisp = context.findViewById(R.id.input);
        outDisp = context.findViewById(R.id.output);

        MaterialButton button0 = context.findViewById(R.id.zero);
        MaterialButton button1 = context.findViewById(R.id.one);
        MaterialButton button2 = context.findViewById(R.id.two);
        MaterialButton button3 = context.findViewById(R.id.three);
        MaterialButton button4 = context.findViewById(R.id.four);
        MaterialButton button5 = context.findViewById(R.id.five);
        MaterialButton button6 = context.findViewById(R.id.six);
        MaterialButton button7 = context.findViewById(R.id.seven);
        MaterialButton button8 = context.findViewById(R.id.eight);
        MaterialButton button9 = context.findViewById(R.id.nine);
        MaterialButton buttonDot = context.findViewById(R.id.dot);
        MaterialButton buttonAdd = context.findViewById(R.id.sum);
        MaterialButton buttonSub = context.findViewById(R.id.diff);
        MaterialButton buttonProd = context.findViewById(R.id.product);
        MaterialButton buttonDiv = context.findViewById(R.id.divide);
        MaterialButton buttonEqual = context.findViewById(R.id.result);
        MaterialButton buttonClear = context.findViewById(R.id.clear);
        MaterialButton buttonRem = context.findViewById(R.id.rem);

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inDisp.setText(inDisp.getText() + "0");
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inDisp.setText(inDisp.getText() + "1");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inDisp.setText(inDisp.getText() + "2");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inDisp.setText(inDisp.getText() + "3");
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inDisp.setText(inDisp.getText() + "4");
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inDisp.setText(inDisp.getText() + "5");
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inDisp.setText(inDisp.getText() + "6");
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inDisp.setText(inDisp.getText() + "7");
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inDisp.setText(inDisp.getText() + "8");
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inDisp.setText(inDisp.getText() + "9");
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (curSymbol != '0') {
                } else {
                    firstVal = Double.parseDouble(inDisp.getText().toString());
                    CalcOperations();
                    curSymbol = add;
                    outDisp.setText(decForm.format(firstVal) + "+");
                    inDisp.setText(null);
                }
            }
        });

        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (curSymbol != '0') {
                } else {
                    firstVal = Double.parseDouble(inDisp.getText().toString());
                    CalcOperations();
                    curSymbol = sub;
                    outDisp.setText(decForm.format(firstVal) + "-");
                    inDisp.setText(null);
                }
            }
        });

        buttonProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (curSymbol != '0') {
                } else {
                    firstVal = Double.parseDouble(inDisp.getText().toString());
                    CalcOperations();
                    curSymbol = prod;
                    outDisp.setText(decForm.format(firstVal) + "*");
                    inDisp.setText(null);
                }
            }
        });

        buttonDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (curSymbol != '0') {
                } else {
                    firstVal = Double.parseDouble(inDisp.getText().toString());
                    CalcOperations();
                    curSymbol = div;
                    outDisp.setText(decForm.format(firstVal) + "/");
                    inDisp.setText(null);
                }
            }
        });

        buttonRem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (curSymbol != '0') {
                } else {
                    firstVal = Double.parseDouble(inDisp.getText().toString());
                    CalcOperations();
                    curSymbol = mod;
                    outDisp.setText(decForm.format(firstVal) + "%");
                    inDisp.setText(null);
                }
            }
        });

        buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inDisp.getText().toString().matches("[0-9]*\\.[0-9]*")){
                } else if (inDisp.getText().toString().equals("")) {
                    inDisp.setText("0.");
                } else{
                    inDisp.setText(inDisp.getText() + ".");
                }
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (curSymbol != '0'){
                    CharSequence curText = outDisp.getText();
                    inDisp.setText(curText.subSequence(0,curText.length() - 1));
                    outDisp.setText(curText.subSequence(0,curText.length() - 1));
                    curSymbol = '0';
                } else if (inDisp.getText().length()>0){
                    CharSequence curText = inDisp.getText();
                    inDisp.setText(curText.subSequence(0,curText.length() - 1));
                } else {
                    firstVal = Double.NaN;
                    secondVal = Double.NaN;
                    inDisp.setText("");
                    outDisp.setText("");
                }
            }
        });

        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inDisp.getText().toString().equals("")) {
                }
                else if (!inDisp.getText().toString().equals("") && curSymbol == '0'){
                    firstVal = Double.parseDouble(inDisp.getText().toString());
                    outDisp.setText(decForm.format(firstVal));
                    //inDisp.setText(decForm.format(firstVal));
                }else{
                    CalcOperations();
                    outDisp.setText(decForm.format(firstVal));
                    inDisp.setText(decForm.format(firstVal));
                    /*firstVal = Double.parseDouble(outDisp.getText().toString());*/
                    curSymbol = '0';
                }
            }
        });
    }
    private void CalcOperations(){
        if (!Double.isNaN(firstVal)){
            secondVal = Double.parseDouble(inDisp.getText().toString());
            inDisp.setText(null);

            switch (curSymbol) {
                case add:
                    firstVal = this.firstVal + secondVal;
                    break;

                case sub:
                    firstVal = this.firstVal - secondVal;
                    break;

                case prod:
                    firstVal = this.firstVal * secondVal;
                    break;

                case div:
                    if (secondVal == 0)
                        break;
                    firstVal = this.firstVal / secondVal;
                    break;

                case mod:
                    if (secondVal == 0)
                        break;
                    firstVal = this.firstVal % secondVal;
                    break;
            }
        } else {
            try{
                firstVal = Double.parseDouble(inDisp.getText().toString());
            } catch (Exception e){

            }
        }


    }
}