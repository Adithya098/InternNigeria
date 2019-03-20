package com.werpindia.internnigeria;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class applicant_list extends Fragment {
    private static final String tag ="Applicant_list";
    List<applications> applicants;
public  applicant_list()
{
    //requires empty consrtuctor

}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.applicant_list,container,false);


        applicants=new ArrayList<>();
        applicants.add(new applications("Adam Smith","smithad@gmail.com",R.drawable.adam));
        applicants.add(new applications("Christa Jacob","jacobchirs@gmail.com",R.drawable.christa));
        applicants.add(new applications("Kunal Hirani","kunal.hirani@gmail.com",R.drawable.abc));
        applicants.add(new applications("Steve Martin","stevemartin12@gmail.com.",R.drawable.seteve));
        applicants.add(new applications("Emiley Cook","emiley1999@gmail.com",R.drawable.emiley));
        applicants.add(new applications("Om Shukla","om.shukla@gmail.com",R.drawable.abc));
        applicants.add(new applications("Marc Lewis","om.shukla@gmail.com",R.drawable.adam));
        RecyclerView myrv=view.findViewById(R.id.rv1);
        myrv.setHasFixedSize(true);
        RecylerViewAdapter recylerViewAdapter=new RecylerViewAdapter(getActivity(),applicants);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        myrv.setLayoutManager(layoutManager);
        myrv.setAdapter(recylerViewAdapter);
        return view;
    }
}
