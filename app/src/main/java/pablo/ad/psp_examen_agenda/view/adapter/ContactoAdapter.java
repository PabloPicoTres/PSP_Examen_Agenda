package pablo.ad.psp_examen_agenda.view.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import pablo.ad.psp_examen_agenda.model.entity.Contacto;
import pablo.ad.psp_examen_agenda.view.OnContactoClickListener;

public class ContactoAdapter extends ListAdapter<Contacto, ContactoViewHolder> {

    private OnContactoClickListener listener;

    public ContactoAdapter(@NonNull DiffUtil.ItemCallback<Contacto> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ContactoViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder holder, int position) {
        Contacto current = getItem(position);
        holder.bind(current.getNombre(),
                    current.getApellidos(),
                    String.valueOf(current.getTelefono()),
                    current.getLocalidad());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.contactoClick(current);
            }
        });

    }


    public void setListener(OnContactoClickListener listener) {
        this.listener = listener;
    }

    public static class ContactoDiff extends DiffUtil.ItemCallback<Contacto> {

        @Override
        public boolean areItemsTheSame(@NonNull Contacto oldItem, @NonNull Contacto newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Contacto oldItem, @NonNull Contacto newItem) {
            return oldItem.getNombre().equals(newItem.getNombre());
        }
    }

}
