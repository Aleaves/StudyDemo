package com.sdk.mvp.base;

import com.sdk.mvp.entity.BaseEntity;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class BaseModel{

    private BasePresenter p;

    public BaseModel(BasePresenter p) {
        this.p = p;
    }

    public <T> void post(Observable<BaseEntity<T>> observable){
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseEntity<T>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseEntity<T> tBaseEntity) {
                        if(null != p) {
                            if (tBaseEntity.getCode() == 200) {
                                p.loadDataSuccess(tBaseEntity);
                            }else{
                                p.loadDataError(tBaseEntity.getTime()+"");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(null != p) {
                            p.loadDataError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
