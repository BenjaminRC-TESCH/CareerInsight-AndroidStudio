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

public class Intereses extends AppCompatActivity {

    private String[] preguntas = {
            "1. Atender y cuidar enfermos",
            "2. Intervenir activamente en las discusiones de clase.",
            "3. Escribir cuentos, crónicas o artículos.",
            "4. Dibujar y pintar.",
            "5. Cantar en un coro estudiantil.",
            "6. Llevar en orden tus libros y cuadernos.",
            "7. Conocer y estudiar la estructura de las plantas y de los animales.",
            "8. Resolver cuestionarios de matemáticas.",
            "9. Armar y desarmar objetos mecánicos.",
            "10. Salir de excursión.",
            "11. Proteger a los muchachos menores del grupo.",
            "12. Ser jefe de un grupo.",
            "13. Leer obras literarias.",
            "14. Moldear el barro, plastilina o cualquier otro material.",
            "15. Escuchar música clásica.",
            "16. Ordenar y clasificar los libros de una biblioteca.",
            "17. Hacer experimentos en un laboratorio.",
            "18. Resolver problemas de aritmética.",
            "19. Manejar herramientas y maquinaria.",
            "20. Pertenecer a un grupo de exploradores.",
            "21. Ser miembro de una sociedad de ayuda y asistencia.",
            "22. Dirigir la campaña política para un candidato estudiantil.",
            "23. Hacer versos para una publicación.",
            "24. Encargarte del decorado del lugar para un festival.",
            "25. Aprender a tocar un instrumento musical.",
            "26. Aprender a escribir a máquina y en taquigrafía.",
            "27. Investigar el origen de las costumbres de los pueblos.",
            "28. Llevar las cuentas de una institución.",
            "29. Construir objeto o muebles.",
            "30. Trabajar al aire libre, fuera de la ciudad.",
            "31. Enseñar a leer a los analfabetos.",
            "32. Hacer propaganda para la difusión de una idea.",
            "33. Representar un papel en una obra de teatro.",
            "34. Idear y diseñar el escudo de un club o sociedad.",
            "35. Ser miembro de una asociación musical.",
            "36. Ayudar a calificar pruebas.",
            "37. Estudiar y entender las causas de los movimientos sociales.",
            "38. Explicar a otros cómo resolver problemas de matemáticas.",
            "39. Reparar las instalaciones eléctricas, de gas o de plomería en tu casa.",
            "40. Sembrar y plantar en una granja durante las vacaciones.",
            "41. Ayudar a tus compañeros en sus dificultades y preocupaciones.",
            "42. Leer biografías de políticos eminentes.",
            "43. Participar en un concurso de oratoria.",
            "44. Diseñar el vestuario para una función teatral.",
            "45. Leer biografías de músicos eminentes.",
            "46. Encargarte del archivo y los documentos de una sociedad.",
            "47. Leer revistas y libros científicos.",
            "48. Participar en concursos de matemáticas.",
            "49. Proyectar y dirigir alguna construcción.",
            "50. Atender animales en un rancho durante las vacaciones.",
            "51. ¿Qué tanto te gustaría trabajar como? Funcionario al servicio de las clases humildes.",
            "52. ¿Qué tanto te gustaría trabajar como? Experto en relaciones sociales de una gran empresa.",
            "53. ¿Qué tanto te gustaría trabajar como? Escritor en un periódico o empresa editorial.",
            "54. ¿Qué tanto te gustaría trabajar como? Dibujante profesional en una empresa.",
            "55. ¿Qué tanto te gustaría trabajar como? Concertista en una sinfónica.",
            "56. ¿Qué tanto te gustaría trabajar como? Técnico organizador de oficinas.",
            "57. ¿Qué tanto te gustaría trabajar como? Investigar en un laboratorio.",
            "58. ¿Qué tanto te gustaría trabajar como? Experto calculista en una institución.",
            "59. ¿Qué tanto te gustaría trabajar como? Perito mecánico en un taller.",
            "60. ¿Qué tanto te gustaría trabajar como? Técnico cuyas actividades se desempeñan fuera de la ciudad."

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

    private TextView preguntaTextView;
    private RadioGroup radioGroup;
    private Button aceptarButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intereses);

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
                interesesServicioSocial += respuesta;
                break;
            case 1:
                interesesEjecutivoPersuasiva += respuesta;
                break;
            case 2:
                interesesVerbal += respuesta;
                break;
            case 3:
                interesesArtisticoPlastica += respuesta;
                break;
            case 4:
                interesesMusical += respuesta;
                break;
            case 5:
                interesesOrganizacion += respuesta;
                break;
            case 6:
                interesesCientifica += respuesta;
                break;
            case 7:
                interesesCalculo += respuesta;
                break;
            case 8:
                interesesMecanicoConstructiva += respuesta;
                break;
            case 9:
                interesesTrabajoAlAireLibre += respuesta;
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
                    Intent i = new Intent(Intereses.this, Aptitudes.class);
                    i.putExtra("interesesServicioSocial",interesesServicioSocial);
                    i.putExtra("interesesEjecutivoPersuasiva",interesesEjecutivoPersuasiva);
                    i.putExtra("interesesVerbal",interesesVerbal);
                    i.putExtra("interesesArtisticoPlastica",interesesArtisticoPlastica);
                    i.putExtra("interesesMusical",interesesMusical);
                    i.putExtra("interesesOrganizacion",interesesOrganizacion);
                    i.putExtra("interesesCientifica",interesesCientifica);
                    i.putExtra("interesesCalculo",interesesCalculo);
                    i.putExtra("interesesMecanicoConstructiva",interesesMecanicoConstructiva);
                    i.putExtra("interesesTrabajoAlAireLibre",interesesTrabajoAlAireLibre);
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

}
