package pablo.ad.psp_examen_agenda.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Pattern;

import pablo.ad.psp_examen_agenda.R;
import pablo.ad.psp_examen_agenda.model.entity.Contacto;

import static pablo.ad.psp_examen_agenda.view.MainActivity.EXTRA_CONTACTO;

public class AddContactoActivity extends AppCompatActivity {



    private TextInputEditText tietNombre;
    private TextInputEditText tietApellidos;
    private TextInputEditText tietTelefono;
    private TextInputEditText tietNacimiento;
    private TextInputEditText tietLocalidad;
    private TextInputEditText tietCalle;
    private TextInputEditText tietNumero;
    private Button btCancelar;
    private Button btAceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contacto);

        btCancelar = findViewById(R.id.btCancelar);
        btAceptar = findViewById(R.id.btAceptar);

        tietNombre = findViewById(R.id.tietNombre);
        tietApellidos = findViewById(R.id.tietApellidos);
        tietTelefono = findViewById(R.id.tietTelefono);
        tietNacimiento = findViewById(R.id.tietNacimiento);
        tietLocalidad = findViewById(R.id.tietLocalidad);
        tietCalle = findViewById(R.id.tietCalle);
        tietNumero = findViewById(R.id.tietNumero);

        init();
    }

    private void init() {
        btAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( validarCampos() ) {
                    creaContacto();
                }

            }
        });

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                setResult(RESULT_CANCELED, replyIntent);
                finish();
            }
        });
    }


    //Es correcto si tiene rellenos los campos vacíos y el de telefono solo tiene numeros
    private boolean validarCampos() {
        boolean result = true;

        Pattern soloNumeros = Pattern.compile("\\d+");
        if(tietNombre.getText().toString().equals("") || tietTelefono.getText().toString().equals("") || (!soloNumeros.matcher(tietTelefono.getText().toString()).matches()) ){
            result = false;
            Toast.makeText(this, "Introduzca los campos correctamente", Toast.LENGTH_SHORT).show();
        }

        return result;
    }

    private void creaContacto() {

        Contacto newContacto = new Contacto(
                tietNombre.getText().toString(),
                tietApellidos.getText().toString(),
                Integer.parseInt(tietTelefono.getText().toString()),
                tietNacimiento.getText().toString(),
                tietLocalidad.getText().toString(),
                tietCalle.getText().toString(),
                tietNumero.getText().toString()
        );
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_CONTACTO, newContacto);
        setResult(RESULT_OK, replyIntent);
        finish();

    }
}