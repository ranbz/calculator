package calculator.good.calculator;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    public boolean firstOpr = false , lastPsign , lastPnum , lastEQsign;
    public String currStr = "";
    public String currUPto = "0";
    public String lastOP = "";
    public Double currSum , oldSum;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currSum = 0.0;
        firstOpr = false;

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    public void writeToScr(String Nstr) {           //writeToScr

        TextView msgTextView = (TextView) findViewById(R.id.msg_tv);
        currStr = currStr + Nstr;

        Log.d("Tag", currStr);

        msgTextView.setText(currStr);

    }

    public void writeTorzlt(boolean Cleer) {           //writeTorzlt

        TextView msgRsltView = (TextView) findViewById(R.id.rslt_tv);

        if(Cleer)
        {
            Log.d("writeTorzlt", "Cleer");
            msgRsltView.setText("");
        }
        else
        {
            Log.d("writeTorzlt", String.valueOf(currSum));
            msgRsltView.setText(String.valueOf(currSum));
        }


    }

    public void CLRbtn(View v) {

        currStr = "";
        currSum = 0.0;
        lastOP = "";
        writeToScr(currStr);
        writeTorzlt(true);
        firstOpr = false;
        TogellView(false);
        oldSum = 0.0;
        lastPsign=false;
        lastPnum=false;
        currUPto = "0";
    }

    public void currOPbtn(View v) {         //currOPbtn
        Button clickedButton = (Button) v;
        Double newNm;

        String textFromButton1 = clickedButton.getText().toString();

        if (currUPto.compareTo("") == 0)
        {
            newNm = oldSum;
        }else
        {
            newNm = Double.parseDouble(currUPto);
            oldSum = newNm;
        }

        if (textFromButton1.compareTo("=") == 0) {      // click =

            switch (lastOP) {
                case "/":                       //lastOP   /
                    currSum = currSum / newNm;

                    break;
                case "X":                       //lastOP   x
                    currSum = currSum * newNm;

                    break;
                case "-":                       //lastOP   -
                    currSum = currSum - newNm;

                    break;
                case "+":                       //lastOP   +
                    currSum = currSum + newNm;

                    break;


            }


            writeTorzlt(false);
            currUPto = String.valueOf(currSum);
            currStr = String.valueOf(currSum);
            lastOP = "";
            writeToScr("");
            firstOpr = false;
            lastEQsign=true;
            TogellView(true);


        } else {                    // click = ->end

            lastEQsign=false;
            if (!firstOpr) {        // firstOpr
                currSum = Double.parseDouble(currUPto);
                lastOP = textFromButton1;
            } else {                // firstOpr ->end

                //newNm = Double.parseDouble(currUPto);

                switch (lastOP) {
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
                writeTorzlt(false);

            }

            switch (textFromButton1) {
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
        lastPsign=true;
        lastPnum=false;

    }           //currOPbtn ->end


    public void currNbtn(View v) {          //currNbtn

        String tmpNm;
        Button clickedButton = (Button) v;

        String textFromButton2 = clickedButton.getText().toString();
        if (lastEQsign)
        {
            //tmpNm = currUPto;
            tmpNm = textFromButton2;
            CLRbtn(v);
            currUPto = tmpNm;
            currSum = 0.0;
            lastEQsign=false;
            writeToScr(tmpNm);
        }else
        {
            currUPto = currUPto + textFromButton2;
            writeToScr(textFromButton2);
        }


        TogellView(false);

        lastPsign=false;
        lastPnum=true;
    }

    public void TogellView(boolean ON) {
        TextView tv_r = (TextView) findViewById(R.id.rslt_tv);
        TextView tv_m = (TextView) findViewById(R.id.msg_tv);

        if (ON) {
            TableRow.LayoutParams params_m = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 0, 0f);
            TableRow.LayoutParams params_r = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 0, 2f);
            tv_m.setLayoutParams(params_m);
            tv_r.setLayoutParams(params_r);
        } else {
            TableRow.LayoutParams params_m = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 0, 1f);
            TableRow.LayoutParams params_r = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 0, 1f);
            tv_m.setLayoutParams(params_m);
            tv_r.setLayoutParams(params_r);
        }


    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://calculator.good.calculator/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://calculator.good.calculator/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
