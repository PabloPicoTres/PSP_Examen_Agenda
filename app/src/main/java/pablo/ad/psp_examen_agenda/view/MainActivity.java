package pablo.ad.psp_examen_agenda.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import pablo.ad.psp_examen_agenda.R;
import pablo.ad.psp_examen_agenda.model.entity.Contacto;
import pablo.ad.psp_examen_agenda.view.adapter.ContactoAdapter;
import pablo.ad.psp_examen_agenda.viewmodel.ContactoViewModel;

public class MainActivity extends AppCompatActivity implements OnContactoClickListener{

    private Contacto contactoAux;
    private ContactoViewModel androidViewModel;
    public static final String EXTRA_CONTACTO = "pablo.ad.psp_examen_agenda.view.CONTACTO";
    public static final int ADD_CONTACT_REQUEST_CODE = 1;
    public static final int DETAIL_CONTACT_REQUEST_CODE = 2;
    public static final int RESULT_DELETE = 100;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //requestCode -> actividad
        //resultCode -> OK, CANCEL
        //data -> datos del resultado
        if(requestCode == ADD_CONTACT_REQUEST_CODE) {
            if(resultCode == RESULT_OK) {
                Contacto newContacto = (Contacto) data.getSerializableExtra(EXTRA_CONTACTO);
                androidViewModel.insert(newContacto);
                Toast.makeText(
                        getApplicationContext(),
                        "Contacto guardado",
                        Toast.LENGTH_LONG).show();
            } else if(resultCode == RESULT_CANCELED) {
                Toast.makeText(
                        getApplicationContext(),
                        "Contacto no guardado",
                        Toast.LENGTH_LONG).show();
            }
        }

        if(requestCode == DETAIL_CONTACT_REQUEST_CODE) {
            if(resultCode == RESULT_OK) {
                Contacto newContacto = (Contacto) data.getSerializableExtra(EXTRA_CONTACTO);
                //androidViewModel.update(newContacto); o esto no funciona o lo estoy haciendo mal :(
                androidViewModel.delete(contactoAux);
                androidViewModel.insert(newContacto);
                Toast.makeText(
                        getApplicationContext(),
                        "Actualizado con éxito",
                        Toast.LENGTH_LONG).show();

            } else if(resultCode == RESULT_DELETE) {
                Contacto newContacto = (Contacto) data.getSerializableExtra(EXTRA_CONTACTO);
                androidViewModel.delete(newContacto);
                Toast.makeText(
                        getApplicationContext(),
                        "Contacto borrado",
                        Toast.LENGTH_LONG).show();
            } else if(resultCode == RESULT_CANCELED) {
                /*Toast.makeText(
                        getApplicationContext(),
                        "Cancelado",
                        Toast.LENGTH_LONG).show();*/
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        androidViewModel = new ViewModelProvider(this).get(ContactoViewModel.class);

        init();

    }

    private void init() {

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ContactoAdapter adapter = new ContactoAdapter(new ContactoAdapter.ContactoDiff());
        adapter.setListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        FloatingActionButton fab = findViewById(R.id.fabAdd);
        fab.setOnClickListener( view -> {
            Intent intent = new Intent(MainActivity.this, AddContactoActivity.class);
            startActivityForResult(intent, ADD_CONTACT_REQUEST_CODE);
        });

        androidViewModel.getLiveAgenda().observe(this, new Observer<List<Contacto>>() {
            @Override
            public void onChanged(List<Contacto> Agenda) {
                Log.v("xyz", "onChanged: " + Agenda.toString());
                adapter.submitList(Agenda);
            }
        });
    }

    @Override
    public void contactoClick(Contacto contacto) {
        contactoAux = contacto;
        Intent intent = new Intent(MainActivity.this, DetailContactoActivity.class);
        intent.putExtra(EXTRA_CONTACTO, contacto);
        startActivityForResult(intent, DETAIL_CONTACT_REQUEST_CODE);
    }

}