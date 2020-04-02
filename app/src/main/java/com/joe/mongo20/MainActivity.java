package com.joe.mongo20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;

public class MainActivity extends AppCompatActivity {
    private TextView nameText;
    private TextView emailText;
    private TextView mobileText;
    private TextView ageText;
    private TextView errorText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameText = findViewById(R.id.nameq);
        emailText = findViewById(R.id.emailq);
        mobileText = findViewById(R.id.mobileq);
        ageText = findViewById(R.id.edadq);
        errorText = findViewById(R.id.errorq);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://altrest.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        restApi restApi = retrofit.create(com.joe.mongo20.restApi.class);
        Call<List<post>> call = restApi.getPosts();

        call.enqueue(new Callback<List<post>>() {
            @Override
            public void onResponse(Call<List<post>> call, Response<List<post>> response) {
                if (!response.isSuccessful()){
                    errorText.setText("Code: " + response.code());
                    return;
                }
                List<post> posts = response.body();

                for (post post : posts){
                    String nameContent = "";
                    String emailContent = "";
                    String mobileContent = "";



                    nameContent+=  post.getPulso();
                    emailContent+= post.getDate();
                    mobileContent+= post.getSensor();


                    nameText.append(nameContent);
                    emailText.append(emailContent);
                    mobileText.append(mobileContent);

                }


            }

            @Override
            public void onFailure(Call<List<post>> call, Throwable t) {
                errorText.setText(t.getMessage());
            }
        });

    }
}
