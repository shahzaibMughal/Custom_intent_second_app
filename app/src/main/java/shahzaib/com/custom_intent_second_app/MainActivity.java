package shahzaib.com.custom_intent_second_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {


    private static final int ADD_NUMBERS_REQUEST_CODE = 10 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == ADD_NUMBERS_REQUEST_CODE)
        {
            int resultReceived = data.getIntExtra("Result",0);

            TextView resultTV = findViewById(R.id.resultTV);
            resultTV.setText(String.valueOf(resultReceived));
        }

    }

    public void addNumbers(View view) {
        //TODO: add numbers in other app(Custom intent receiver app) through implicit intent

        TextView firstNumberET, secondNumberET;
        firstNumberET = findViewById(R.id.fistNumberET);
        secondNumberET = findViewById(R.id.secondNumberET);

        if(firstNumberET.getText().toString().length()>0 && secondNumberET.getText().toString().length()>0)
        {
            int firstNumber = Integer.parseInt(firstNumberET.getText().toString());
            int secondNumber = Integer.parseInt(secondNumberET.getText().toString());

            //ACTION_ADD_TWO_NUMBERS
            String CUSTOM_ACTION = "ACTION_ADD_NUMBERS";
            Intent intent = new Intent();
            intent.setAction(CUSTOM_ACTION);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.putExtra("FirstNumber",firstNumber);
            intent.putExtra("SecondNumber",secondNumber);

            if(intent.resolveActivity(getPackageManager())!=null)
            {
                startActivityForResult(Intent.createChooser(intent,"Choose activity to perform calculation"),ADD_NUMBERS_REQUEST_CODE);
            }
            else
            {
                Toast.makeText(this, "No activity can perform this action", Toast.LENGTH_SHORT).show();
            }


        }
        else
        {
            Toast.makeText(this, "First or Second Number is missing", Toast.LENGTH_SHORT).show();
        }



    }
}
