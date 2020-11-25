package pablo.ad.psp_examen_agenda.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import pablo.ad.psp_examen_agenda.model.ContactoRepository;
import pablo.ad.psp_examen_agenda.model.entity.Contacto;

public class ContactoViewModel extends AndroidViewModel {

    private ContactoRepository repository;
    private LiveData<List<Contacto>> liveContacto;

    public ContactoViewModel(@NonNull Application application) {
        super(application);
        repository = new ContactoRepository(application);
        liveContacto = repository.getLiveAgenda();
    }

    public LiveData<List<Contacto>> getLiveAgenda() {
        return liveContacto;
    }

    public void insert(Contacto contacto) {
        repository.insert(contacto);
    }

    public Contacto getContactoId(int id) {
        return repository.getContactoId(id);
    }

    public void update(Contacto contacto) {
        repository.update(contacto);
    }

    public void delete(Contacto contacto) {
        repository.delete(contacto);
    }
}
