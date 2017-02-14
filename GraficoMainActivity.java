package software.de.engenharia.com.burndown;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;



public class GraficoMainActivity extends AppCompatActivity {


//  LineChart lineChart;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_grafico_main);
  }

  public void enviarTempoAtividades(View view) {
    EditText tempoET = (EditText) findViewById(R.id.tempoET);
    EditText atividadeET = (EditText) findViewById(R.id.atividadeET);
    String tempoValor = tempoET.getText().toString();
    String atividadeValor = atividadeET.getText().toString();

//    Intent intent = new Intent(this,GerarGraficoActivity.class);
//    intent.putExtra(GerarGraficoActivity.TEMPOSTRING,tempoValor);
//    intent.putExtra(GerarGraficoActivity.ATIVIDADESTRING,atividadeValor);

    Intent intent2 = new Intent(this,InserirDadosActivity.class);
    intent2.putExtra(InserirDadosActivity.TEMPOSTRING2,tempoValor);
    intent2.putExtra(InserirDadosActivity.ATIVIDADESTRING2,atividadeValor);

    startActivity(intent2);
  }
}

