package com.isober;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class SplashFragment extends Fragment{
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ImageButton button = (ImageButton) rootView.findViewById(R.id.button1);
        button.setOnClickListener(listener);
        return rootView;
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getFragmentManager().beginTransaction().add(R.id.container, new CalibrateFragment()).commit();
            getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.container)).commit();
            getFragmentManager().executePendingTransactions();
        }
    };
}
