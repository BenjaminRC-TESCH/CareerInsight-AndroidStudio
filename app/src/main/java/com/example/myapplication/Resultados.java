package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Resultados extends AppCompatActivity {
    private BarChart barChart;
    public int interesesServicioSocial = 0;
    public int interesesEjecutivoPersuasiva = 0;
    public int interesesVerbal = 0;
    public int interesesArtisticoPlastica = 0;
    public int interesesMusical = 0;
    public int interesesOrganizacion = 0;
    public int interesesCientifica = 0;
    public int interesesCalculo = 0;
    public int interesesMecanicoConstructiva = 0;
    public int interesesTrabajoAlAireLibre = 0;

    public int aptitudesServicioSocial = 0;
    public int aptitudesEjecutivoPersuasiva = 0;
    public int aptitudesVerbal = 0;
    public int aptitudesArtisticoPlastica = 0;
    public int aptitudesMusical = 0;
    public int aptitudesOrganizacion = 0;
    public int aptitudesCientifica = 0;
    public int aptitudesCalculo = 0;
    public int aptitudesMecanicoConstructiva = 0;
    public int aptitudesTrabajoAlAireLibre = 0;

    public static int valorInteresesSS;
    public static int valorInteresesEP;
    public static int valorInteresesV;
    public static int valorInteresesAP;
    public static int valorInteresesMS;
    public static int valorInteresesOG;
    public static int valorInteresesCT;
    public static int valorInteresesCL;
    public static int valorInteresesMC;
    public static int valorInteresesAL;

    public static int valorAptitudesSS;
    public static int valorAptitudesEP;
    public static int valorAptitudesV;
    public static int valorAptitudesAP;
    public static int valorAptitudesMS;
    public static int valorAptitudesOG;
    public static int valorAptitudesCT;
    public static int valorAptitudesCL;
    public static int valorAptitudesMC;
    public static int valorAptitudesAL;

    public static String puntajeIntereses;
    public static String puntajeAptitudes;
    public static String areaInteres;
    public static String carreasInteres;

    private TextView prueba;
    private Button Aceptar;

    private List<String> xvalues = Arrays.asList("I-SS","A-SS","I-EP","A-EP","I-V","A-V","I-AP","A-AP","I-MS",
            "A-MS","I-OG","A-OG","I-CT","A-CT","I-CL","A-CL","I-MC","A-MC","I-AL","A-AL");


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);


        crearGrafico();
        calcularAreasIntereses();
        calcularAreasAptitudes();

        Aceptar = findViewById(R.id.aceptarR);

        Aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Resultados.this, Reporte.class);
                i.putExtra("puntajeIntereses",puntajeIntereses);
                i.putExtra("puntajeAptitudes",puntajeAptitudes);
                i.putExtra("areaInteres",areaInteres);
                i.putExtra("carreasInteres",carreasInteres);

                i.putExtra("nombre", getIntent().getStringExtra("nombre"));
                i.putExtra("grado", getIntent().getStringExtra("grado"));
                i.putExtra("grupo", getIntent().getStringExtra("grupo"));
                startActivity(i);
            }
        });


    }

    //Funcion para convertir los valores a porcentaje
    public int convertirValor(int valorOriginal) {
        if (valorOriginal == 0) {
            return 0;
        } else if (valorOriginal == 1) {
            return 4;
        } else if (valorOriginal == 2) {
            return 8;
        } else if (valorOriginal == 3) {
            return 13;
        } else if (valorOriginal == 4) {
            return 17;
        } else if (valorOriginal == 5) {
            return 21;
        } else if (valorOriginal == 6) {
            return 25;
        } else if (valorOriginal == 7) {
            return 29;
        } else if (valorOriginal == 8) {
            return 33;
        } else if (valorOriginal == 9) {
            return 38;
        } else if (valorOriginal == 10) {
            return 42;
        } else if (valorOriginal == 11) {
            return 46;
        } else if (valorOriginal == 12) {
            return 50;
        } else if (valorOriginal == 13) {
            return 54;
        } else if (valorOriginal == 14) {
            return 58;
        } else if (valorOriginal == 15) {
            return 63;
        } else if (valorOriginal == 16) {
            return 67;
        } else if (valorOriginal == 17) {
            return 71;
        } else if (valorOriginal == 18) {
            return 75;
        } else if (valorOriginal == 19) {
            return 79;
        } else if (valorOriginal == 20) {
            return 83;
        } else if (valorOriginal == 21) {
            return 89;
        } else if (valorOriginal == 22) {
            return 92;
        } else if (valorOriginal == 23) {
            return 96;
        } else if (valorOriginal == 24) {
            return 100;
        } else {
            return valorOriginal;
        }
    }

    //Funcion para calcular el area de interes
    public void calcularAreasIntereses() {
        // Calcular el máximo valor de intereses
        int maxIntereses = Math.max(valorInteresesSS, Math.max(valorInteresesEP, Math.max(valorInteresesV, Math.max(valorInteresesAP,
                Math.max(valorInteresesMS, Math.max(valorInteresesOG, Math.max(valorInteresesCT, Math.max(valorInteresesCL,
                        Math.max(valorInteresesMC, valorInteresesAL)))))))));
        // Asignar el valor correspondiente a puntajeIntereses
        if (maxIntereses == valorInteresesSS) {
            areaInteres = "Servicio Social";
            carreasInteres = "Urbanismo, Ingeniería Civil, Sociología, Trabajo Social, Derecho, Enfermería y Obstetricia, Psicología, Pedagogía, Medicina, Odontología, Ciencias Políticas y Administración Pública, Economía, Relaciones Internacionales, Enseñanza de Inglés, Optometría, Planificación para el Desarrollo Agropecuario, Estudios Latinoamericanos, Bibliotecología y Estudios de la Información, Educación Musical.";
            puntajeIntereses = "Preferencia por participar en actividades directamente relacionadas con el bienestar de las personas.";
        } else if (maxIntereses == valorInteresesEP) {
            areaInteres = "Ejecutivo Persuasiva";
            carreasInteres = "Actuaría, Economía, Administración, Ciencias  Políticas y Administración Pública, Derecho, Ingeniería Industrial, Ingeniería de Alimentos, Ingeniería Petrolera, Psicología, Medicina, Relaciones Internacionales.";
            puntajeIntereses = "Agrado por planear, organizar o dirigir las actividades de personas o agrupaciones.";
        } else if (maxIntereses == valorInteresesV) {
            areaInteres = "Verbal";
            carreasInteres = "Derecho, Ciencias de la Comunicación, Letras Clásicas, Lengua y Literaturas Modernas, Relaciones Internacionales, Literatura Dramática y Teatro, Sociología, Ciencias Políticas y Administración Pública.";
            puntajeIntereses = "Gusto por la lectura de obras diversas y satisfacción al expresarse verbalmente o por escrito.";
        } else if (maxIntereses == valorInteresesAP) {
            areaInteres = "Artístico Plástica";
            carreasInteres = "Artes  Visuales, Diseño y Comunicación Visual, Diseño Gráfico, Arquitectura, Arquitectura de Paisaje, Odontología, Literatura Dramática y Teatro.";
            puntajeIntereses = "Agrado por conocer o realizar actividades creativas como dibujo, la pintura, la escultura, el modelado, etcétera.";
        } else if (maxIntereses == valorInteresesMS) {
            areaInteres = "Musical";
            carreasInteres = "Composición, Instrumentista, Canto, Etnomusicología, Piano, Educación Musical.";
            puntajeIntereses = "Gusto por la ejecución, estudio o composición de la música.";
        } else if (maxIntereses == valorInteresesOG) {
            areaInteres = "Organización";
            carreasInteres = "Bibliotecología y Estudios de la Información, Actuaría, Matemáticas Aplicadas y Computación, Informática, Contaduría, Administración, Ciencias de la Comunicación, Matemáticas, Relaciones Internacionales, Economía, Ciencias Políticas y Administración Pública.";
            puntajeIntereses = "Preferencia por actividades que requieren orden y sistematización.";
        } else if (maxIntereses == valorInteresesCT) {
            areaInteres = "Científica";
            carreasInteres = "Investigación Biomédica Básica, Ciencias Genómicas, Matemáticas, Física, Ingeniería Mecatrónica, Química, Biología, Psicología, Medicina Veterinaria y Zootecnia, Ingeniería Química, Química Farmacéutico-Biológica, Química Industrial, Química de Alimentos, Ingeniería en Alimentos, Filosofía, Historia.";
            puntajeIntereses = "Gusto por conocer o investigar los fenómenos, las causas que los provocan y los principios que los explican.";
        } else if (maxIntereses == valorInteresesCL) {
            areaInteres = "Cálculo";
            carreasInteres = "Matemáticas, Economía, Contaduría, Física, Ingenierías: Geológica, Geofísica, Civil, en Telecomunicaciones, Computación, Topográfica, Industrial, Química; Arquitectura, Geografía, Actuaría, Informática, Química, Matemáticas Aplicadas y Computación, Ciencias de la Comunicación.";
            puntajeIntereses = "Gusto por resolver problemas de tipo cuantitativo, donde se utilizan las operaciones matemáticas.";
        } else if (maxIntereses == valorInteresesMC) {
            areaInteres = "Mecánico Constructiva";
            carreasInteres = "Ingenierías: Eléctrica-Electrónica, Geofísica, Topográfica, Civil, Petrolera, Mecánica Eléctrica, Química, en Computación, Mecánica Química Metalúrgica, Mecatrónica, Arquitectura, Diseño Industrial.";
            puntajeIntereses = "Atracción por armar, conocer o descubrir mecanismos mediante los cuales funciona un aparato, así como proyectar y construir objetos diversos.";
        } else if (maxIntereses == valorInteresesAL) {
            areaInteres = "Trabajo Al Aire Libre ";
            carreasInteres = "Biología, Ingeniería Agrícola, Ingeniería Geológica, Ingeniería Petrolera, Geografía, Ingeniería Civil, Ingeniería Topográfica y Geodésica, Medicina Veterinaria y Zootecnia, Planificación para el Desarrollo Agropecuario, Urbanismo.";
            puntajeIntereses = "Satisfacción por actividades que se realizan en lugares abiertos y/o apartados de los conglomerados urbanos.";
        } else {
            areaInteres = "Servicio Social";
            carreasInteres = "Urbanismo, Ingeniería Civil, Sociología, Trabajo Social, Derecho, Enfermería y Obstetricia, Psicología, Pedagogía, Medicina, Odontología, Ciencias Políticas y Administración Pública, Economía, Relaciones Internacionales, Enseñanza de Inglés, Optometría, Planificación para el Desarrollo Agropecuario, Estudios Latinoamericanos, Bibliotecología y Estudios de la Información, Educación Musical.";
            puntajeIntereses = "Preferencia por participar en actividades directamente relacionadas con el bienestar de las personas.";
        }

    }

    //Funcion para calcular el area de interes
    public void calcularAreasAptitudes() {
        // Calcular el máximo valor de aptitudes
        int maxAptitudes = Math.max(valorAptitudesSS, Math.max(valorAptitudesEP, Math.max(valorAptitudesV, Math.max(valorAptitudesAP,
                Math.max(valorAptitudesMS, Math.max(valorAptitudesOG, Math.max(valorAptitudesCT, Math.max(valorAptitudesCL,
                        Math.max(valorAptitudesMC, valorAptitudesAL)))))))));
        if (maxAptitudes == valorAptitudesSS) {
            puntajeAptitudes = "Habilidad para comprender problemas humanos, para tratar personas, cooperar y persuadir; para hacer lo más adecuado ante situaciones sociales. Actitud de ayuda afectuosa y desinteresada hacia sus semejantes.";
        } else if (maxAptitudes == valorAptitudesEP) {
            puntajeAptitudes = "Capacidad para organizar, dirigir y supervisar a otros adecuadamente; poseer iniciativa, confianza en sí mismo, ambición de progreso, habilidad para dominar en situaciones sociales y en relaciones de persona a persona.";
        } else if (maxAptitudes == valorAptitudesV) {
            puntajeAptitudes = "Habilidad  para  comprender  y  expresarse  correctamente.  También  para  utilizar  las  palabras precisas y adecuadas.";
        } else if (maxAptitudes == valorAptitudesAP) {
            puntajeAptitudes = "Habilidad para apreciar las formas o colores de un objeto, dibujo, escultura o pintura y para crear obras de mérito artístico en pintura, escultura, grabado o dibujo.";
        } else if (maxAptitudes == valorAptitudesMS) {
            puntajeAptitudes = "Habilidad para captar y distinguir sonidos en  sus diversas modalidades, para imaginar estos sonidos, reproducirlos o utilizarlos en forma creativa; sensibilidad a la combinación y armonía de sonidos.";
        } else if (maxAptitudes == valorAptitudesOG) {
            puntajeAptitudes = "Capacidad de  organización, orden,  exactitud  y  rapidez en  el  manejo de  nombres, números, documentos, sistemas y sus detalles en trabajos rutinarios.";
        } else if (maxAptitudes == valorAptitudesCT) {
            puntajeAptitudes = "Habilidad para la investigación; aptitud para captar, definir y comprender principios y relaciones causales de los fenómenos proponiéndose siempre la obtención de la novedad.";
        } else if (maxAptitudes == valorAptitudesCL) {
            puntajeAptitudes = "Dominio de las operaciones y mecanizaciones numéricas, así como habilidad para el cálculo matemático.";
        } else if (maxAptitudes == valorAptitudesMC) {
            puntajeAptitudes = "Comprensión y habilidad en la manipulación de objetos y facilidad para percibir, imaginar y analizar formas en dos o tres dimensiones, así como para abstraer sistemas, mecanismos y movimientos.";
        } else if (maxAptitudes == valorAptitudesAL) {
            puntajeAptitudes = "Habilidad en el uso de las manos para el manejo de herramientas; ejecución de movimientos coordinados y precisos.";
        } else {
            puntajeAptitudes = "Habilidad para comprender problemas humanos, para tratar personas, cooperar y persuadir; para hacer lo más adecuado ante situaciones sociales. Actitud de ayuda afectuosa y desinteresada hacia sus semejantes.";
        }
    }

    public void crearGrafico(){
        Bundle datos = this.getIntent().getExtras();
        //Se recupera datos de intereses
        interesesServicioSocial = datos.getInt("interesesServicioSocialAP");
        interesesEjecutivoPersuasiva = datos.getInt("interesesEjecutivoPersuasivaAP");
        interesesVerbal = datos.getInt("interesesVerbalAP");
        interesesArtisticoPlastica = datos.getInt("interesesArtisticoPlasticaAP");
        interesesMusical = datos.getInt("interesesMusicalAP");
        interesesOrganizacion = datos.getInt("interesesOrganizacionAP");
        interesesCientifica = datos.getInt("interesesCientificaAP");
        interesesCalculo = datos.getInt("interesesCalculoAP");
        interesesMecanicoConstructiva = datos.getInt("interesesMecanicoConstructivaAP");
        interesesTrabajoAlAireLibre = datos.getInt("interesesTrabajoAlAireLibreAP");

        //se recupera datos de aptitudes
        aptitudesServicioSocial = datos.getInt("aptitudesServicioSocial");
        aptitudesEjecutivoPersuasiva = datos.getInt("aptitudesEjecutivoPersuasiva");
        aptitudesVerbal = datos.getInt("aptitudesVerbal");
        aptitudesArtisticoPlastica = datos.getInt("aptitudesArtisticoPlastica");
        aptitudesMusical = datos.getInt("aptitudesMusical");
        aptitudesOrganizacion = datos.getInt("aptitudesOrganizacion");
        aptitudesCientifica = datos.getInt("aptitudesCientifica");
        aptitudesCalculo = datos.getInt("aptitudesCalculo");
        aptitudesMecanicoConstructiva = datos.getInt("aptitudesMecanicoConstructiva");
        aptitudesTrabajoAlAireLibre = datos.getInt("aptitudesTrabajoAlAireLibre");

        //Convierte los datos a porcentaje
        valorInteresesSS = convertirValor(interesesServicioSocial);
        valorInteresesEP = convertirValor(interesesEjecutivoPersuasiva);
        valorInteresesV = convertirValor(interesesVerbal);
        valorInteresesAP = convertirValor(interesesArtisticoPlastica);
        valorInteresesMS = convertirValor(interesesMusical);
        valorInteresesOG = convertirValor(interesesOrganizacion);
        valorInteresesCT = convertirValor(interesesCientifica);
        valorInteresesCL = convertirValor(interesesCalculo);
        valorInteresesMC = convertirValor(interesesMecanicoConstructiva);
        valorInteresesAL = convertirValor(interesesTrabajoAlAireLibre);

        //Convierte los datos a porcentaje
        valorAptitudesSS = convertirValor(aptitudesServicioSocial);
        valorAptitudesEP = convertirValor(aptitudesEjecutivoPersuasiva);
        valorAptitudesV = convertirValor(aptitudesVerbal);
        valorAptitudesAP = convertirValor(aptitudesArtisticoPlastica);
        valorAptitudesMS = convertirValor(aptitudesMusical);
        valorAptitudesOG = convertirValor(aptitudesOrganizacion);
        valorAptitudesCT = convertirValor(aptitudesCientifica);
        valorAptitudesCL = convertirValor(aptitudesCalculo);
        valorAptitudesMC = convertirValor(aptitudesMecanicoConstructiva);
        valorAptitudesAL = convertirValor(aptitudesTrabajoAlAireLibre);


        //Creacion de la grafica de los resultados
        BarChart barChart = findViewById(R.id.barChart);
        barChart.getAxisRight().setDrawLabels(false);

        ArrayList<BarEntry> entries = new ArrayList<>();

        entries.add(new BarEntry(0,valorInteresesSS));
        entries.add(new BarEntry(1,valorAptitudesSS));

        entries.add(new BarEntry(2,valorInteresesEP));
        entries.add(new BarEntry(3,valorAptitudesEP));

        entries.add(new BarEntry(4,valorInteresesV));
        entries.add(new BarEntry(5,valorAptitudesV));

        entries.add(new BarEntry(6,valorInteresesAP));
        entries.add(new BarEntry(7,valorAptitudesAP));

        entries.add(new BarEntry(8,valorInteresesMS));
        entries.add(new BarEntry(9,valorAptitudesMS));

        entries.add(new BarEntry(10,valorInteresesOG));
        entries.add(new BarEntry(11,valorAptitudesOG));

        entries.add(new BarEntry(12,valorInteresesCT));
        entries.add(new BarEntry(13,valorAptitudesCT));

        entries.add(new BarEntry(14,valorInteresesCL));
        entries.add(new BarEntry(15,valorAptitudesCL));

        entries.add(new BarEntry(16,valorInteresesMC));
        entries.add(new BarEntry(17,valorAptitudesMC));

        entries.add(new BarEntry(18,valorInteresesAL));
        entries.add(new BarEntry(19,valorAptitudesAL));


        YAxis yAxis = barChart.getAxisLeft();
        yAxis.setAxisMaximum(0);
        yAxis.setAxisMaximum(100);
        yAxis.setAxisLineWidth(2);
        yAxis.setAxisLineColor(Color.BLACK);
        yAxis.setLabelCount(10);

        BarDataSet dataSet = new BarDataSet(entries,"Resultados");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        BarData barData = new BarData(dataSet);
        barChart.setData(barData);

        barChart.getDescription().setEnabled(false);
        barChart.invalidate();

        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xvalues));
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getXAxis().setGranularity(1);
        barChart.getXAxis().setGranularityEnabled(true);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == event.KEYCODE_BACK) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("¿Desea salir del test?")
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);

                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
            builder.show();
        }

        return super.onKeyDown(keyCode, event);
    }

}