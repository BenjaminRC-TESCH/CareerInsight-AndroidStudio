package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Datos extends AppCompatActivity {

    EditText nombreEditText, edadEditText, gradoEditText, grupoEditText, especialidadEditText;
    Button aceptarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        // Inicializa los componentes
        nombreEditText = findViewById(R.id.editTextText4);
        edadEditText = findViewById(R.id.editTextNumber3);
        gradoEditText = findViewById(R.id.editTextNumber4);
        grupoEditText = findViewById(R.id.editTextText5);
        especialidadEditText = findViewById(R.id.editTextText6);
        aceptarButton = findViewById(R.id.aceptar);

        // Agrega los filtros de entrada y los oyentes de texto
        setupEditTextFilters();
        setupTextChangeListeners();

        aceptarButton.setEnabled(false);

        aceptarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Datos.this, Instrucciones.class);
                i.putExtra("nombre",nombreEditText.getText().toString());
                i.putExtra("grado",gradoEditText.getText().toString());
                i.putExtra("grupo",grupoEditText.getText().toString());
                startActivity(i);
            }
        });
    }

    private void setupEditTextFilters() {
        // Filtra para permitir letras y espacios en el campo de nombre
        nombreEditText.setFilters(new InputFilter[]{
                new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence source, int start, int end,
                                               Spanned dest, int dstart, int dend) {
                        if (TextUtils.isEmpty(source)) {
                            return source;
                        }
                        if (source.toString().matches("[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\\s]+")) {
                            return source;
                        }
                        return "";
                    }
                }
        });

        // Filtra para permitir solo dos dígitos en el campo de edad
        edadEditText.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(2),
                new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence source, int start, int end,
                                               Spanned dest, int dstart, int dend) {
                        if (TextUtils.isEmpty(source)) {
                            return source;
                        }
                        if (source.toString().matches("\\d{1,2}")) {
                            return source;
                        }
                        return "";
                    }
                }
        });

        // Filtra para permitir solo dos dígitos en el campo de edad
        gradoEditText.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(1),
                new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence source, int start, int end,
                                               Spanned dest, int dstart, int dend) {
                        if (TextUtils.isEmpty(source)) {
                            return source;
                        }
                        if (source.toString().matches("\\d{1,2}")) {
                            return source;
                        }
                        return "";
                    }
                }
        });

        // Filtra para permitir solo un dígito en el campo de grupo
        grupoEditText.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(1),
                new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence source, int start, int end,
                                               Spanned dest, int dstart, int dend) {
                        if (TextUtils.isEmpty(source)) {
                            return source;
                        }
                        if (source.toString().matches("[A-Za-z]+")) {
                            return source;
                        }
                        return "";
                    }
                }
        });

        especialidadEditText.setFilters(new InputFilter[]{
                new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence source, int start, int end,
                                               Spanned dest, int dstart, int dend) {
                        if (TextUtils.isEmpty(source)) {
                            return source;
                        }
                        if (source.toString().matches("[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\\s]+")) {
                            return source;
                        }
                        return "";
                    }
                }
        });
    }

    private void setupTextChangeListeners() {
        // Agrega un oyente de texto a cada campo
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Verifica si todos los campos están llenos para habilitar o deshabilitar el botón
                boolean camposLlenos = !TextUtils.isEmpty(nombreEditText.getText().toString()) &&
                        !TextUtils.isEmpty(edadEditText.getText().toString()) &&
                        !TextUtils.isEmpty(gradoEditText.getText().toString()) &&
                        !TextUtils.isEmpty(grupoEditText.getText().toString()) &&
                        !TextUtils.isEmpty(especialidadEditText.getText().toString());

                aceptarButton.setEnabled(camposLlenos);
            }
        };

        // Agrega el oyente de texto a cada campo
        nombreEditText.addTextChangedListener(textWatcher);
        edadEditText.addTextChangedListener(textWatcher);
        gradoEditText.addTextChangedListener(textWatcher);
        grupoEditText.addTextChangedListener(textWatcher);
        especialidadEditText.addTextChangedListener(textWatcher);
    }
}
