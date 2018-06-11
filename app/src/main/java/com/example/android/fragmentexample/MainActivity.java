/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.fragmentexample;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SimpleFragment.OnFragmentInteractionListener{
    private Button oButton;
    private boolean isFragmentDisplay = false;
    private int mRadioButtonChoice = 2;

    @Override
    public void mRadioButtonChoice(int choice) {
        mRadioButtonChoice = choice;
        Toast.makeText(this, "Choice is " + choice, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        oButton = findViewById(R.id.open_button);

        oButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFragmentDisplay) {
                    // display the fragment (add)
                    displayFragment();
                } else {
                    // close the fragment (remove)
                    closeFragment();
                }
             }
        });
    }

    public void  displayFragment() {
        // 1. get fragment object
        SimpleFragment simpleFragment = SimpleFragment.newInstance(mRadioButtonChoice);
        // 2. get the fragmentManager and start a transaction. (add, remove)
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // 3. add the fragment
        fragmentTransaction
                .add(R.id.fragment_container, simpleFragment)
                .addToBackStack(null)
                .commit(); // do it
        // 4.
        oButton.setText("CLOSE");

        isFragmentDisplay = true;
    }

    public void closeFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        SimpleFragment simpleFragment = (SimpleFragment)fragmentManager.findFragmentById(R.id.fragment_container);
        if (simpleFragment != null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(simpleFragment).commit();
        }
        oButton.setText("OPEN");
        isFragmentDisplay = false;
    }


}
