# ViewModel
우리가 안드로이드 앱을 사용할 때 화면 회전과 같은 구성(configuration change)이 변경되면 앱은 새로운 구성으로 activity를 파괴하고
재생성(destroy and recreate)해야 한다. 그 결과 activity의 실행 기간 동안 생성된 값도 파괴된다.
```
Configuration Change:  Screen rotations, keyboard changes, language charges, Enabling multi window mode.
```
rest api에서 2000개 제품에 대한 데이터를 얻어야 한다면,구성이 변경될 때마다 앱은 온라인 rest API로 데이터를 계속해서 다운로드해야 할 것이다.
이는 많은 데이터와 시스템 자원이 불필요하게 낭비될 것이다.많은 데이터와 시스템 자원이 불필요하게 낭비될 것이다. 그리고 사용자들은 다운로드 과정이 완료될 때까지 계속해서 기다려야 하기 때문에, 앱은 매우 나쁜, 매우 부정적인 사용자 경험을 제공할 것이다.
안드로이드 Jetpack과 함께 도입된 View Model 아키텍처 컴포넌트는 이러한 문제에 대한 최고의 솔루션이다.

UI 관련 데이터를 저장·관리하도록 설계했다. 우리는 보통 activity이나 fragment에 대해 하나의 뷰 모델을 만든다. 
때로는 둘 이상의 activity이나 fragment들이 하나의 뷰 모델을 공유할 수 있다.View Model은 activity가 생성되면 메모리에서 activity가 지워질 때까지 지속된다. 
따라서 뷰 모델은 값이 activity에 속할 수 있다.

```
From the moment an app launches an activity to the moment it appears on the screen Activity has to pass three lifecycle states.
Create state, start state and resume state. Activity has to pass pause, stop and destroy states before it ends. ViewModel scope start with the invocation of the onCreate method. 
It ends when the activity removed form the memory.During that period activity can recreate again and again , but view model instance will live in the memory holding activity's data. 
```
#
## Implementation
* MainActivity
```
class MainActivity : AppCompatActivity() {

    lateinit var mBinding:ActivityMainBinding
    lateinit var mainVM:MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        mainVM = ViewModelProvider(this,MainViewModelFactory(application,100)).get(MainViewModel::class.java)
        mBinding.apply {
            tvCount.text = mainVM.getCurrentCount().toString()
            btnCount.setOnClickListener {
                tvCount.text = mainVM.getUpdateCount().toString()
            }
        }
    }
}
```

* MainViewModel
```
class MainViewModel(application: Application, startCount: Int) : AndroidViewModel(application) {
    private var testCount: Int = 0

    init {
        testCount = startCount
    }

    fun getCurrentCount(): Int {
        return testCount
    }

    fun getUpdateCount(): Int {
        return ++testCount
    }
}
```

* MainViewModelFactory
```
class MainViewModelFactory(private val mApplication: Application, private val startCount: Int) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(mApplication, startCount) as T
    }
}
```