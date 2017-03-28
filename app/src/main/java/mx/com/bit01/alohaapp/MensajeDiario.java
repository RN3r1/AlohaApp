package mx.com.bit01.alohaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MensajeDiario extends AppCompatActivity implements Animation.AnimationListener {


    String[] mensajes;
    private Animation animation1;
    private Animation animation2;
    private boolean isBackOfCardShowing = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensaje_diario);

        mensajes = getResources().getStringArray(R.array.arregloMensajes);

        animation1 = AnimationUtils.loadAnimation(this, R.anim.to_middle);

        animation1.setAnimationListener(this);

        animation2 = AnimationUtils.loadAnimation(this, R.anim.from_middle);

        animation2.setAnimationListener(this);

        ImageView tarjeta = (ImageView) findViewById(R.id.tarjetaImg);
        tarjeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!isBackOfCardShowing){
                    Intent intent = new Intent(v.getContext(), MainActivity.class);
                    startActivity(intent);
                }else{

                    findViewById(R.id.activity_mensaje_diario).clearAnimation();

                    findViewById(R.id.activity_mensaje_diario).setAnimation(animation1);

                    findViewById(R.id.activity_mensaje_diario).startAnimation(animation1);

                }

            }
        });

    }

    @Override
    public void onAnimationStart(Animation animation) {



    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if (animation==animation1) {

            if (isBackOfCardShowing) {

                ((ImageView)findViewById(R.id.tarjetaImg)).setImageResource(R.drawable.card_front2);
                ((TextView)findViewById(R.id.lblMensaje)).setText(mensajes[0]);

            }

            findViewById(R.id.activity_mensaje_diario).clearAnimation();
            findViewById(R.id.activity_mensaje_diario).setAnimation(animation2);
            findViewById(R.id.activity_mensaje_diario).startAnimation(animation2);

        } else {

            isBackOfCardShowing=!isBackOfCardShowing;

        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
