package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.FileNotFoundException;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.print.PrintAttributes;
import android.view.View;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class Reporte extends AppCompatActivity {

    public String puntajeIntereses;
    public String puntajeAptitudes;
    public String areaInteres;
    public String carreasInteres;

    public String nombre;
    public String grado;
    public String grupo;

    String textoInforme;

    private TextView reporte;

    private Button generar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte);

        reporte = findViewById(R.id.reporte_resultados);
        reporte.setMovementMethod(new ScrollingMovementMethod());

        //Obtiene los datos de resultados
        puntajeIntereses = getIntent().getStringExtra("puntajeIntereses");
        puntajeAptitudes = getIntent().getStringExtra("puntajeAptitudes");
        areaInteres = getIntent().getStringExtra("areaInteres");
        carreasInteres = getIntent().getStringExtra("carreasInteres");

        //Obtiene informacion del alumno

        nombre = getIntent().getStringExtra("nombre");
        grado = getIntent().getStringExtra("grado");
        grupo = getIntent().getStringExtra("grupo");


        textoInforme = "Se realizó la prueba inventario Herrera y Montes, perfil mis intereses y perfil mis aptitudes a "  + nombre.toUpperCase() + " el cual arrojo los siguiente resultados.\n"
                + "\n"
                + "De acuerdo al puntaje obtenido en la prueba de perfil de intereses:\n"
                + "1." + puntajeIntereses + "\n"
                + "\n"
                + "Por otro lado, de acuerdo al puntaje obtenido en la prueba de perfil de aptitudes:\n"
                + "1. " + puntajeAptitudes + "\n"
                //+ "\n"
                //+ "Podría sentirse más cómoda en el área de " + resultados.areaInteres + " donde tiene " + resultados.puntajeIntereses + "\n"
                + "\n"
                + "La mejor opción para " + nombre.toUpperCase() + " de acuerdo a la prueba realizada son las carreras de " + carreasInteres + "\n"
                + "\n"
                + "Cabe mencionar que depende de cada persona la selección de la carrera que quiere estudiar, esta es una prueba estandarizada que puede ayudar a tener un mejor panorama de los intereses y las aptitudes, se recomienda aplicar otra prueba de orientación vocacional para confirmar sus intereses vocacionales.";

        reporte.setText(textoInforme);



        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            if(checkPermission()) {
                Toast.makeText(this, "Permiso Aceptado", Toast.LENGTH_LONG).show();
            } else {
                requestPermissions();
            }
        }

        generar = findViewById(R.id.generarReporte);

        //al presionar este boton
        generar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    createPDF();
                    exit(v);
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }
            }
        });

    }

    private void createPDF() throws FileNotFoundException {

        SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date d = new Date();

        String fecha = dFormat.format(d);

        SimpleDateFormat hFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        Date h = new Date();

        String hora = hFormat.format(h);


        String titulo = "Resultados de test vocacional ";
        String datos = "Nombre del test: Inventario Herrera y Montes" + "\n"
                + "Nombre del alumno: " + nombre.toUpperCase() + "\n"
                + "Grado: " + grado.toUpperCase() + "\n"
                + "Grupo: " + grupo.toUpperCase() + "\n"
                + "Fecha de aplicación de la prueba: " + fecha + ", " + hora;

        String parrafo1 = "Se realizó la prueba inventario Herrera y Montes, perfil mis intereses y perfil mis aptitudes a " + nombre.toUpperCase() + " el cual arrojo los siguiente resultados.";
        String parrafo2 = "De acuerdo al puntaje obtenido en la prueba de perfil de intereses:\n"
                + "1." + puntajeIntereses;
        String parrafo3 = "Por otro lado, de acuerdo al puntaje obtenido en la prueba de perfil de aptitudes:\n"
                + "1. " + puntajeAptitudes;
        String parrafo4 = "La mejor opción para " + nombre.toUpperCase() + " de acuerdo a la prueba realizada son las carreras de " + carreasInteres;
        String parrafo5 = "Cabe mencionar que depende de cada persona la selección de la carrera que quiere estudiar, esta es una prueba estandarizada que puede ayudar a tener un mejor panorama de los intereses y las aptitudes, se recomienda aplicar otra prueba de orientación vocacional para confirmar sus intereses vocacionales.";

        String app = "__________________________________";
        String nombreAplicador = "Nombre y firma del aplicador";

        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(595, 842, 1).create(); // A4 size
        PdfDocument.Page page = document.startPage(pageInfo);
        Canvas canvas = page.getCanvas();

        // Agrega la imagen al documento PDF
        int imageWidth = 100; // Ancho deseado de la imagen
        int imageHeight = 100; // Altura deseada de la imagen
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.log);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, imageWidth, imageHeight, false);
        canvas.drawBitmap(scaledBitmap, (canvas.getWidth() - imageWidth) / 2, 30, null);


        // Configura el estilo de texto
        TextPaint paint = new TextPaint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(12);

        // Dibuja cada párrafo en el PDF
        float yPosition = 170; // Posición inicial

        // Dibuja el título centrado sobre el primer párrafo
        float tituloWidth = paint.measureText(titulo);
        canvas.drawText(titulo, (canvas.getWidth() - tituloWidth) / 2, yPosition, paint);
        yPosition += paint.descent() - paint.ascent() + 10; // Espacio entre el título y el primer párrafo

        // Dibuja los datos debajo del título
        yPosition = drawParagraph(canvas, paint, datos, yPosition, canvas.getWidth() - 60);
        yPosition += 10; // Espacio entre datos y el primer párrafo


        // Agrega los párrafos al documento PDF
        yPosition = drawParagraph(canvas, paint, parrafo1, yPosition, canvas.getWidth() - 60);
        yPosition += 10; // Espacio entre párrafos
        yPosition = drawParagraph(canvas, paint, parrafo2, yPosition, canvas.getWidth() - 60);
        yPosition += 10;
        yPosition = drawParagraph(canvas, paint, parrafo3, yPosition, canvas.getWidth() - 60);
        yPosition += 10;
        yPosition = drawParagraph(canvas, paint, parrafo4, yPosition, canvas.getWidth() - 60);
        yPosition += 10;
        yPosition = drawParagraph(canvas, paint, parrafo5, yPosition, canvas.getWidth() - 60);
        yPosition += 50;

        // Agrega "app" al final de los párrafos y lo centra
        float appWidth = paint.measureText(app);
        canvas.drawText(app, (canvas.getWidth() - appWidth) / 2, yPosition, paint);
        yPosition += paint.descent() - paint.ascent() + 5;

        // Agrega "nombreAplicador" al final sin espacio y lo centra
        float nombreAplicadorWidth = paint.measureText(nombreAplicador);
        canvas.drawText(nombreAplicador, (canvas.getWidth() - nombreAplicadorWidth) / 2, yPosition, paint);


        document.finishPage(page);

        // Guarda el archivo PDF en la carpeta de Descargas
        String fileName = "Informe.pdf";
        File file;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ContentResolver resolver = getContentResolver();
            ContentValues contentValues = new ContentValues();
            contentValues.put(MediaStore.Downloads.DISPLAY_NAME, fileName);
            contentValues.put(MediaStore.Downloads.MIME_TYPE, "application/pdf");
            contentValues.put(MediaStore.Downloads.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS);

            // Inserta un nuevo registro en MediaStore
            try {
                OutputStream outputStream = resolver.openOutputStream(resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues));
                document.writeTo(outputStream);
                outputStream.close();
                Toast.makeText(this, "PDF creado con éxito en Descargas", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error al crear PDFF" + e, Toast.LENGTH_SHORT).show();
            }
        } else {
            // Versiones anteriores a Android 10
            File downloadsFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            file = new File(downloadsFolder, fileName);

            try {
                document.writeTo(new FileOutputStream(file));
                Toast.makeText(this, "PDF creado con éxito en Descargas", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error al crear PDF" + e, Toast.LENGTH_SHORT).show();
            }
        }

        document.close();
    }

    private float drawParagraph(Canvas canvas, TextPaint paint, String paragraph, float yPosition, float maxWidth) {
        String[] lines = paragraph.split("\n");
        for (String line : lines) {
            // Divide la línea en fragmentos que se ajusten al ancho máximo
            String[] words = line.split(" ");
            StringBuilder currentLine = new StringBuilder(words[0]);
            for (int i = 1; i < words.length; i++) {
                if (paint.measureText(currentLine + " " + words[i]) < maxWidth) {
                    currentLine.append(" ").append(words[i]);
                } else {
                    // Justifica y dibuja la línea actual
                    drawJustifiedText(canvas, paint, currentLine.toString(), 30, yPosition, maxWidth);
                    yPosition += paint.descent() - paint.ascent();
                    currentLine = new StringBuilder(words[i]);
                }
            }
            // Justifica y dibuja la última línea del párrafo
            drawJustifiedText(canvas, paint, currentLine.toString(), 30, yPosition, maxWidth);
            yPosition += paint.descent() - paint.ascent();
        }
        return yPosition;
    }

    private void drawJustifiedText(Canvas canvas, TextPaint paint, String text, float x, float y, float maxWidth) {
        float spaceWidth = paint.measureText(" ");
        String[] words = text.split(" ");

        if (words.length == 1 || text.length() < 70) {
            // Si solo hay una palabra o la longitud total es menor a 70 caracteres,
            // dibuja la línea sin justificación
            canvas.drawText(text, x, y, paint);
        } else {
            float totalWidth = 0;

            // Calcula el ancho total de las palabras
            for (String word : words) {
                totalWidth += paint.measureText(word);
            }

            float spaceBetweenWords = (maxWidth - totalWidth) / (words.length - 1);

            // Dibuja las palabras justificadas
            for (int i = 0; i < words.length; i++) {
                canvas.drawText(words[i], x, y, paint);
                x += paint.measureText(words[i]) + spaceBetweenWords;
            }
        }
    }

    public void exit(View view){
        finishAffinity();
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

                            try {
                                createPDF();
                                startActivity(intent);
                            }catch (FileNotFoundException e){
                                e.printStackTrace();
                            }
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

    private boolean checkPermission() {
        int permission1 = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int permission2 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        return permission1 == PackageManager.PERMISSION_GRANTED && permission2 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, 200);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 200) {
            if(grantResults.length > 0) {
                boolean writeStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean readStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                if(writeStorage && readStorage) {
                    Toast.makeText(this, "Permiso concedido", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Permiso denegado", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        }
    }
}