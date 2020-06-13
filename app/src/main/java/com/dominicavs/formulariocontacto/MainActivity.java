package com.dominicavs.formulariocontacto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.dominicavs.formulariocontacto.dialog.DatePickerFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText etNombre;
    private TextInputLayout tlFecNac;
    private TextInputEditText etFecNac;
    private TextInputEditText etTelefono;
    private TextInputEditText etEmail;
    private TextInputEditText etDesContacto;
    private Button btnNext;
    String nombre, fecha_nacimiento, telefono, email, descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle parametros = getIntent().getExtras();
        nombre = parametros != null ? parametros.getString(getResources().getString(R.string.key_nombre)) : "";
        fecha_nacimiento = parametros != null ? parametros.getString(getResources().getString(R.string.key_fecNac)) : "";
        telefono = parametros != null ? parametros.getString(getResources().getString(R.string.key_telefono)) : "";
        email = parametros != null ? parametros.getString(getResources().getString(R.string.key_email)) : "";
        descripcion = parametros != null ? parametros.getString(getResources().getString(R.string.key_desCont)) : "";

        etNombre = (TextInputEditText) findViewById(R.id.etNombre);
        tlFecNac = (TextInputLayout) findViewById(R.id.tlFecNac);
        tlFecNac.setOnClickListener(this);
        etFecNac = (TextInputEditText) findViewById(R.id.etFecNac);
        etFecNac.setOnClickListener(this);
        etTelefono = (TextInputEditText) findViewById(R.id.etTelefono);
        etEmail = (TextInputEditText) findViewById(R.id.etEmail);
        etDesContacto = (TextInputEditText) findViewById(R.id.etDesContacto);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);

        loadData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tlFecNac:
            case R.id.etFecNac:
                showDatePickerDialog();
                break;
            case R.id.btnNext:
                addData();
                break;
            default:
                break;
        }
    }

    public void addData() {
        Intent i = new Intent(MainActivity.this, ConfirmarDatos.class);
        i.putExtra(getResources().getString(R.string.key_nombre), etNombre.getText().toString());
        i.putExtra(getResources().getString(R.string.key_fecNac), tlFecNac.getEditText().getText().toString());
        i.putExtra(getResources().getString(R.string.key_telefono), etTelefono.getText().toString());
        i.putExtra(getResources().getString(R.string.key_email), etEmail.getText().toString());
        i.putExtra(getResources().getString(R.string.key_desCont), etDesContacto.getText().toString());
        showMessageConfirm("¡Registro exitoso!");
        startActivity(i);
    }

    public void showMessageConfirm(String message) {
        Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show();
    }

    public void showDatePickerDialog() {
        DatePickerFragment pickerFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                final String selectedDate = dayOfMonth + "/" + (month+1) + "/" + year;

                tlFecNac.getEditText().setText(selectedDate);
            }
        });

        pickerFragment.show(getSupportFragmentManager(), "datepicker");
    }

    public void loadData() {
        etNombre.setText(nombre);
        tlFecNac.getEditText().setText(fecha_nacimiento);
        etTelefono.setText(telefono);
        etEmail.setText(email);
        etDesContacto.setText(descripcion);
    }
}
