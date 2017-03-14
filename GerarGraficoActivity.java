package software.de.engenharia.com.burndown;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static software.de.engenharia.com.burndown.InserirDadosActivity.retas;

public class GerarGraficoActivity extends AppCompatActivity {
//  final FirebaseDatabase database = FirebaseDatabase.getInstance();
//  DatabaseReference ref = database.getReference("server/saving-data/fireblog");

  LineChart graficodereta;
  public static final String TEMPOSTRING = "tempo";
  public static final String ATIVIDADESTRING = "atividade";
  private float tempo;
  private float atividade;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_gerar_grafico);
    Intent intent=getIntent();
     tempo = Float.parseFloat(intent.getStringExtra(TEMPOSTRING));
     atividade = Float.parseFloat(intent.getStringExtra(ATIVIDADESTRING));
    desenharGrafico();
  }
  private void desenharGrafico(){
    graficodereta = (LineChart) findViewById(R.id.grafico_de_reta);
    List<Entry> ideal= new ArrayList<>();
    List<ILineDataSet> retasIdeal = new ArrayList<>();
    float soma=0;
    float aux=tempo/atividade;

    for (int i = 0; i <=atividade ; i++) {
      ideal.add(new Entry(i,tempo-soma));
      soma = soma + aux;
    }
    LineDataSet entrada = new LineDataSet(ideal,"Trajeto Ideal");
    entrada.setColor(Color.BLUE);
    entrada.setDrawCircles(false);
    entrada.setDrawValues(false);
//    entrada.addColor(Color.GREEN);  Adicionar duas cores a reta




    retasIdeal.add(entrada);
    for (ILineDataSet reta : retas)
      retasIdeal.add(reta);

//    Tentativa de ligação com banco de dados

//    DatabaseReference usersRef = ref.child("users");
//    Map<String, List<ILineDataSet>> users = new HashMap<>();
//    users.put(retas.get(1).getLabel(),retasIdeal );
//      usersRef.setValue(users);

//      retasIdeal.add(retas.get(0));
//    retasIdeal.add(retas.get(1));
    graficodereta.setData(new LineData(retasIdeal));
//    graficodereta.setData(new LineData(retas));
//    graficodereta.getAxisLeft().setTextColor(Color.WHITE);  mudar cor dos numeros
//    graficodereta.getAxisRight().setTextColor(Color.WHITE); mudar cor dos numeros
    graficodereta.getXAxis().setTextColor(Color.WHITE);
    graficodereta.getXAxis().setValueFormatter(new PercentFormatter());
//    graficodereta.setBackgroundColor(Color.rgb(33,33,33)); mudar cor de fundo para preto


  }
}
