package nalouti.raoudha.monprojetevent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecyclerAdapterEventGratuit extends RecyclerView.Adapter<RecyclerAdapterEventGratuit.MyViewHolder> {

    private List<EventData> events;
    private Context context;

    public RecyclerAdapterEventGratuit(List<EventData> events, Context context) {
        this.events = events;
        this.context = context;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_gratuit, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.titre.setText(events.get(position).getTitre());
        holder.dateDeb.setText(events.get(position).getDateDebEvent());
        holder.datefin.setText(events.get(position).getDateFin());
        Glide.with(context).load(events.get(position).getImgEvent()).into(holder.img);


    }

    @Override
    public int getItemCount() {
        return events.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView titre, dateDeb, datefin;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgEvt);
            titre = itemView.findViewById(R.id.titreEvt);
            dateDeb = itemView.findViewById(R.id.dateDebEvent);
            datefin = itemView.findViewById(R.id.dateFinEvent);

        }
    }
}
