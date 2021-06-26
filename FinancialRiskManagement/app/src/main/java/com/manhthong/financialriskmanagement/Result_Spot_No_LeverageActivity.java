package com.manhthong.financialriskmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result_Spot_No_LeverageActivity extends AppCompatActivity {
    TextView tv_Von;
    TextView tv_ruiRo;
    TextView tv_giaDT;
    TextView tv_soCoin;
    TextView tv_entry;
    Button btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result__spot__no__leverage);

        tv_Von=findViewById(R.id.txtVon);
        tv_ruiRo=findViewById(R.id.txtRuiRo);
        tv_giaDT=findViewById(R.id.txtGiaDauTu);
        tv_soCoin=findViewById(R.id.txtSoCoin);
        tv_entry=findViewById(R.id.txtEntry);
        btn_back=findViewById(R.id.btn_back);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent=getIntent();
        String von=intent.getStringExtra("tongVon")+"$";
        tv_Von.setText(von);

        String ruiRo=intent.getStringExtra("ruiRo")+"%";
        tv_ruiRo.setText(ruiRo);

        String soTienBoRa=intent.getStringExtra("SoTienBoRa")+"$";
        tv_giaDT.setText(soTienBoRa);

        String soCoinCanMua=intent.getStringExtra("SoCoiCanMua")+ " Coin";
        tv_soCoin.setText(soCoinCanMua);

        String entry=intent.getStringExtra("entry")+"$";
        tv_entry.setText(entry);
    }
}