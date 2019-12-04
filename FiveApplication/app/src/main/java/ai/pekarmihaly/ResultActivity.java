package ai.pekarmihaly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity implements  View.OnClickListener{

    Button newButton;
    TextView label;
    TextView result;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        label = findViewById(R.id.resultLabelTextViewResult);
        result = findViewById(R.id.textViewResult);
        newButton = findViewById(R.id.newButtonResult);

        intent = getIntent();
        String action = intent.getStringExtra("action");
        Operation(action);
        newButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.newButtonResult){
            intent.putExtra("result",result.getText().toString());
            setResult(RESULT_OK,intent);
            finish();
        }
    }
    private void Operation(String Action ){
        switch (Action){
            case "ADD":{
                label.setText("The result of the addition: ");
                double a = intent.getDoubleExtra("a",-1);
                double b = intent.getDoubleExtra("b",-1);
                result.setText(String.valueOf(a+b));
                break;
            }
            case "MUL":{
                label.setText("The result of the multiplication: ");
                double a = intent.getDoubleExtra("a",-1);
                double b = intent.getDoubleExtra("b",-1);
                result.setText(String.valueOf(a*b));
                break;
            }
            case "DIV":{
                label.setText("The result of the divison: ");
                double a = intent.getDoubleExtra("a",-1);
                double b = intent.getDoubleExtra("b",-1);
                result.setText(String.valueOf(a/b));
                break;
            }
            default: {
                break;
            }
        }
    }
}
