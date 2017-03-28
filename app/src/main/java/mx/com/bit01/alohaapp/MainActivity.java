package mx.com.bit01.alohaapp;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private int mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button btnMensaje = (Button) findViewById(R.id.btnAgendar);
        btnMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });

        TextView lblMensaje = (TextView) findViewById(R.id.lblHoraMensaje);
        TextView lblMensajeTxt = (TextView) findViewById(R.id.lblMensajeTxt);
        Switch sw = (Switch) findViewById(R.id.switchAgendar);

        if(getResources().getString(R.string.Aloha).equals(lblMensaje.getText().toString())){
            lblMensaje.setVisibility(View.GONE);
            lblMensajeTxt.setVisibility(View.GONE);
            sw.setVisibility(View.GONE);
        }


        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Snackbar.make(findViewById(R.id.btnAgendar), "Configurada la alarma", Snackbar.LENGTH_LONG).show();
                }else{
                    Snackbar.make(findViewById(R.id.btnAgendar), "Cancelada la alarma", Snackbar.LENGTH_LONG).show();
                }
            }
        });

        Button btnTest = (Button) findViewById(R.id.test);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MensajeDiario.class);
                startActivity(intent);
            }
        });


    }

    public void showTimePicker(){

        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                mHour = hourOfDay;
                mMinute = minute;
                TextView lblMensajeTxt = (TextView) findViewById(R.id.lblMensajeTxt);
                TextView lblHora = (TextView) findViewById(R.id.lblHoraMensaje);
                lblHora.setText(armarHora(mHour,mMinute));
                Switch sw = (Switch) findViewById(R.id.switchAgendar);
                sw.setChecked(true);
                Snackbar.make(findViewById(R.id.btnAgendar), "Configurada la alarma", Snackbar.LENGTH_LONG).show();
                lblHora.setVisibility(View.VISIBLE);
                lblMensajeTxt.setVisibility(View.VISIBLE);
                sw.setVisibility(View.VISIBLE);

            }
        }, mHour,mMinute,false);
        timePickerDialog.show();
    }

    public String armarHora(int hora, int min){

        String hr, minS;

        hr=hora+"";
        minS=min+"";

        if(hora<10){
            hr="0"+hora;
        }

        if(min<10){
            minS="0"+min;
        }

        return hr+":"+minS+" hrs";

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
