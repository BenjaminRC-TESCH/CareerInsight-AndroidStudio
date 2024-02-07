package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Aptitudes extends AppCompatActivity {

    private String[] preguntas = {
            "1. Tratar y hablar con sensibilidad a las personas.",
            "2. Ser jefe competente de un grupo, equipo o sociedad.",
            "3. Expresarte con facilidad en clase o al platicar con tus amigos.",
            "4. Dibujar casar, objetos, figuras humanas, etcétera.",
            "5. Cantar en un grupo.",
            "6. Llevar en forma correcta y ordenada los apuntes de clase. ",
            "7. Entender principios y experimentos de biología.",
            "8. Ejecutar con rapidez y exactitud operaciones aritméticas.",
            "9. Armar y componer objetos mecánicos como chapas,timbres,etcétera.",
            "10. Actividades que requieren destreza manual.",
            "11. Ser miembro activo y útil en un club o sociedad.",
            "12. Organizar y dirigir festivales, encuentros deportivos, excursiones o campañas sociales.",
            "13. Redactar composiciones o artículos periodísticos.",
            "14. Pintar paisajes.",
            "15. Tocar un instrumento musical.",
            "16. Ordenar y clasificar debidamente documentos en una oficina.",
            "17. Entender principios y experimentos de física.",
            "18. Resolver problemas de aritmética.",
            "19. Desarmar, armar y componer objetos complicados.",
            "20. Manejar con habilidad herramienta de carpintería.",
            "21. Colaborar con otros para el bien de la comunidad.",
            "22. Convencer a otros para que hagan lo que crees que deben hacer.",
            "23. Componer versos serios o jocosos.",
            "24. Decorar artísticamente un salón, corredor, escenario o patio para un festival.",
            "25. Distinguir cuando alguien desentona en las canciones o piezas musicales.",
            "26. Contestar y redactar correctamente oficios y cartas.",
            "27. Entender principios y experimentos de química.",
            "28. Resolver rompecabezas numéricos.",
            "29. Resolver rompecabezas de alambre o de madera.",
            "30. Manejar con facilidad herramientas mecánicas como pinzas, llaves de tuercas, desarmador, etcétera.",
            "31. Saber escuchar a otros con paciencia y comprender su punto de vista.",
            "32. Dar órdenes a otros con seguridad y naturalidad.",
            "33. Escribir cuentos, narraciones o historietas.",
            "34. Modelar con barro, plastilina o grabar madera.",
            "35. Entonar correctamente las canciones de moda.",
            "36. Anotar y manejar con exactitud y rapidez nombres, números y otros datos.",
            "37. Entender principios y hechos económicos y sociales.",
            "38. Resolver problemas de álgebra.",
            "39. Armar y componer muebles.",
            "40. Manejar con habilidad pequeñas piezas y herramientas como agujas, manecillas, joyas, piezas de relojería, etcétera.",
            "41. Conversar en las reuniones y fiestas con acierto y naturalidad.",
            "42. Dirigir un grupo o equipo en situaciones difíciles o peligrosas.",
            "43. Distinguir y apreciar la buena literatura.",
            "44. Distinguir y apreciar la buena pintura.",
            "45. Distinguir y apreciar la buena música.",
            "46. Encargarse de recibir, anotar y dar recados sin olvidar detalles importantes.",
            "47. Entender las causas que determinan los acontecimientos históricos.",
            "48. Resolver problemas de geometría.",
            "49. Aprender el funcionamiento de ciertos mecanismos complicados como motores, relojes, bombas, etcétera.",
            "50. Hacer con facilidad trazos geométricos con la ayudad de las escuadras, la regla “T” y compás.",
            "51. Actuar con desinterés.",
            "52. Corregir a los demás sin ofenderlos.",
            "53. Exponer juicios públicamente sin preocupación por la crítica.",
            "54. Colaborar en la elaboración de un libro sobre el arte en la Arquitectura.",
            "55. Dirigir un grupo musical.",
            "56. Colaborar en el desarrollo de métodos más eficientes de trabajo.",
            "57. Realizar investigaciones científicas teniendo como finalidad la búsqueda de la verdad.",
            "58. Enseñar a resolver problemas de matemáticas.",
            "59. Inducir a las personas a obtener resultados prácticos.",
            "60. Participar en un concurso de modelismo de coches, aviones, barcos, etcétera."
    };

    private List<Integer> respuestas = new ArrayList<>();
    private int preguntaActualIndex = 0;

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

    private TextView preguntaTextView;
    private RadioGroup radioGroup;
    private Button aceptarButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aptitudes);

        capturarDatos();

        preguntaTextView = findViewById(R.id.pregunta);
        preguntaTextView.setMovementMethod(new ScrollingMovementMethod());

        preguntaTextView = findViewById(R.id.pregunta);
        radioGroup = findViewById(R.id.radioGroup);
        aceptarButton = findViewById(R.id.buttonAP);

        aceptarButton.setEnabled(false); // Inicialmente deshabilitado

        mostrarPregunta();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                aceptarButton.setEnabled(checkedId != -1);
            }
        });

        aceptarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarRespuesta();
                mostrarSiguientePregunta();
            }
        });
    }

    private void mostrarPregunta() {
        preguntaTextView.setText(preguntas[preguntaActualIndex]);
        radioGroup.clearCheck(); // Limpiar la selección de RadioButton
        aceptarButton.setEnabled(false); // Deshabilitar el botón al mostrar una nueva pregunta
    }

    private void guardarRespuesta() {
        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);

        int respuesta = -1; // Inicializamos la respuesta como -1 para detectar si no se seleccionó ninguna opción

        if (selectedRadioButton != null) {
            respuesta = Integer.parseInt(selectedRadioButton.getTag().toString());
        }

        respuestas.add(respuesta);

        int preguntaPosicion = preguntaActualIndex % 10;

        switch (preguntaPosicion) {
            case 0:
                aptitudesServicioSocial += respuesta;
                break;
            case 1:
                aptitudesEjecutivoPersuasiva += respuesta;
                break;
            case 2:
                aptitudesVerbal += respuesta;
                break;
            case 3:
                aptitudesArtisticoPlastica += respuesta;
                break;
            case 4:
                aptitudesMusical += respuesta;
                break;
            case 5:
                aptitudesOrganizacion += respuesta;
                break;
            case 6:
                aptitudesCientifica += respuesta;
                break;
            case 7:
                aptitudesCalculo += respuesta;
                break;
            case 8:
                aptitudesMecanicoConstructiva += respuesta;
                break;
            case 9:
                aptitudesTrabajoAlAireLibre += respuesta;
                break;
        }
    }

    private void mostrarSiguientePregunta() {
        if (preguntaActualIndex < preguntas.length - 1) {
            preguntaActualIndex++;
            mostrarPregunta();
        } else {
            //String textoMostrar = "";
            //textoMostrar += "interesesServicioSocial: " + interesesServicioSocial + "\n";
            //textoMostrar += "interesesEjecutivoPersuasiva: " + interesesEjecutivoPersuasiva + "\n";
            //textoMostrar += "interesesVerbal: " + interesesVerbal + "\n";
            //textoMostrar += "interesesArtisticoPlastica: " + interesesArtisticoPlastica + "\n";
            //textoMostrar += "interesesMusical: " + interesesMusical + "\n";
            //textoMostrar += "interesesOrganizacion: " + interesesOrganizacion + "\n";
            //textoMostrar += "interesesCientifica: " + interesesCientifica + "\n";
            //textoMostrar += "interesesCalculo: " + interesesCalculo + "\n";
            //textoMostrar += "interesesMecanicoConstructiva: " + interesesMecanicoConstructiva + "\n";
            //textoMostrar += "interesesTrabajoAlAireLibre: " + interesesTrabajoAlAireLibre + "\n";

            //preguntaTextView.setText(textoMostrar);

            aceptarButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Aptitudes.this, Resultados.class);
                    i.putExtra("aptitudesServicioSocial",aptitudesServicioSocial);
                    i.putExtra("aptitudesEjecutivoPersuasiva",aptitudesEjecutivoPersuasiva);
                    i.putExtra("aptitudesVerbal",aptitudesVerbal);
                    i.putExtra("aptitudesArtisticoPlastica",aptitudesArtisticoPlastica);
                    i.putExtra("aptitudesMusical",aptitudesMusical);
                    i.putExtra("aptitudesOrganizacion",aptitudesOrganizacion);
                    i.putExtra("aptitudesCientifica",aptitudesCientifica);
                    i.putExtra("aptitudesCalculo",aptitudesCalculo);
                    i.putExtra("aptitudesMecanicoConstructiva",aptitudesMecanicoConstructiva);
                    i.putExtra("aptitudesTrabajoAlAireLibre",aptitudesTrabajoAlAireLibre);

                    i.putExtra("interesesServicioSocialAP",interesesServicioSocial);
                    i.putExtra("interesesEjecutivoPersuasivaAP",interesesEjecutivoPersuasiva);
                    i.putExtra("interesesVerbalAP",interesesVerbal);
                    i.putExtra("interesesArtisticoPlasticaAP",interesesArtisticoPlastica);
                    i.putExtra("interesesMusicalAP",interesesMusical);
                    i.putExtra("interesesOrganizacionAP",interesesOrganizacion);
                    i.putExtra("interesesCientificaAP",interesesCientifica);
                    i.putExtra("interesesCalculoAP",interesesCalculo);
                    i.putExtra("interesesMecanicoConstructivaAP",interesesMecanicoConstructiva);
                    i.putExtra("interesesTrabajoAlAireLibreAP",interesesTrabajoAlAireLibre);

                    i.putExtra("nombre", getIntent().getStringExtra("nombre"));
                    i.putExtra("grado", getIntent().getStringExtra("grado"));
                    i.putExtra("grupo", getIntent().getStringExtra("grupo"));
                    startActivity(i);
                }
            });
        }
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
                            finishAffinity();
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

    //capturar datos de intereses
    public void capturarDatos(){
        Bundle datos = this.getIntent().getExtras();
        //Se recupera datos de intereses
        interesesServicioSocial = datos.getInt("interesesServicioSocial");
        interesesEjecutivoPersuasiva = datos.getInt("interesesEjecutivoPersuasiva");
        interesesVerbal = datos.getInt("interesesVerbal");
        interesesArtisticoPlastica = datos.getInt("interesesArtisticoPlastica");
        interesesMusical = datos.getInt("interesesMusical");
        interesesOrganizacion = datos.getInt("interesesOrganizacion");
        interesesCientifica = datos.getInt("interesesCientifica");
        interesesCalculo = datos.getInt("interesesCalculo");
        interesesMecanicoConstructiva = datos.getInt("interesesMecanicoConstructiva");
        interesesTrabajoAlAireLibre = datos.getInt("interesesTrabajoAlAireLibre");
    }



}
