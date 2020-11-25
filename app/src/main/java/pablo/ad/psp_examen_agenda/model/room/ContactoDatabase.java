package pablo.ad.psp_examen_agenda.model.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import pablo.ad.psp_examen_agenda.model.dao.ContactoDao;
import pablo.ad.psp_examen_agenda.model.entity.Contacto;

import static pablo.ad.psp_examen_agenda.ContactoApplication.threadExecutor;


@Database(entities = Contacto.class, version = 1, exportSchema = false)
public abstract class ContactoDatabase extends RoomDatabase {

    public abstract ContactoDao getContactoDao();

    private volatile static ContactoDatabase INSTANCE;

    static synchronized ContactoDatabase getDb(final Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    ContactoDatabase.class, "ContactosDataBase")
                    .build();
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            threadExecutor.execute(() -> {
                ContactoDao dao = INSTANCE.getContactoDao();
                dao.deleteAll();

                //INICIALIZAR O HACER PRUEBAS CON VALORES INICIALES EN LA BASE DE DATOS
                /*Contacto contacto = new Contacto("Miguel", "Revilla", 666000112,"","","","");
                dao.insert(contacto);
                contacto = new Contacto("Falete", "", 666000113,"","","","");
                dao.insert(contacto);*/
            });
        }
    };

    public static ContactoDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ContactoDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE =  Room.databaseBuilder(context.getApplicationContext(),
                                ContactoDatabase.class, "ContactosDataBase")
                                    .addCallback(callback)
                                    .build();
                }
            }
        }
        return INSTANCE;
    }

}
