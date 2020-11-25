package pablo.ad.psp_examen_agenda.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import pablo.ad.psp_examen_agenda.R;

public class ContactoViewHolder extends RecyclerView.ViewHolder{
    private final TextView tvNombre;
    private final TextView tvApellidos;
    private final TextView tvTelefono;
    private final TextView tvLocalidad;

    public ContactoViewHolder(@NonNull View itemView) {
        super(itemView);
        this.tvNombre = itemView.findViewById(R.id.tvNombre);
        this.tvApellidos = itemView.findViewById(R.id.tvApellidos);
        this.tvTelefono = itemView.findViewById(R.id.tvTelefono);
        this.tvLocalidad = itemView.findViewById(R.id.tvLocalidad);
    }

    //aquí se le asigna un valor al textview
    public void bind(String nombre, String apellidos, String telefono, String localidad) {
        tvNombre.setText(nombre);
        tvApellidos.setText(apellidos);
        tvTelefono.setText(telefono);
        tvLocalidad.setText(localidad);
    }

    //aquí se crea un layout del tipo item (inflate)
    static ContactoViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contacto, parent, false);
        return new ContactoViewHolder(view);
    }
}
