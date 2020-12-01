## 리사이클러뷰 (RecyclerView)

#### 개념

* 많은 수의 데이터 집합을 제한된 영역 내에서 유연하게(flexible) 표시할 수 있도록 만들어주는 위젯이다.

> 사용자가 관리하는 많은 수의 **데이터 집합(Data Set)**을 개별 아이템 단위로 구성하여 화면에 출력하는 **뷰그룹(ViewGroup)**이며, 한 화면에 표시되기 힘든 많은 수의 데이터를 **스크롤** 가능한 **리스트**로 표시해주는 위젯

* 기본적으로 리사이클러뷰(RecyclerView)는 리스트뷰(ListView)와 사용 목적 및 동작 방식이 매우 유사한 요소이다. 리스트뷰의 확장판 또는 개선판이라고 볼 수 있다.

#### 구성 요소

* **리사이클러뷰(RecyclerView)** : 사용자 데이터를 리스트 형태로 화면에 표시하는 컨텥이너 역할 수행
* **어댑터(Adapter)** : 사용자 데이터 리스트로부터 아이템뷰를 만드는 역할
* **레이아웃매니저(LayoutManager)** : 리사이클러뷰가 아이템을 화면에 표시할 때, 아이템 뷰들이 리사이클러뷰 내부에서 배치되는 형태를 관리하는 요소
* **뷰홀더(ViewHolder)** : 화면에 표시될 아이템 뷰를 저장하는 객체

#### 기본 사용법

![리사이클러뷰 예제 화면 구성](https://t1.daumcdn.net/cfile/tistory/9972DD4A5C88BD1A0A)

이런 기본적인 리스트 표시 형태 만들기. 각 아이템이 텍스트뷰 하나로 구성된 형태로 데이터 리스트를 표시한다.

1. 메인액티비티에 리사이클러뷰 추가

   ```
   [STEP-1] "content_main.xml" - 메인액티비티 레이아웃 리소스 XML에 리사이클러뷰 추가.
   <android.support.v7.widget.RecyclerView
       android:id="@+id/recycler1"
       android:scrollbars="vertical"
       android:layout_width="match_parent"
       android:layout_height="match_parent"/>
   ```

2. 리사이클러뷰 아이템 뷰 레이아웃 추가

   ```
   [STEP-2] "recyclerview_item.xml" - 아이템 뷰를 위한 리소스 레이아웃 XML 작성.
   <?xml version="1.0" encoding="utf-8"?>
   <android.support.constraint.ConstraintLayout
       xmlns:android="http://schemas.android.com/apk/res/android"
       android:layout_width="match_parent"
       android:layout_height="wrap_content">
       <TextView
           android:layout_width="wrap_content"
           android:layout_height="wrap_content"
           android:id="@+id/text1"
           android:textSize="32sp"/>
   
   </android.support.constraint.ConstraintLayout>
   ```

3. 리사이클러뷰 어댑터 구현

   ```
   [STEP-3] "SimpleTextAdapter.java" - 리사이클러뷰 어댑터 작성.
   public class SimpleTextAdapter extends RecyclerView.Adapter<SimpleTextAdapter.ViewHolder> {
   
       private ArrayList<String> mData = null ;
   
       // 아이템 뷰를 저장하는 뷰홀더 클래스.
       public class ViewHolder extends RecyclerView.ViewHolder {
           TextView textView1 ;
   
           ViewHolder(View itemView) {
               super(itemView) ;
   
               // 뷰 객체에 대한 참조. (hold strong reference)
               textView1 = itemView.findViewById(R.id.text1) ;
           }
       }
   
       // 생성자에서 데이터 리스트 객체를 전달받음.
       SimpleTextAdapter(ArrayList<String> list) {
           mData = list ;
       }
   
       // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
       @Override
       public SimpleTextAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
           Context context = parent.getContext() ;
           LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
   
           View view = inflater.inflate(R.layout.recyclerview_item, parent, false) ;
           SimpleTextAdapter.ViewHolder vh = new SimpleTextAdapter.ViewHolder(view) ;
   
           return vh ;
       }
   
       // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
       @Override
       public void onBindViewHolder(SimpleTextAdapter.ViewHolder holder, int position) {
           String text = mData.get(position) ;
           holder.textView1.setText(text) ;
       }
   
       // getItemCount() - 전체 데이터 갯수 리턴.
       @Override
       public int getItemCount() {
           return mData.size() ;
       }
   }
   ```

4. 리사이클러뷰에 어댑터와 레이아웃매니저 지정하기

   ```
   [STEP-4] "MainActivity.java" - 리사이클러뷰에 어댑터와 레이아웃매니저 지정.
   public class MainActivity extends AppCompatActivity {
   
       @Override
       protected void onCreate(Bundle savedInstanceState) {
           // ... 코드 계속
   
           // 리사이클러뷰에 표시할 데이터 리스트 생성.
           ArrayList<String> list = new ArrayList<>();
           for (int i=0; i<100; i++) {
               list.add(String.format("TEXT %d", i)) ;
           }
   
           // 리사이클러뷰에 LinearLayoutManager 객체 지정.
           RecyclerView recyclerView = findViewById(R.id.recycler1) ;
           recyclerView.setLayoutManager(new LinearLayoutManager(this)) ;
   
           // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
           SimpleTextAdapter adapter = new SimpleTextAdapter(list) ;
           recyclerView.setAdapter(adapter) ;
       }
   ```

*  `LinearLayoutManager` 의 orientation 기본 값이 Vertical이므로 수직 방향으로 아이템을 표시할 수 있다. 만약 수평(Horizontal) 방향으로 아이템을 배치하려면 아래 코드처럼 객체 생성 시 아이템 배치 방향을 수평방향으로 지정하면 된다.

  ```
  recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)) ;
  ```