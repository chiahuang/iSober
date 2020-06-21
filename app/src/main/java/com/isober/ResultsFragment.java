package com.isober;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

public class ResultsFragment extends Fragment{

    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_results, container, false);
        TextView box = (TextView) rootView.findViewById(R.id.results);
        box.setText(MainActivity.res);

        ImageButton test_a = (ImageButton) rootView.findViewById(R.id.button1);
        test_a.setOnClickListener(listener);
		/*
		rootView.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent evnt) {
				//Toast.makeText(rootView.getContext(), "ResultsFragment", Toast.LENGTH_SHORT).show();
				return true;
			}
		});*/

        return rootView;
    }


    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.button1)
            {
                //FragmentManager fm = getFragmentManager();
                //for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                //    fm.popBackStack();
                //}

                Intent myIntent = new Intent(rootView.getContext(), MainActivity.class);
                startActivity(myIntent);
                getActivity().finish();
                //getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.container)).commit();
                //getFragmentManager().beginTransaction().add(R.id.container, new SplashFragment()).commit();
                //getFragmentManager().executePendingTransactions();

            }
        }
    };
}
