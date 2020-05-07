package br.com.dlweb.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText numero1;
    EditText numero2;
    TextView resultado;
    ListView listaResultados;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numero1 = findViewById(R.id.etNum1);
        numero2 = findViewById(R.id.etNum2);
        resultado = findViewById(R.id.tvRes);
        listaResultados = findViewById(R.id.lvResultados);

        criarBancoDados();
    }

    public void adicaoResultado (View v) {
        resultado.setText("");
        String num1St = numero1.getText().toString().trim();
        String num2St = numero2.getText().toString().trim();

        if (num1St.equals("") || num2St.equals("")) {
            Toast.makeText(this, "Por favor, informar os dois números!", Toast.LENGTH_LONG).show();
        } else {
            float res = Float.valueOf(num1St) + Float.valueOf(num2St);
            resultado.setText("Resultado: " + res);
            gravaResultado("+", num1St, num2St, res);
        }
    }

    public void subtracaoResultado (View v) {
        resultado.setText("");
        String num1St = numero1.getText().toString().trim();
        String num2St = numero2.getText().toString().trim();

        if (num1St.equals("") || num2St.equals("")) {
            Toast.makeText(this, "Por favor, informar os dois números!", Toast.LENGTH_LONG).show();
        } else {
            float res = Float.valueOf(num1St) - Float.valueOf(num2St);
            resultado.setText("Resultado: " + res);
            gravaResultado("-", num1St, num2St, res);
        }
    }

    public void multiplicacaoResultado (View v) {
        resultado.setText("");
        String num1St = numero1.getText().toString().trim();
        String num2St = numero2.getText().toString().trim();

        if (num1St.equals("") || num2St.equals("")) {
            Toast.makeText(this, "Por favor, informar os dois números!", Toast.LENGTH_LONG).show();
        } else {
            float res = Float.valueOf(num1St) * Float.valueOf(num2St);
            resultado.setText("Resultado: " + res);
            gravaResultado("*", num1St, num2St, res);
        }
    }

    public void divisaoResultado (View v) {
        resultado.setText("");
        String num1St = numero1.getText().toString().trim();
        String num2St = numero2.getText().toString().trim();

        if (num1St.equals("") || num2St.equals("")) {
            Toast.makeText(this, "Por favor, informar os dois números!", Toast.LENGTH_LONG).show();
        } else {
            if (num2St.equals("0")) {
                Toast.makeText(this, "O segundo número não pode ser ZERO!", Toast.LENGTH_LONG).show();
            } else {
                float res = Float.valueOf(num1St) / Float.valueOf(num2St);
                resultado.setText("Resultado: " + res);
                gravaResultado("/", num1St, num2St, res);
            }
        }
    }

    public void limparResultado (View v) {
        listaResultados.setAdapter(null);
    }

    private void gravaResultado (String op, String n1, String n2, Float r) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO resultado(operacao, numero1, numero2, resultado) VALUES (");
        sql.append("'" + op + "',");
        sql.append(n1 + ",");
        sql.append(n2 + ",");
        sql.append(r);
        sql.append(")");

        try {
            Toast.makeText(getApplicationContext(), sql.toString(), Toast.LENGTH_LONG).show();
            db.execSQL(sql.toString());
        } catch (SQLException ex) {
            Toast.makeText(getApplicationContext(), "Error: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void criarBancoDados(){
        db = openOrCreateDatabase("calculadora.db", Context.MODE_PRIVATE, null);

        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS resultado(");
        sql.append("id INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sql.append("operacao VARCHAR(1), ");
        sql.append("numero1 FLOAT(8,2), ");
        sql.append("numero2 FLOAT(8,2), ");
        sql.append("resultado FLOAT(8,2)");
        sql.append(")");

        try {
            db.execSQL(sql.toString());
        } catch (SQLException ex) {
            Toast.makeText(getApplicationContext(), "Error: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}
