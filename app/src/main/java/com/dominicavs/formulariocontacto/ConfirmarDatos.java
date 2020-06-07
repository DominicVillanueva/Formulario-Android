package com.dominicavs.formulariocontacto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmarDatos extends AppCompatActivity implements View.OnClickListener {

    private TextView tvNombre;
    private TextView tvFecNac;
    private TextView tvTelefono;
    private TextView tvEmail;
    private TextView tvDescripcion;
    private Button btnEditData;
    String nombre, fecha_nacimiento, telefono, email, descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_datos);

        Bundle parametros = getIntent().getExtras();
        nombre = parametros.getString(getResources().getString(R.string.key_nombre));
        fecha_nacimiento = parametros.getString(getResources().getString(R.string.key_fecNac));
        telefono = parametros.getString(getResources().getString(R.string.key_telefono));
        email = parametros.getString(getResources().getString(R.string.key_email));
        descripcion = parametros.getString(getResources().getString(R.string.key_desCont));

        tvNombre = (TextView) findViewById(R.id.tvNombre);
        tvNombre.setText(nombre);
        tvFecNac = (TextView) findViewById(R.id.tvFecNac);
        tvFecNac.setText(fecha_nacimiento);
        tvTelefono = (TextView) findViewById(R.id.tvTelefono);
        tvTelefono.setText(telefono);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvEmail.setText(email);
        tvDescripcion = (TextView) findViewById(R.id.tvDescripcion);
        tvDescripcion.setText(descripcion);
        btnEditData = (Button) findViewById(R.id.btnEditData);
        btnEditData.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnEditData:
                goToFormContacto();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            goToFormContacto();
        }
        return super.onKeyDown(keyCode, event);
    }

    public void goToFormContacto() {
        Intent i = new Intent(ConfirmarDatos.this, MainActivity.class);
        i.putExtra(getResources().getString(R.string.key_nombre), nombre);
        i.putExtra(getResources().getString(R.string.key_fecNac), fecha_nacimiento);
        i.putExtra(getResources().getString(R.string.key_telefono), telefono);
        i.putExtra(getResources().getString(R.string.key_email), email);
        i.putExtra(getResources().getString(R.string.key_desCont), descripcion);
        startActivity(i);
        finish();
    }
}
