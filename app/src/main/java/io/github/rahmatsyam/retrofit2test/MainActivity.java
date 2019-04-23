package io.github.rahmatsyam.retrofit2test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

import io.github.rahmatsyam.retrofit2test.adapter.AnswersAdapter;
import io.github.rahmatsyam.retrofit2test.data.model.Item;
import io.github.rahmatsyam.retrofit2test.data.model.SOAnswersResponse;
import io.github.rahmatsyam.retrofit2test.data.remote.RetrofitClient;
import io.github.rahmatsyam.retrofit2test.data.remote.SOService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private AnswersAdapter mAdapter;
    RecyclerView mRecylerView;
    private SOService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mService = ApiUtils.getSOService();
        mRecylerView = findViewById(R.id.rv_answers);

        mAdapter = new AnswersAdapter(this, new ArrayList<Item>(0), new AnswersAdapter.PostItemListener() {

            @Override
            public void onPostClick(long id) {
                Toast.makeText(MainActivity.this, "Post id is" + id, Toast.LENGTH_SHORT).show();
            }


        });


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecylerView.setLayoutManager(layoutManager);
        mRecylerView.setAdapter(mAdapter);
        mRecylerView.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mRecylerView.addItemDecoration(itemDecoration);

        loadAnswers();

    }

    public void loadAnswers() {
        RetrofitClient.getAnswer(new Callback<SOAnswersResponse>() {
            @Override
            public void onResponse(Call<SOAnswersResponse> call, Response<SOAnswersResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mAdapter.updateAnswers(response.body().getItems());
                } else {
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<SOAnswersResponse> call, Throwable t) {
                showErrorMessage();
            }
        });


    }

    public void showErrorMessage() {
        Toast.makeText(this, "Error loading posts", Toast.LENGTH_SHORT).show();
    }


}