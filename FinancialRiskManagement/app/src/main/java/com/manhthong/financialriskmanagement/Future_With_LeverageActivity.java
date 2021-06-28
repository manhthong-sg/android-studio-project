package com.manhthong.financialriskmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.annotation.Target;

public class Future_With_LeverageActivity extends AppCompatActivity {
    EditText tongVon;
    EditText ruiRo;
    EditText stoploss;
    EditText entry;
    EditText target;
    EditText donBay;
    TextView tv_spot;
    Switch sw_short;
    Button btn_Tinh;

    boolean tongVon_flag=false;
    Integer long_short=1;
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
        sw_short=findViewById(R.id.sw_short);
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
        sw_short.setChecked(false);
        //set sự kiện cho switch short
        sw_short.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    long_short=0;
                    btn_Tinh.setSelected(true);
                    btn_Tinh.setText("Tính lệnh SHORT");
                }
                else {
                    long_short=1;
                    btn_Tinh.setSelected(false);
                    btn_Tinh.setText("Tính lệnh LONG");
                }
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
                Double Target=Double.parseDouble(target.getText().toString());

                if(long_short==1){
                    Double soTienBoRa=TongVon*RuiRo*1/100*Entry/(DonBay*(Entry-Stoploss));

                    Double soCoinCanMua=soTienBoRa/Entry;

                    Double LoiNhuan=DonBay*(Target/Entry-1)*100;

                    Double tiLeRR=(soTienBoRa*LoiNhuan/100)/(TongVon*RuiRo/100);

                    soCoinCanMua=(double)Math.round(soCoinCanMua*10000)/10000;
                    soTienBoRa=(double)Math.round(soTienBoRa*100)/100;
                    LoiNhuan=(double)Math.round(LoiNhuan*100)/100;
                    tiLeRR=(double)Math.round(tiLeRR*100)/100;

                    Intent intentResult = new Intent(Future_With_LeverageActivity.this, Result_Future_With_LeverageActivity.class);
                    intentResult.putExtra("tongVon",tongVon.getText().toString());
                    intentResult.putExtra("ruiRo", ruiRo.getText().toString());
                    intentResult.putExtra("stoploss", stoploss.getText().toString());
                    intentResult.putExtra("entry", entry.getText().toString());
                    intentResult.putExtra("target", target.getText().toString());
                    intentResult.putExtra("donBay", donBay.getText().toString());
                    intentResult.putExtra("SoCoiCanMua", soCoinCanMua.toString());
                    intentResult.putExtra("SoTienBoRa", soTienBoRa.toString());
                    intentResult.putExtra("LoiNhuan", LoiNhuan.toString());
                    intentResult.putExtra("tiLeRR", tiLeRR.toString());

                    startActivity(intentResult);
                }
                if(long_short==0){
                    //Toast.makeText(Future_With_LeverageActivity.this, "Bạn muốn SHORT?", Toast.LENGTH_SHORT).show();
                    Double soTienBoRa=TongVon*RuiRo*1/100*Entry/(DonBay*(Stoploss-Entry));

                    Double soCoinCanMua=soTienBoRa/Entry;

                    Double LoiNhuan=DonBay*(1-Target/Entry)*100;

                    Double tiLeRR=(soTienBoRa*LoiNhuan/100)/(TongVon*RuiRo/100);

                    soCoinCanMua=(double)Math.round(soCoinCanMua*10000)/10000;
                    soTienBoRa=(double)Math.round(soTienBoRa*100)/100;
                    LoiNhuan=(double)Math.round(LoiNhuan*100)/100;
                    tiLeRR=(double)Math.round(tiLeRR*100)/100;

                    Intent intentResult = new Intent(Future_With_LeverageActivity.this, Result_Future_With_LeverageActivity.class);
                    intentResult.putExtra("tongVon",tongVon.getText().toString());
                    intentResult.putExtra("ruiRo", ruiRo.getText().toString());
                    intentResult.putExtra("stoploss", stoploss.getText().toString());
                    intentResult.putExtra("entry", entry.getText().toString());
                    intentResult.putExtra("target", target.getText().toString());
                    intentResult.putExtra("donBay", donBay.getText().toString());
                    intentResult.putExtra("SoCoiCanMua", soCoinCanMua.toString());
                    intentResult.putExtra("SoTienBoRa", soTienBoRa.toString());
                    intentResult.putExtra("LoiNhuan", LoiNhuan.toString());
                    intentResult.putExtra("tiLeRR", tiLeRR.toString());

                    startActivity(intentResult);
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