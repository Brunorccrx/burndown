package software.de.engenharia.com.burndown;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;


public class InserirDadosActivity extends AppCompatActivity {
  public static final String TEMPOSTRING2 = "tempo";
  public static final String ATIVIDADESTRING2 = "atividade";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_inserir_dados);
  }

  List<Entry> entradas;
  List<String> nomes;
  public static List<ILineDataSet> retas = new ArrayList<>();
  float atividade;
  float tempo;
  String nome;
  ILineDataSet reta;
  Entry entrada;

  public void enviarTempoAtividades(View view) {
    EditText nomeET = (EditText) findViewById(R.id.nomeET);
    EditText atividadeET = (EditText) findViewById(R.id.atividadeET);
    EditText tempoET = (EditText) findViewById(R.id.tempoET);
    nomes = new ArrayList<>();
    nome = nomeET.getText().toString();
    atividade = Float.parseFloat(atividadeET.getText().toString());
    tempo = Float.parseFloat(tempoET.getText().toString());
    entrada = new Entry(atividade, tempo);


    reta = pegarReta();

    if(reta==null) {
      entradas = new ArrayList<>();
      entradas.add(entrada);
      reta = new LineDataSet(entradas, nome);

//      ((LineDataSet) reta).setColor(Color.RED);
      nomes.add(nome);
      retas.add(reta);
    }
    else
      reta.addEntry(entrada);



  }

  private ILineDataSet pegarReta() {
    for (ILineDataSet reta : retas)
      if (nome.equals(reta.getLabel()))
        return reta;

    return null;
  }

  public void enviarDataSets(View view) {
//    retas.get(0);
    Intent intent = getIntent();
    String tempo2 = intent.getStringExtra(TEMPOSTRING2);
    String atividade2 = intent.getStringExtra(ATIVIDADESTRING2);

    intent = new Intent(this,GerarGraficoActivity.class);

    intent.putExtra(GerarGraficoActivity.TEMPOSTRING,tempo2);
    intent.putExtra(GerarGraficoActivity.ATIVIDADESTRING,atividade2);

    startActivity(intent);
  }
}