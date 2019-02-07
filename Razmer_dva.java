package kz.mazur.shkaf_pro;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Razmer_dva extends Activity {
    SharedPreferences aPer;
    private static final String PORADOK="0";
    private static final String VISOTA = "800";
    private static final String SHIRINA = "600";
    private static final String GLUBINA = "450";
    private static final String COKOL = "100";
    private static final String POLKA_1="2";
    private static final String POLKA_2="1";
    private static final String VISOTA_FACADA="200";
    private static final String V_ANTRES="500";
   EditText haa,aaa,baa,caa,daa,polkaa,polkab,v_fa;
   ImageView karty;
   TextView pol,t_aaa,textView26;
   LinearLayout wera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.razmer_dva);
        aPer=getSharedPreferences("aPer",MODE_PRIVATE);
        haa=(EditText)findViewById(R.id.haa);
        aaa=(EditText)findViewById(R.id.aaa);
        baa=(EditText)findViewById(R.id.baa);
        caa=(EditText)findViewById(R.id.caa);
        daa=(EditText)findViewById(R.id.daa);
        polkaa=(EditText)findViewById(R.id.polkaa);
        polkab=(EditText)findViewById(R.id.polkab);
        karty=(ImageView)findViewById(R.id.karty) ;
        pol=(TextView)findViewById(R.id.pol);
        textView26=(TextView)findViewById(R.id.textView26);
        t_aaa=(TextView)findViewById(R.id.t_aaa);
        v_fa=(EditText)findViewById(R.id.v_fa);
        wera=(LinearLayout)findViewById(R.id.wera);
        staroe();
    }

    private void staroe() {
        int pora=aPer.getInt(PORADOK,0);
        haa.setText(aPer.getString(VISOTA,"800"));
        aaa.setText(aPer.getString(V_ANTRES,"500"));
        baa.setText(aPer.getString(SHIRINA,"600"));
        caa.setText(aPer.getString(COKOL,"100"));
        daa.setText(aPer.getString(GLUBINA,"450"));
        polkaa.setText(aPer.getString(POLKA_1,"2"));
        polkab.setText(aPer.getString(POLKA_2,"1"));
        v_fa.setText(aPer.getString(VISOTA_FACADA,"200"));
        if (pora==16){karty.setImageResource(R.drawable.sh1);}
        else {
            karty.setImageResource(R.drawable.sh2);
            if (pora == 19 || pora==17) {
                polkab.setVisibility(View.VISIBLE);
                pol.setVisibility(View.VISIBLE);
            }
            else {
                karty.setImageResource(R.drawable.kuh14d);
                aaa.setVisibility(View.GONE);
                t_aaa.setVisibility(View.GONE);
                if (pora==21 || pora==22){
                    wera.setVisibility(View.VISIBLE);
                }
                if (pora==23){polkaa.setVisibility(View.GONE);
                    textView26.setVisibility(View.GONE);}

            }
        }
        if (pora==26){karty.setImageResource(R.drawable.stol_tumba_cherteg);}
    }

    public void rewer(View view) {
        SharedPreferences.Editor edi=aPer.edit();
        if (TextUtils.isEmpty(haa.getText().toString())){}
        else {edi.putString(VISOTA, haa.getText().toString()); };
        if (TextUtils.isEmpty(baa.getText().toString())){}
        else{edi.putString(SHIRINA, baa.getText().toString());}
        if (TextUtils.isEmpty(daa.getText().toString())){}
        else{ edi.putString(GLUBINA, daa.getText().toString());}
        if (TextUtils.isEmpty(caa.getText().toString())){}
        else{edi.putString(COKOL, caa.getText().toString());}
        if (TextUtils.isEmpty(polkaa.getText().toString())){}
        else{edi.putString(POLKA_1, polkaa.getText().toString());}
        if (TextUtils.isEmpty(polkab.getText().toString())){}
        else{edi.putString(POLKA_2, polkab.getText().toString());}
        if (TextUtils.isEmpty(aaa.getText().toString())){}
        else{edi.putString(V_ANTRES, aaa.getText().toString());}
        if (TextUtils.isEmpty(v_fa.getText().toString())){}
        else{edi.putString(VISOTA_FACADA, v_fa.getText().toString());}
        edi.commit();
        finish();
    }
}
