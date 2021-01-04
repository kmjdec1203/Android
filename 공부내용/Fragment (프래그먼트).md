# Fragment (프래그먼트)

## Fragment 란?

* Fragment는 동작 또는 Activity내에서 사용자 인터페이스의 일부를 나타낸다. 
* 여러 개의 프래그먼트를 하나의 액티비티에 조합하여 창이 여러 개인 UI를 구축할 수 있으며, 하나의 프래그먼트를 여러 액티비티에서 재사용할 수 있다.
* 자체 수명 주기를 가지고, 자체 입력 이벤트를 받으며, 액티비티 실행 중에 추가 및 제거가 가능한 액티비티의 모듈식 섹션. (하위 액티비티와 같은 개념)

---

## 장점, 사용 목적, 특징

1. 분할된 화면들을 독립적으로 구성하기 위해, 상태를 관리하기 위해 사용됨
2. 액티비티는 안드로이드 시스템에서 관리하는 화면, 프래그먼트는 단순히 액티비티 위에 올라가는 '부분 화면'
3. 액티비티를 관리하는 시스템의 모듈 = 액티비티 매니저, 데이터 전달 인텐트로.
4. 프래그먼트 관리 = 프래그먼트 매니저, 데이터 전달 단순히 메소드 생성, 호출 방식으로. (인터페이스를 사용하면 관리하기 더 좋음)
5. 전체화면으로도 사용할 수 있음 (액티비티에 뷰를 프래그먼트로만 해놓으면 됨)
6. 액티비티는 그대로 있고 프래그먼트만 전환되어 시스템이 직접 관리하지 않기 때문에 가볍다.



## 프래그먼트를 사용하는 예

* 카카오톡

  ![img](https://scontent.xx.fbcdn.net/v/t1.15752-0/s320x320/135815407_312413196765169_7982466953916050244_n.jpg?_nc_cat=106&ccb=2&_nc_sid=58c789&_nc_ohc=LRgmP8jqv7kAX_wx6o0&_nc_oc=AQkpwsg5a6UgHtldGZnWE8XDjUumo99VYb8V-eeAPsQxr9PcUcOcawUSjGX3DL_q9MShk2tFEDIRmXJ9n_OweC6u&_nc_ad=z-m&_nc_cid=0&_nc_ht=scontent.xx&tp=7&oh=37bc873b3c71916f85a6411f4082449a&oe=6016FD7C)

  밑에 탭화면은 그대로 있고 부분적인 화면만 전환되면 된다.



## 프래그먼트 예제

* MainActivity.class

```java
public class MainActivity extends AppCompatActivity {
    
    // 프래그먼트는 xml 레이아웃 파일 하나랑 자바소스 파일 하나로 정의할 수 있음
    // 액티비티를 본떠 만들었으므로 프래그먼트 매니저가 소스코드에서 담당함
    MainFragment fragment1;
    MenuFragment fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // 프래그먼트는 뷰와 다르게 context를 매개변수로 넣어줄 필요가 없음
        fragment1 = new MainFragment();
        fragment2 = new MenuFragment();

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();	// 트랜잭션 안에서 수행되는 것이므로 마지막에 꼭 commit을 해줘야 실행됨
            }
        });

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).commit();
            }
        });
    }
    
    // 프래그먼트와 프래그먼트끼리 직접 접근을 허용하지 않는다. 프래그먼트와 액티비티가 접근함
    public void onFragmentChange(int index) {
        if(index == 0) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();
        } else if(index == 1) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).commit();
        }
    }
}
```

* MainFragment

```java
// 프래그먼트는 액티비티 위에 올라가 있을 때만 프래그먼트로서 동작할 수 있다.
public class MainFragment extends Fragment {

    MainActivity activity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        // 이제 더이상 엑티비티 참조 안 됨
        activity = null;
    }

    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 프래그먼트 메인을 인플레이트 해주고 컨테이너에 붙여달라는 뜻
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);
        Button button = rootView.findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onFragmentChange(1); // 메뉴로 넘어가는 건 1
            }
        });
        return rootView;
    }
}
```

* MenuFragment

```java
public class MenuFragment extends Fragment {

    MainActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        // 이제 더이상 엑티비티 참조 안 됨
        activity = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 프래그먼트 메인을 인플레이트 해주고 컨테이너에 붙여달라는 뜻
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_menu, container, false);

        Button button = rootView.findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onFragmentChange(0); // 메인으로 넘어가는 건 0
            }
        });
        return rootView;
    }
}
```

---

## 실행화면

![img](https://scontent.xx.fbcdn.net/v/t1.15752-0/s320x320/134738709_231731231796548_6132444211900461755_n.jpg?_nc_cat=107&ccb=2&_nc_sid=58c789&_nc_ohc=uBuYWTahwgYAX8Xe5Zi&_nc_ad=z-m&_nc_cid=0&_nc_ht=scontent.xx&tp=7&oh=62091ca7be82b2fc664def505a418e41&oe=601835EB)![img](https://scontent.xx.fbcdn.net/v/t1.15752-0/s320x320/134242799_307633117338250_7263726536841932750_n.jpg?_nc_cat=106&ccb=2&_nc_sid=58c789&_nc_ohc=WC862r0slewAX-Mwf2P&_nc_ad=z-m&_nc_cid=0&_nc_ht=scontent.xx&tp=7&oh=d2843a1828f7292a311f4261cc302620&oe=6018432F)![img](https://scontent.xx.fbcdn.net/v/t1.15752-0/s320x320/134078264_201740474940505_7913019410538443498_n.jpg?_nc_cat=109&ccb=2&_nc_sid=58c789&_nc_ohc=GKGgzKSA27MAX_IeZvL&_nc_ad=z-m&_nc_cid=0&_nc_ht=scontent.xx&tp=7&oh=008f30bb693c2a489fe54bfb04cc4991&oe=6017F08E)

* ```메인``` 버튼을 클릭하면 2번째의 파란 창(메인 프래그먼트)이 뜬다.

* ```메뉴``` 버튼을 클릭하면 3번째의 보라색 창(메뉴 프래그먼트)이 뜬다.

* 메인 프래그먼트에서 ```메뉴 화면으로``` 라는 버튼을 클릭하면 메뉴 프래그먼트가,

  메뉴 프래그먼트에서 ```메인 화면으로``` 라는 버튼을 클릭하면 메인 프래그먼트가 뜬다.

* 위에 ```메인``` 과 ```메뉴``` 버튼이 있는 곳은 그대로 있고, 아래 창만 프래그먼트가 번갈아서 나온다.



참고 https://youngest-programming.tistory.com/3