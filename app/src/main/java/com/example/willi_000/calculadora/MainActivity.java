package com.example.willi_000.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private int tamanhoTexto;
    private EditText txt_visor;
    private String aux;
    private Button[] btn_numericos = new Button[10];
    private Button[] btn_operacoes = new Button[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_visor = findViewById(R.id.txt_visor);

        btn_numericos[0] = findViewById(R.id.bt_0);
        btn_numericos[1] = findViewById(R.id.bt_1);
        btn_numericos[2] = findViewById(R.id.bt_2);
        btn_numericos[3] = findViewById(R.id.bt_3);
        btn_numericos[4] = findViewById(R.id.bt_4);
        btn_numericos[5] = findViewById(R.id.bt_5);
        btn_numericos[6] = findViewById(R.id.bt_6);
        btn_numericos[7] = findViewById(R.id.bt_7);
        btn_numericos[8] = findViewById(R.id.bt_8);
        btn_numericos[9] = findViewById(R.id.bt_9);

        btn_operacoes[0] = findViewById(R.id.bt_soma);
        btn_operacoes[1] = findViewById(R.id.bt_subt);
        btn_operacoes[2] = findViewById(R.id.bt_multi);
        btn_operacoes[3] = findViewById(R.id.bt_divi);
        btn_operacoes[4] = findViewById(R.id.bt_ponto);
        btn_operacoes[5] = findViewById(R.id.bt_igual);
        btn_operacoes[6] = findViewById(R.id.bt_limpar);
        btn_operacoes[7] = findViewById(R.id.bt_maisMenos);
        btn_operacoes[8] = findViewById(R.id.bt_porcent);

        iniciandoBtns();

        btn_operacoes[6].setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                txt_visor.setText("");
                return false;
            }
        });
    }

    private void iniciandoBtns() {

        for (int i = 0; i < 10; i++) {
            btn_numericos[i].setOnClickListener(this);
        }
        for (int i = 0; i < 9; i++) {
            btn_operacoes[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.bt_0:
                teclado("0");
                break;
            case R.id.bt_1:
                teclado("1");
                break;
            case R.id.bt_2:
                teclado("2");
                break;
            case R.id.bt_3:
                teclado("3");
                break;
            case R.id.bt_4:
                teclado("4");
                break;
            case R.id.bt_5:
                teclado("5");
                break;
            case R.id.bt_6:
                teclado("6");
                break;
            case R.id.bt_7:
                teclado("7");
                break;
            case R.id.bt_8:
                teclado("8");
                break;
            case R.id.bt_9:
                teclado("9");
                break;
            case R.id.bt_limpar:
                limparVisor();
                break;
            case R.id.bt_igual:
                calcular();
                break;
            case R.id.bt_ponto:
                ponto(".");
                break;
            case R.id.bt_maisMenos:
                maisOUmenos();
                break;
            case R.id.bt_porcent:
                porcento();
                break;
            case R.id.bt_soma:
                teclado("+");
                break;
            case R.id.bt_subt:
                teclado("-");
                break;
            case R.id.bt_multi:
                teclado("x");
                break;
            case R.id.bt_divi:
                teclado("/");
                break;
        }
    }

    private void teclado(String valor) {
        if(txt_visor.getText().toString().equals("0")) {
            txt_visor.setText("");
            String textvisor = txt_visor.getText().toString();
            aux = textvisor + valor ;
            txt_visor.setText(aux);
        }else {
            String textvisor = txt_visor.getText().toString();
            aux = textvisor + valor;
            txt_visor.setText(aux);
        }
    }

    private void porcento() {
        String textVisor = txt_visor.getText().toString().trim();
        double resultado = Double.parseDouble(textVisor) / 100;
        if (resultado == Math.rint(resultado)) {
            int valorInt = (int) resultado;
            txt_visor.setText(String.valueOf(valorInt));
        } else {
            txt_visor.setText(String.valueOf(resultado));
        }
    }

    private void ponto(String ponto){
        String textvisor = txt_visor.getText().toString();
        txt_visor.setText(textvisor + ponto);
    }

    private void limparVisor() {
        String textVisor = txt_visor.getText().toString();
        String visor;
        tamanhoTexto = txt_visor.length();
        if(tamanhoTexto == 0) {
            txt_visor.setText("");
        } else{
            visor = textVisor.substring(0, tamanhoTexto - 1);
            txt_visor.setText(visor);
        }
    }

    private void maisOUmenos(){
        String textVisor = txt_visor.getText().toString();
        double valor = Double.parseDouble(textVisor) * (-1);

        if (valor == Math.rint(valor)) {
            int valorInt = (int) valor;
            txt_visor.setText(String.valueOf(valorInt));
        } else {
            txt_visor.setText(String.valueOf(valor));
        }
    }

    private void calcular() {
        String textVisor = txt_visor.getText().toString().trim();
        String numeroString = "";
        List<Double> numeros = new ArrayList<>();
        List<Character> operadores = new ArrayList<>();
        tamanhoTexto = textVisor.length();
        double resultado = 0;

        for (int i = 0; i < tamanhoTexto; i++) {
            if (isOperador(textVisor.charAt(i)) == false) {
                numeroString = numeroString.concat(String.valueOf(textVisor.charAt(i)));
            } else if (isOperador(textVisor.charAt(i))) {
                numeros.add(Double.parseDouble(numeroString.trim()));
                operadores.add(textVisor.charAt(i));
                numeroString = "";
            }

            if (i + 1 == textVisor.length()) {
                numeros.add(Double.parseDouble(numeroString.trim()));
            }
        }

        for (int n = 0; n < numeros.size(); n++) {
            if (n == 0) {
                resultado = numeros.get(n);
            } else {
                if (operadores.get(n - 1) == '/') {
                    resultado = resultado / numeros.get(n);
                } else if (operadores.get(n - 1) == 'x') {
                    resultado = resultado * numeros.get(n);
                } else if (operadores.get(n - 1) == '+') {
                    resultado = resultado + numeros.get(n);
                } else if (operadores.get(n - 1) == '-') {
                    resultado = resultado - numeros.get(n);
                }
            }
        }

        if (resultado == Math.rint(resultado)) {
            int resultadoInt = (int) resultado;
            txt_visor.setText(String.valueOf(resultadoInt));
        } else {
            txt_visor.setText(String.valueOf(resultado));
        }
    }

    private boolean isOperador(char c) {
        switch (c){
            case '+':
            case '-':
            case 'x':
            case '/': return true;
            default: return false;
        }
    }

}
