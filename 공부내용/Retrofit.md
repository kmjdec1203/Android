# Retrofit

## build.gradle 추가하기

```java
Module: app


dependencies {
    ....
implementation 'com.squareup.retrofit2:retrofit:2.6.1'
implementation 'com.squareup.retrofit2:converter-gson:2.6.1'
    ....
}
```



## Manifest Permission 등록

```java
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.retrofitexample">

    <uses-permission android:name="android.permission.INTERNET"/>
    
    ...
    
</manifest>
```

internet을 사용하므로 internet permission을 추가해준다.



## Retrofit 객체 생성 & ApiService 활용 클래스 생성

```java
public class RetrofitClient {
    //객체생성
    Retrofit retrofit = new Retrofit.Builder()
            //서버 url설정    
            .baseUrl(TaskServer.ip)
            //데이터 파싱 설정
            .addConverterFactory(GsonConverterFactory.create())
            //객체정보 반환
            .build();
 
    public ApiService apiService = retrofit.create(ApiService.class);
}
```

* 서버 URL을 설정하고 데이터 파싱 및 객체 정보를 반환할 수 있는 Retrofit 객체를 하나 생성
* ApiService를 활용할 수 있는 클래스도 하나 생성해 준다.



## Retrofit Interface 구성

```java
ApiService.java

public interface ApiService {
   @GET("/통신하기 위한/Api Server 주소")
   Call<JsonArray> getretrofitdata();

}
```

* Call 을 통해서 웹서버에 요청을 보낼 수 있다.

1. Http요청을 어노테이션으로 명시

2. URL Parameter와 Query Parameter 사용이 가능하다.

 ex) @GET("/group/{id}/users") Call<List<User>> groupList(@Path("id") int groupId, @Query("sort") String sort)

3. 객체는 Body 로 json형태로 전달한다.

   url 끝에 / 를 빼먹으면 error 가 발생할 수 있으니 유의해야 한다.



## MainActivity

```java
RetrofitClient retrofitClient = new RetrofitClient();
     Call<JsonArray> call = retrofitClient.apiService.getretrofitdata();
     call.enqueue(new Callback<JsonArray>() {
         @Override
         public void onResponse(Call<JsonArray> call,Response<JsonArray> response) {
                if (response.isSuccessful()) {
                }
            }
         @Override
         public void onFailure(Call<JsonArray> call, Throwable t) {
         }
     });

```

통신에 성공할 경우 onResponse()로, 실패한 경우 onFailure()로 들어간다.



## Data 객체 정의 (데이터 파싱)

```java
package com.example.retrofitexample;

public class PostItem {
    private String title;
    private String text;

    public String getText() {
        return text;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String s){
        title = s;
    }

    public void setText(String s){
        text = s;
    }
}
```

String 타입의 데이터를 사용하기 쉽도록 바꿔야 한다.

server의 model에서 구성했던 것과 동일하게 구성해준다.

* 응답 받을 데이터 구조와 같은 구조이어야 하며 변수 명도 같아야 한다.





