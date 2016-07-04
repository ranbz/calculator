package calculator.good.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    boolean firstOpr=false;
    String  currStr="", currUPto="" ;
    Double currNm , currSum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void main()
    {
        currSum = 0.0;
    }



    public void writeToScr(String Nstr)
    {

        TextView msgTextView = (TextView) findViewById(R.id.msg_tv);
        currStr = currStr + Nstr;

        Log.d("Tag", currStr);

        msgTextView.setText(currStr);

    }

    public void writeTorslt(String Nstr)
    {

        TextView msgTextView = (TextView) findViewById(R.id.rslt_tv);
        currStr = currStr + Nstr;

        Log.d("Tag", currStr);

        msgTextView.setText(currStr);

    }

    public void CLRbtn(View v)
    {
        currStr = "";
        currSum = 0.0;
        writeToScr(currStr);
        writeTorslt(currStr);
        firstOpr = false;

    }

    public void currOPbtn(View v)
    {
        Button clickedButton= (Button) v;
        Double newNm;

        String textFromButton = clickedButton.getText().toString();

        if (firstOpr == false)
        {
            currSum = Double.parseDouble(currUPto);
        }
        else
        {
            newNm = Double.parseDouble(currUPto);

            switch (textFromButton)
            {
                case "/":
                    currSum = currSum / newNm;

                    break;
                case "*":
                    currSum = currSum * newNm;

                    break;
                case "-":
                    currSum = currSum - newNm;

                    break;
                case "+":
                    currSum = currSum + newNm;

                    break;


            }

            writeTorslt(String.valueOf(currSum));
        }

        switch (textFromButton)
        {
            case "/":

                writeToScr("/");
                break;
            case "*":

                writeToScr("*");
                break;
            case "-":

                writeToScr("-");
                break;
            case "+":

                writeToScr("+");
                break;


        }
        currUPto = "";
        firstOpr = true;




    }

    public void currNbtn(View v)
    {
        Button clickedButton= (Button) v;

        String textFromButton= clickedButton.getText().toString();
        currUPto = currUPto + textFromButton;
        writeToScr(textFromButton);



    }

}
