package pablo.ad.psp_examen_agenda.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import pablo.ad.psp_examen_agenda.model.entity.Contacto;

@Dao
public interface ContactoDao {

   /*
   * insertar un nuevo registro
   * editar un registro
   * borrar un registro
   * borrar todos los registros
   * listar todos los registros (LiveData)
   * listar todos los registros (ArrayList)
   * listar un registro (necesita id)
   */


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Contacto contacto);

    @Update
    void update(Contacto contacto);

    @Delete
    void delete(Contacto contacto);

    @Query("delete from contacto")
    void deleteAll();

    @Query("select * from contacto order by nombre")
    LiveData<List<Contacto>> getAllLive();

    @Query("select * from contacto order by nombre")
    List<Contacto> getAll();

    @Query("select * from contacto where id = :id")
    Contacto getById(int id);

}
