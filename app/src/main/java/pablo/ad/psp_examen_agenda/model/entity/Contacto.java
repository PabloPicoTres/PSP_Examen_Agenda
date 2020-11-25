package pablo.ad.psp_examen_agenda.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.sql.Date;

@Entity(tableName = "contacto")
public class Contacto implements Serializable {

    /*
    tabla: contacto
    campos:     id integer autonumeric primary key,
                nombre varchar not null,
                apellidos varchar ,
                teléfono integer not null,
                fecha de nacimiento varchar,
                localidad varchar,
                calle varchar,
                número varchar
    */

    //-------------- CAMPOS -----------------//

    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    @ColumnInfo(name = "nombre")
    private String nombre;


    @ColumnInfo(name = "apellidos")
    private String apellidos;

    @NonNull
    @ColumnInfo(name = "telefono")
    private int telefono;

    @ColumnInfo(name = "nacimiento")
    private String nacimiento;


    @ColumnInfo(name = "localidad")
    private String localidad;


    @ColumnInfo(name = "calle")
    private String calle;


    @ColumnInfo(name = "numero")
    private String numero;

    //------------ CONSTRUCTORES -------------//


    public Contacto() {
        this("", "", 0, null, "", "", "");
    }

    public Contacto( @NonNull String nombre, String apellidos, int telefono, String nacimiento, String localidad, String calle, String numero ) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.nacimiento = nacimiento;
        this.localidad = localidad;
        this.calle = calle;
        this.numero = numero;
    }



    //---------- GETTERS Y SETTERS -----------//

    public int getId() {
        return id;
    }

    @NonNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    @NonNull
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
