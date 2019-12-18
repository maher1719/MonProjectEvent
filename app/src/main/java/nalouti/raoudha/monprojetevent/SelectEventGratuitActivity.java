package nalouti.raoudha.monprojetevent;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectEventGratuitActivity extends AppCompatActivity {

    ApiInterface mService;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<EventData> events;
    private RecyclerAdapterEventGratuit adapterEventGratuit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_event_gratuit);
        mService = Common.getAPI();
        recyclerView = findViewById(R.id.recylerviewGratuit);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        String montant = "gratuit";
        fetchInformation(montant);

    }

    private void fetchInformation(String montant) {
        Call<List<EventData>> call = mService.selectGratuit(montant);
        call.enqueue(new Callback<List<EventData>>() {
            @Override
            public void onResponse(Call<List<EventData>> call, Response<List<EventData>> response) {
                events = response.body();
                adapterEventGratuit = new RecyclerAdapterEventGratuit(events, SelectEventGratuitActivity.this);
                recyclerView.setAdapter(adapterEventGratuit);


            }

            @Override
            public void onFailure(Call<List<EventData>> call, Throwable t) {
            }
        });


    }
}
