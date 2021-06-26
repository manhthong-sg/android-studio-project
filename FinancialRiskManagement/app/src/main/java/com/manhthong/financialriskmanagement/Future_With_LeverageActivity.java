package com.manhthong.financialriskmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Future_With_LeverageActivity extends AppCompatActivity {
    EditText tongVon;
    EditText ruiRo;
    EditText stoploss;
    EditText entry;
    EditText target;
    EditText donBay;
    TextView tv_spot;
    Button btn_Tinh;

    boolean tongVon_flag=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_future_with_leverage);
        tongVon=findViewById(R.id.edt_tongVon);
        ruiRo=findViewById(R.id.edt_ruiRoPercent);
        stoploss=findViewById(R.id.edt_stopLoss);
        entry=findViewById(R.id.edt_entry);
        target=findViewById(R.id.edt_target);
        donBay=findViewById(R.id.edt_leverage);
        tv_spot=findViewById(R.id.tv_Spot);
        btn_Tinh=findViewById(R.id.btn_Tinh);


        tongVon.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(tongVon.getText().toString().isEmpty() || tongVon.getText().toString()==null) {
                    tongVon.setError("Tổng vốn không được để trống");
                }
                tongVon_flag=true;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //set sk cho nút chuyển sang future-đòn bẩy
        tv_spot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentFuture= new Intent(Future_With_LeverageActivity.this, Spot_No_LeverageActivity.class );
                startActivity(intentFuture);
            }
        });

        //set sự kiện cho nút Tính
        btn_Tinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Double TongVon= Double.parseDouble(tongVon.getText().toString());
                Double RuiRo= Double.parseDouble(ruiRo.getText().toString());
                Double Entry= Double.parseDouble(entry.getText().toString());
                Double Stoploss= Double.parseDouble(stoploss.getText().toString());
                Double DonBay=Double.parseDouble(donBay.getText().toString());
                if(checkValuedation()){
                    Double soTienBoRa=TongVon*RuiRo*1/100*Entry/(DonBay*(Entry-Stoploss));

                    Double soCoinCanMua=soTienBoRa/Entry;

                    soCoinCanMua=(double)Math.round(soCoinCanMua*10000)/10000;
                    soTienBoRa=(double)Math.round(soTienBoRa*100)/100;

                    Intent intentResult = new Intent(Future_With_LeverageActivity.this, Result_Future_With_LeverageActivity.class);
                    intentResult.putExtra("tongVon",tongVon.getText().toString());
                    intentResult.putExtra("ruiRo", ruiRo.getText().toString());
                    intentResult.putExtra("stoploss", stoploss.getText().toString());
                    intentResult.putExtra("entry", entry.getText().toString());
                    intentResult.putExtra("target", target.getText().toString());
                    intentResult.putExtra("donBay", donBay.getText().toString());
                    intentResult.putExtra("SoCoiCanMua", soCoinCanMua.toString());
                    intentResult.putExtra("SoTienBoRa", soTienBoRa.toString());

                    startActivity(intentResult);
                }
                else{
                    Toast.makeText(Future_With_LeverageActivity.this, "Please fill up all fields!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private boolean checkValuedation(){
        if (tongVon_flag) {
            return true;
        }
        else {
            if (!tongVon_flag){
                tongVon.setError("Display name is required.");
            }
//            if (!phoneNumber_flag){
//                edt_phoneNumber.setError("Phone number is required.");
//            }
//            if (!password_flag){
//                edt_password.setError("Password is required.");
//            }
//            if (!email_flag){
//                edt_email.setError("Email is required.");
//            }
            return false;
        }
    }
}