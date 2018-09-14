package com.example.alunofatec_local.qualocombustvel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private static NumberFormat currencyFormat =
            NumberFormat.getCurrencyInstance();

    private TextView texto_preco_gasolina;
    private TextView texto_preco_alcool;
    private TextView texto_resultado;
    private ImageView image_resultado;

    private double resultado;
    private double preco_gasolina =  0;
    private double preco_alcool =  0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image_resultado = (ImageView) findViewById(R.id.image_resultado);
        texto_preco_gasolina = (TextView) findViewById(R.id.texto_preco_gasolina);
        texto_preco_alcool = (TextView) findViewById(R.id.texto_preco_alcool);
        texto_resultado = (TextView) findViewById(R.id.texto_resultado);

        texto_preco_gasolina.setText(currencyFormat.format(0));
        texto_preco_alcool.setText(currencyFormat.format(0));

        SeekBar seekBar_gasolina = (SeekBar) findViewById(R.id.seekBar_gasolina);
        SeekBar seekBar_alcool = (SeekBar) findViewById(R.id.seekBar_alcool);

        seekBar_gasolina.setOnSeekBarChangeListener(new ObservadorDaSeekBar());
        seekBar_alcool.setOnSeekBarChangeListener(new ObservadorDaSeekBar());
    }

    private class ObservadorDaSeekBar implements SeekBar.OnSeekBarChangeListener{

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (seekBar.getId() == R.id.seekBar_alcool) {
                texto_preco_alcool.setText(currencyFormat.format(progress));
                preco_alcool = progress / 100.0;
                texto_preco_alcool.setText(currencyFormat.format(preco_alcool));
            }
            if (seekBar.getId() == R.id.seekBar_gasolina) {
                texto_preco_gasolina.setText(currencyFormat.format(progress));
                preco_gasolina = progress/100.0;
                texto_preco_gasolina.setText(currencyFormat.format(preco_gasolina));
            }
            if (preco_alcool == 0.0 && preco_gasolina == 0.0) {
                texto_resultado.setText(getString(R.string.texto_resultado));
                image_resultado.setImageResource(R.drawable.nenhum);
            } else if ((preco_alcool / preco_gasolina) >= 0.7) {
                texto_resultado.setText(getString(R.string.combustivel_gasolina));
                image_resultado.setImageResource(R.drawable.gasolina);
            }  else {
                texto_resultado.setText(getString(R.string.combustivel_alcool));
                image_resultado.setImageResource(R.drawable.etanol);
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }
}
