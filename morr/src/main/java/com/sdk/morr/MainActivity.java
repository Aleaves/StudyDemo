package com.sdk.morr;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
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
import io.reactivex.observables.GroupedObservable;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    /**
     * create
     *
     * @param view
     */
    public void RxCreate(View view) {
        //上游
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onComplete();
                //emitter.onError(new Exception("错错错"));
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.i(TAG, integer + "");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete");
            }
        });
    }

    /**
     * just创建符
     *
     * @param view
     */
    public void RxJust(View view) {
        Observable.just(1, 2, 3, 4)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, integer + "");
                    }
                });
    }

    /**
     * fromArray创建符
     *
     * @param view
     */
    public void RxfromArray(View view) {
        Observable.fromArray(1, 2, 3)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, integer + "");
                    }
                });
    }

    /**
     * fromIterable创建符
     *
     * @param view
     */
    public void RxFromIterable(View view) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        Observable.fromIterable(list)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.i(TAG, s);
                    }
                });
    }

    /**
     * empty操作符
     *
     * @param view
     */
    public void RxEmpty(View view) {
        Observable.empty()
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object o) {
                        Log.i(TAG, o.toString());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete");
                    }
                });


    }

    Integer i = 1;

    /**
     * defer创建符号
     *
     * @param view
     */
    public void RxDefer(View view) {
        //第一次对i进行赋值

        // 通过defer定义被观察者对象
        Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
            @Override
            public ObservableSource<? extends Integer> call() throws Exception {
                return Observable.just(i);
            }
        });

        //第二次对i进行赋值
        i = 11;

        //订阅事件   订阅之后才会发射事件  此时，才会调用defer（）创建被观察者对象（Observable）
        observable.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.i(TAG, integer + "");
            }
        });


    }

    /**
     * timer创建符号
     *
     * @param view
     */
    public void RxTimer(View view) {
        //延迟指定事件，发送一个0，一般用于检测
        Observable.timer(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.i(TAG, aLong + "");
                    }
                });
    }

    /**
     * interval创建符
     *
     * @param view
     */
    public void RxInterval(View view) {

        // 参数1 = 第1次延迟时间；
        // 参数2 = 间隔时间数字；
        // 参数3 = 时间单位；
        Observable.interval(2, 1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.i(TAG, aLong + "");
                    }
                });

    }

    /**
     * intervalRange创建符
     *
     * @param view
     */
    public void RxIntervalRange(View view) {
        //参数1 = 开始值
        //参数2 = 发射数量
        //参数3 = 延迟多久发射
        //参数4 = 每次间隔多久发射
        //参数5 = 时间单位
        Observable.intervalRange(5, 10, 1, 1, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.i(TAG, aLong + "");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete");
                    }
                });
    }

    /**
     * range创建符
     *
     * @param view
     */
    public void RxRange(View view) {
        //参数1 = 开始值
        //参数2 = 发射数量
        Observable.range(4, 10)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.i(TAG, integer + "");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete");
                    }
                });
    }

    /**
     * map操作符
     *
     * @param view
     */
    public void RxMap(View view) {

        //事件类型有integer转为string
        Observable.just(1, 2, 3)
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        return integer + "====";
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.i(TAG, s);
                    }
                });

    }

    /**
     * flatMap操作符
     *
     * @param view
     */
    public void RxFlatMap(View view) {

        //将integer转成 ObservableSource<String>> 发送  下游接收 string
        Observable.just("张三", "李四", "王五")
                .flatMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String s) throws Exception {
                        List<String> list = new ArrayList<>();
                        for (int i = 0; i < 3; i++) {
                            list.add(s + "==" + i);
                        }
                        return Observable.fromIterable(list).delay(5, TimeUnit.SECONDS);
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.i(TAG, s);
            }
        });

    }

    /**
     * ConcatMap变换符
     *
     * @param view
     */
    public void RxConcatMap(View view) {
        //将integer转成 ObservableSource<String>> 发送  下游接收 string
        Observable.just("张三", "李四", "王五")
                .concatMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String s) throws Exception {
                        List<String> list = new ArrayList<>();
                        for (int i = 0; i < 3; i++) {
                            list.add(s + "==" + i);
                        }
                        return Observable.fromIterable(list).delay(5, TimeUnit.SECONDS);
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.i(TAG, s);
            }
        });
    }

    /**
     * buffer变换符
     *
     * @param view
     */
    public void RxBuffer(View view) {

        //分批发送
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .buffer(2)
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        Log.i(TAG, integers.toString());
                    }
                });

    }

    /**
     * groupBy操作符
     *
     * @param view
     */
    public void RxGroupBy(View view) {


        Observable.just(4000, 5000, 6000, 7000, 8000, 9000, 10000)
                .groupBy(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        return integer > 7000 ? "高端配置电脑" : "低端配置电脑";
                    }
                }).subscribe(new Consumer<GroupedObservable<String, Integer>>() {
            @Override
            public void accept(GroupedObservable<String, Integer> groupedObservable) throws Exception {
                Log.i(TAG, groupedObservable.getKey());
                groupedObservable.subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, groupedObservable.getKey() + ":" + integer);
                    }
                });
            }
        });

    }

    /**
     * filter过滤操作符
     * @param view
     */
    public void RxFilter(View view) {

        //收到 1 3 4  5
        Observable.just(1,2,3,4,5)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        if(integer == 2){
                            return false;
                        }
                        return true;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.i(TAG,integer+"");
            }
        });

    }

    /**
     * take过滤符号
     * @param view
     */
    public void RxTake(View view) {

        Observable.interval(1,TimeUnit.SECONDS)
                .take(5)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.i(TAG,aLong+"");
                    }
                });

    }

    /**
     * distinct过滤符
     * @param view
     */
    public void RxDistinct(View view) {

        Observable.just(1,1,3,4,4,5)
                .distinct()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG,integer+"");
                    }
                });

    }

    /**
     * element过滤符
     * @param view
     */
    public void RxElement(View view) {

        //指定输出下标
        Observable.just(1,2,3,4,5,6,7,8)
                .elementAt(3)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG,integer+"");
                    }
                });

    }
}