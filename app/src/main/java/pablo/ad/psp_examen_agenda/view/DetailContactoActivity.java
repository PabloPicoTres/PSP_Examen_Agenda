package pablo.ad.psp_examen_agenda.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Pattern;

import pablo.ad.psp_examen_agenda.R;
import pablo.ad.psp_examen_agenda.model.entity.Contacto;

import static pablo.ad.psp_examen_agenda.view.MainActivity.EXTRA_CONTACTO;

public class DetailContactoActivity extends AppCompatActivity {

    private Intent intent;

    private TextInputEditText tietNombre;
    private TextInputEditText tietApellidos;
    private TextInputEditText tietTelefono;
    private TextInputEditText tietNacimiento;
    private TextInputEditText tietLocalidad;
    private TextInputEditText tietCalle;
    private TextInputEditText tietNumero;

    private Button btCancelar;
    private Button btGuardar;
    private Button btBorrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_contacto);

        init();

    }

    private void init() {
        //iniciamos el contacto a sus valores
        intent = getIntent();
        Contacto contacto = (Contacto) intent.getSerializableExtra(MainActivity.EXTRA_CONTACTO);

        btBorrar = findViewById(R.id.btBorrar);
        btGuardar = findViewById(R.id.btEdit);
        btCancelar = findViewById(R.id.btCancel);

        tietNombre = findViewById(R.id.tietNombreD);
        tietApellidos = findViewById(R.id.tietApellidosD);
        tietTelefono = findViewById(R.id.tietTelefonoD);
        tietNacimiento = findViewById(R.id.tietNacimientoD);
        tietLocalidad = findViewById(R.id.tietLocalidadD);
        tietCalle = findViewById(R.id.tietCalleD);
        tietNumero = findViewById(R.id.tietNumeroD);

        tietNombre.setText(contacto.getNombre());
        tietApellidos.setText(contacto.getApellidos());
        tietTelefono.setText(String.valueOf(contacto.getTelefono()));
        tietNacimiento.setText(contacto.getNacimiento());
        tietLocalidad.setText(contacto.getLocalidad());
        tietCalle.setText(contacto.getCalle());
        tietNumero.setText(contacto.getNumero());


        //hacemos el onclick de los 3 botones
        btBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                replyIntent.putExtra(EXTRA_CONTACTO, contacto);
                setResult(MainActivity.RESULT_DELETE, replyIntent);
                finish();
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

        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( validarCampos() ) {
                    creaContacto();
                }
            }
        });

    }

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