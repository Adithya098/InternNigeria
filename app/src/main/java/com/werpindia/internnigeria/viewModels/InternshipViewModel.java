package com.werpindia.internnigeria.viewModels;

import android.app.Application;
import android.widget.Toast;

import com.werpindia.internnigeria.models.Internship;
import com.werpindia.internnigeria.repositories.InternshipRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class InternshipViewModel extends AndroidViewModel
{
    private InternshipRepository internshipRepository;
    private CompositeDisposable compositeDisposable;

    public InternshipViewModel(@NonNull Application application)
    {
        super(application);
        internshipRepository = new InternshipRepository();
        compositeDisposable = new CompositeDisposable();
    }

    public LiveData<Boolean> postInternship(Internship newInternship)
    {
        MutableLiveData<Boolean> result = new MutableLiveData<>();

        internshipRepository.postInternship(newInternship).subscribe(new SingleObserver<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onSuccess(Boolean aBoolean) {
                result.setValue(aBoolean);
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(getApplication(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return result;
    }

    public LiveData<List<Internship>> getCompanyInternships(String companyEmail)
    {
        MutableLiveData<List<Internship>> result = new MutableLiveData<>();

        internshipRepository.getCompanyInternships(companyEmail)
                .subscribe(new SingleObserver<List<Internship>>() {
            @Override
            public void onSubscribe(Disposable d) { compositeDisposable.add(d); }

            @Override
            public void onSuccess(List<Internship> internships) { result.setValue(internships); }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(getApplication(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return result;
    }

    public LiveData<List<Internship>> getInternships()
    {
        MutableLiveData<List<Internship>> result = new MutableLiveData<>();

        internshipRepository.getInternships().subscribe(new SingleObserver<List<Internship>>() {
            @Override
            public void onSubscribe(Disposable d) { compositeDisposable.add(d); }

            @Override
            public void onSuccess(List<Internship> internships) { result.setValue(internships); }

            @Override
            public void onError(Throwable e) { Toast.makeText(getApplication(), e.getMessage(), Toast.LENGTH_SHORT).show(); }
        });
        return result;
    }

    @Override
    protected void onCleared()
    {
        compositeDisposable.clear();
        super.onCleared();
    }
}
