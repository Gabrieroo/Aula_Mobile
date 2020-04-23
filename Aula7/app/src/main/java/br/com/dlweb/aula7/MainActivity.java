package br.com.dlweb.aula7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_tela2 = (Button)findViewById(R.id.btn_tela2);
        btn_tela2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Activity2.class);
                startActivity(i);
                //Mensagem para usuário
                //Toast.makeText(getApplicationContext(), "Olá mundo", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
