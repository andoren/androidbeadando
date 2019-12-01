package com.example.pekarbeadnado;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.view.*;
import android.widget.EditText;
import android.widget.Toast;
import static java.lang.Double.parseDouble;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button addButton;
    Button mulButton;
    Button divButton;
    Button dialButton;
    EditText aInput;
    EditText bInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addButton = findViewById(R.id.buttonAddMain);
        mulButton = findViewById(R.id.buttonMulMain);
        divButton = findViewById(R.id.buttonDivMain);
        dialButton = findViewById(R.id.buttonDialMain);
        addButton.setOnClickListener(this);
        mulButton.setOnClickListener(this);
        divButton.setOnClickListener(this);
        dialButton.setOnClickListener(this);
        aInput = findViewById(R.id.aInputValueMain);
        bInput = findViewById(R.id.bInputValueMain);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 404 && resultCode == RESULT_OK){
            aInput.setText(data.getStringExtra("result"));
        }
    }


    @Override
    public void onClick(View v) {
        if( v.getId() != R.id.buttonDialMain && wrongInput() ){
            return;
        }
        switch (v.getId()){
            case R.id.buttonAddMain:{
                startActivityForResult(makeIntent("ADD"),404);
                break;
            }
            case R.id.buttonDivMain:{

                startActivityForResult(makeIntent("DIV"),404);
                break;
            }
            case R.id.buttonMulMain:{

                startActivityForResult(makeIntent("MUL"),404);
                break;
            }
            case R.id.buttonDialMain:{
                startActivity(makeIntent("DIAL"));
                break;
            }
        }
    }

    private boolean wrongInput() {
        boolean wrong = false;
        String a = aInput.getText().toString();
        String b = bInput.getText().toString();
        if((a== null || b == null)||(a.equals("") || b.equals(""))){
            Toast.makeText(this,"Empty input field(s)",Toast.LENGTH_SHORT).show();
            wrong = true;
        }else {
            try{
                double temp = parseDouble(a);
                double temp2 = parseDouble(b);
            }catch (NumberFormatException e){
                Toast.makeText(this,"Invalid number",Toast.LENGTH_SHORT).show();
                wrong = true;
            }
        }
        return wrong;
    }
    private Intent makeIntent(String Action){
        Intent intent;
        if(Action.equals("DIAL")){
            intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + bInput.getText().toString()));
        }
        else {
            intent = new Intent(this, ResultActivity.class);
            intent.putExtra("a", parseDouble(aInput.getText().toString()));
            intent.putExtra("b", parseDouble(bInput.getText().toString()));
            intent.putExtra("action", Action);
        }
        return intent;
    }
}
