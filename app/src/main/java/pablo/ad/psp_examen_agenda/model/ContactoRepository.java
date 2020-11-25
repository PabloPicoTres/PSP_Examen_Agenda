package pablo.ad.psp_examen_agenda.model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import pablo.ad.psp_examen_agenda.model.dao.ContactoDao;
import pablo.ad.psp_examen_agenda.model.entity.Contacto;
import pablo.ad.psp_examen_agenda.model.room.ContactoDatabase;

import static pablo.ad.psp_examen_agenda.ContactoApplication.threadExecutor;

public class ContactoRepository {

    private ContactoDatabase db;
    private ContactoDao contactoDao;


    private LiveData<List<Contacto>> liveAgenda;


    public ContactoRepository(Application context) {
        db = ContactoDatabase.getDatabase(context);
        contactoDao = db.getContactoDao();

        //LiveData: carga de datos en segundo plano
        liveAgenda = contactoDao.getAllLive();
    }


    public LiveData<List<Contacto>> getLiveAgenda() {
        return liveAgenda;
    }


    public void insert(Contacto contacto) {

        threadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                contactoDao.insert(contacto);
            }
        });
    }

    public Contacto getContactoId(int id) {
        final Contacto[] contacto = new Contacto[1];
        threadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                contacto[0] = contactoDao.getById(id);
            }
        });
        return contacto[0];
    }

    public void update(Contacto contacto) {

        threadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                contactoDao.update(contacto);
            }
        });
    }

    public void delete(Contacto contacto) {

        threadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                contactoDao.delete(contacto);
            }
        });
    }

}
