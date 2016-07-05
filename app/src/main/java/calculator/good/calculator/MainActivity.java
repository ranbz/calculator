package calculator.good.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public boolean firstOpr=false;
    public String  currStr = "";
    public String currUPto="" ;
    public String lastOP = "";
    public Double currNm , currSum;

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

    public void writeTorzlt()
    {

        TextView msgRsltView = (TextView) findViewById(R.id.rslt_tv);


        Log.d("Tag", String.valueOf(currSum));

        msgRsltView.setText(String.valueOf(currSum));

    }

    public void CLRbtn(View v)
    {
        currStr = "";
        currSum = 0.0;
        lastOP="";
        writeToScr(currStr);
        writeTorzlt();
        firstOpr = false;

    }

    public void currOPbtn(View v)
    {
        Button clickedButton= (Button) v;
        Double newNm;

        String textFromButton1 = clickedButton.getText().toString();

        if (!firstOpr)
        {
            currSum = Double.parseDouble(currUPto);
            lastOP = textFromButton1;
        }
        else
        {

            newNm = Double.parseDouble(currUPto);

            switch (lastOP)
            {
                case "/":
                    currSum = currSum / newNm;

                    break;
                case "X":
                    currSum = currSum * newNm;

                    break;
                case "-":
                    currSum = currSum - newNm;

                    break;
                case "+":
                    currSum = currSum + newNm;

                    break;


            }
            lastOP = textFromButton1;
            writeTorzlt();

        }

        switch (textFromButton1)
        {
            case "/":

                writeToScr("/");
                break;
            case "X":

                writeToScr("X");
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

    public void Ewbtn()
    {
        currStr = "";
        lastOP = "";
        writeToScr(currStr);
        writeTorzlt();
        firstOpr = false;

    }

    public void currNbtn(View v)
    {
        Button clickedButton= (Button) v;

        String textFromButton2= clickedButton.getText().toString();
        currUPto = currUPto + textFromButton2;
        writeToScr(textFromButton2);



    }

}
