package com.example.android.fragmentexample;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SimpleFragment extends Fragment {
    private static final  int YES = 0;
    private static final  int NO = 1;
    private static final  int NONE = 2;
    public int mRadioButtonChoice = NONE;
    OnFragmentInteractionListener mListener;
    private static final String CHOICE = "choice";
    private static String name;

    interface OnFragmentInteractionListener {
        void mRadioButtonChoice(int choice);
    }

    public static SimpleFragment newInstance(int choice) {
        // factory method
        SimpleFragment fragment = new SimpleFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(CHOICE, choice);
        fragment.setArguments(arguments);

        return fragment;
    }

    public SimpleFragment() {
        // Required empty public constructor

        // instance is not yet created
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener)context;
        } else {
            throw new ClassCastException(context.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_simple, container, false);
        final RadioGroup radioGroup = rootView.findViewById(R.id.radio_group);

        if (getArguments().containsKey(CHOICE)) {
            mRadioButtonChoice = getArguments().getInt(CHOICE);
            if (mRadioButtonChoice != NONE) {
                // display
                radioGroup.check(radioGroup.getChildAt(mRadioButtonChoice).getId());
            }
        }



        // set the listener (yes or no)
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton radio = group.findViewById(checkedId);
                int index = radioGroup.indexOfChild(radio);
                TextView textView = rootView.findViewById(R.id.fragment_header);

                switch (index) {
                    case YES: // user choose "YES"
                        // upload the text
                        textView.setText("ARTICLE: Liked");
                        mRadioButtonChoice = YES;
                        mListener.mRadioButtonChoice(YES);
                        break;

                    case NO: // user choose "NO"
                        // upload the text
                        textView.setText("ARTICLE: Thanks");
                        mRadioButtonChoice = NONE;
                        mListener.mRadioButtonChoice(NONE);
                        break;

                    default:
                        // do nothing
                        mRadioButtonChoice = NONE;
                        mListener.mRadioButtonChoice(NONE);
                        break;
                }

            }
        });

        return rootView;
    }

}
