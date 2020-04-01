package com.sdk.mvp;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jakewharton.rxbinding3.view.RxView;
import com.sdk.mvp.entity.UserInfo;
import com.sdk.mvp.ui.Presenter.UserPresenter;
import com.sdk.mvp.ui.UserView;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import kotlin.Unit;

public class MainActivity extends AppCompatActivity implements UserView {

    private UserPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //login();
            }
        });
        mPresenter = new UserPresenter(this);

        RxView.clicks(fab).throttleFirst(10, TimeUnit.SECONDS)
                .subscribe(new Observer<Unit>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Unit unit) {
                        login();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        //RxMap();
        //RxFlatMap();
        //RxConcat();
        //RxMerge();
        //startWith();
        //RxCount();
        //RxCache();
        RxInterval();
    }

    /**
     * 轮询
     */
    private void RxInterval() {

        Observable.interval(1,1,TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.i("============",aLong+"");
                    }
                });

    }

    /**
     * 缓存示例
     */
    private void RxCache() {
        String memoryCache = "从内存中获取数据";
        String diskCache = "从磁盘中获取数据";

        Observable<String> memory = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                if(memoryCache != null){
                    emitter.onNext(memoryCache);
                }else{
                    emitter.onComplete();
                }
            }
        });

        Observable<String> disk = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                if(diskCache != null){
                    emitter.onNext(diskCache);
                }else{
                    emitter.onComplete();
                }
            }
        });

        Observable<String> netWork = Observable.just("从网络中获取数据");

//        Observable.concat(memory,disk,netWork)
//                //.firstElement()
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Exception {
//                        Log.i("============",s);
//                    }
//                });

        Observable<String> c1 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("c1");
                emitter.onComplete();
            }
        });
        Observable<String> c2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("c2");
                emitter.onComplete();
            }
        });
        Observable<String> c3 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("c3");
                emitter.onComplete();
            }
        });
        Observable<String> c4 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("c4");
                emitter.onComplete();
            }
        });
        Observable.concat(c1,c2,c3,c4)
                .firstElement()
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.i("========",s);
                    }
                });



    }

    private void RxCount() {
        //统计数量
        Observable.just("1","2","3","4")
                .count()
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.i("===========",aLong+"");
                    }
                });

    }


    private void startWith() {
        Observable.just(1,2,3,4)
                .startWithArray(0,5,6)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i("===========",integer+"");
                    }
                });
    }


    private void RxMerge() {

        Observable.mergeArray(Observable.intervalRange(0,3,1,1,TimeUnit.SECONDS),
                Observable.intervalRange(2,3,1,1,TimeUnit.SECONDS)
                )
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.i("===========",aLong+"");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void timer(){
        Observable.timer(2,TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                Log.i("==========",aLong+"");
            }
        });
    }

    private void RxMap(){
        Observable.just(1,2,3,4,5).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return integer + "===";
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.i("=========",s);
            }
        });
    }

    private void RxFlatMap(){
        Observable.just(1,2,3,4,5).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                return Observable.just("a","b","c");
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.i("==========",s);
            }
        });
    }

    private void RxConcat(){
        Observable.concat(Observable.just(1,2,3),Observable.just("2","3","4")).subscribe(new Observer<Serializable>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Serializable serializable) {
                Log.i("+===========",serializable.toString());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void login(){
        mPresenter.quickLogin();
    }

    @Override
    public void onLoginSuccess(UserInfo userInfo) {
        Log.i("==========5",userInfo.toString());
    }

    @Override
    public void onLoginFailure() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }
}
