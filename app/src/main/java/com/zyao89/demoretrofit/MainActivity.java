package com.zyao89.demoretrofit;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
{
    private Handler mHandler = new Handler();
    private RetrofitService mRetrofitService;
    private TextView mLogCat;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLogCat = (TextView) findViewById(R.id.logcat);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://200.200.200.182:9999/")
                //增加返回值为Gson的支持(以实体类返回)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mRetrofitService = retrofit.create(RetrofitService.class);
    }

//    public void login(View view)
//    {
////        User user = new User("admin", "123456");
////        Call<ResponseBody> login = mRetrofitService.login(user);
//        Call<ResponseBody> login = mRetrofitService.login("bacdd", "333444");
//        login.enqueue(getCallback());
//    }

    public void login(View view)
    {
        Call<ResponseBody> login = mRetrofitService.login("login", "admin", "123444");
        login.enqueue(getCallback());
    }

    public void updateUser(View view)
    {
        Call<ResponseBody> updateUser = mRetrofitService.updateUser("angular", "988988");
        updateUser.enqueue(getCallback());
    }

    public void put(View view) {
        User user = new User("admin", "123456");
        Call<ResponseBody> put = mRetrofitService.put(user);
        put.enqueue(getCallback());
    }

    public void get(View view) {
        Call<ResponseBody> get = mRetrofitService.get(1);
        get.enqueue(getCallback());
    }

    public void getUser(View view) {
        Call<User> getUser = mRetrofitService.getUser(5);
        getUser.enqueue(this.<User>getBodyCallback());
    }

    public void query(View view) {
        Call<ResponseBody> get = mRetrofitService.query(4);
        get.enqueue(getCallback());
    }

    public void delete(View view) {
        DeleteID deleteID = new DeleteID(3);
        Call<ResponseBody> delete = mRetrofitService.delete(deleteID);
        delete.enqueue(getCallback());
    }

    private <T> Callback<T> getBodyCallback()
    {
        return new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response)
            {
                printLogCat("Code: " + response.code());
                printLogCat("Message: " + response.message());
                if (response.isSuccessful())
                {
                    T user = response.body();
                    printLogCat("Body: " + user.toString());
                }
                printLogCat("");
            }

            @Override
            public void onFailure(Call<T> call, Throwable t)
            {
                printLogCat("ERROR: " + t.getMessage());
                printLogCat("");
            }
        };
    }

    private Callback<ResponseBody> getCallback()
    {
        return new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
            {
                printLogCat("Code: " + response.code());
                printLogCat("Message: " + response.message());
                printLogCat("isSuccessful: " + response.isSuccessful());
                try
                {
                    printLogCat("Body: " + response.body().string());
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                printLogCat("");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t)
            {
                printLogCat("ERROR: " + t.getMessage());
                printLogCat("");
            }
        };
    }

    private void printLogCat(Object... texts)
    {
        for (Object text : texts)
        {
            mLogCat.append(text + "\n");
        }
        mHandler.post(new Runnable() {
            @Override
            public void run()
            {
                ScrollView scrollView = (ScrollView) mLogCat.getParent();
                scrollView.fullScroll(ScrollView.FOCUS_DOWN);//滚动到底部
            }
        });
    }

    public void clear(View view)
    {
        mLogCat.setText("");
    }

}
