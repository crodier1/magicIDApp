package com.example.magicidapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

//text code w/ 800101 5009 087

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button button;
    TextView textView;
    String ID, DOB,
            p = "<p>", pc = "</P>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.etID);
        button = findViewById(R.id.btn);
        textView = findViewById(R.id.tvResults);

//        OnButtonClicked onButtonClicked = new OnButtonClicked();
//        button.setOnClickListener(onButtonClicked);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ID = editText.getText().toString();
                boolean verification = validateID(ID);


                if(verification){
                    editText.setText("");
                    textView.setText("");

                    textView.append(Html.fromHtml(p +"ID Valid"+pc));

                    DOB = p + "DOB " + ID.substring(2,4) +"/"+ ID.substring(4,6) +"/"+ ID.substring(0,2) + pc;

                    textView.append(Html.fromHtml(DOB));

                    Log.d("gender code ",ID.substring(6,10));

                    int genderCode = Integer.parseInt(ID.substring(6,10));

                    if(genderCode <= 4999){
                        textView.append(Html.fromHtml(p + "Gender: Female" + pc));
                    } else {
                        textView.append(Html.fromHtml(p + "Gender: Male" + pc));
                    }

                    if(ID.substring(10,11).equals("0")){
                        textView.append(Html.fromHtml(p + "SA Citizen" + pc));
                    }

                    if(ID.substring(10,11).equals("1")){
                        textView.append(Html.fromHtml(p + "Permanent Resident" + pc));
                    }

                } else {
                    showToast("ID Number Incorrect");
                }



            }
        });

    }

    private void showToast(String text){
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_LONG).show();
    }

   private boolean validateID(String ID){
        boolean result = false;
        if(ID.length() != 13){
            showToast("ID length must be 13");
            return result;
        }
        char[] IDArr = ID.toCharArray();

        int oddsTotal = 0;

        for(int i=0; i<IDArr.length-2; i+=2){

            oddsTotal += Character.getNumericValue(IDArr[i]);

        }

       Log.d("odds ", String.valueOf(oddsTotal));

        String evensTotalStr = "";

       for(int i=1; i<IDArr.length; i+=2){
           evensTotalStr += IDArr[i];
           //Log.d("evens ", Character.toString(IDArr[i]));
       }

       int evensDoubleInt = Integer.parseInt(evensTotalStr) * 2;
      // Log.d("evensTotalInt ", String.valueOf(evensTotalInt));

       char[] evensDoubleStr = String.valueOf(evensDoubleInt).toCharArray();

       int evensInt = 0;

       for(int i=0; i<evensDoubleStr.length; i++){
           evensInt += Character.getNumericValue(evensDoubleStr[i]);
       }

       Log.d("evens total ", String.valueOf(evensInt));


        int test = oddsTotal + evensInt;
       Log.d("odds+evens ", String.valueOf(test));

      char[] oddsPlusEvens =  Integer.toString(oddsTotal + evensInt).toCharArray();

//      Log.d("oddsPlusEvens ", Character.toString(oddsPlusEvens[1]));
//       Log.d("10 - last num", String.valueOf(10 - Character.getNumericValue(oddsPlusEvens[1])));
//       Log.d("last num ", ""+Character.getNumericValue(IDArr[IDArr.length-1]));

       if(10 - Character.getNumericValue(oddsPlusEvens[1]) == Character.getNumericValue(IDArr[IDArr.length-1])){
           result = true;
       }

       return result;

   }


}



