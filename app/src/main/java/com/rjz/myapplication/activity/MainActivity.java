package com.rjz.myapplication.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.rjz.myapplication.R;
import com.rjz.myapplication.adapter.AllPostAdapter;
import com.rjz.myapplication.model.PostModel;
import com.rjz.myapplication.retro.RetrofitAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView recyclerView;
    private ProgressDialog pd;

    void showProgressDialog() {
        if (pd == null) {
            pd = new ProgressDialog(MainActivity.this);
            pd.setMessage("Loading....");
            pd.show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_id);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        showProgressDialog();
        RetrofitAPI.getServiceClass().getAllPost().enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                hideProgressDialog();
                if (response.isSuccessful()) {
                    List<PostModel> postList = response.body();
                    Log.e(TAG, "Total List size : " + postList.size());
                    AllPostAdapter adapter = new AllPostAdapter(getApplicationContext(), postList);
                    recyclerView.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                hideProgressDialog();
                Log.e(TAG, "error loading from API");
                Toast.makeText(MainActivity.this, "Error loading from API", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void hideProgressDialog() {
        if (pd != null)
            pd.dismiss();
    }
}
