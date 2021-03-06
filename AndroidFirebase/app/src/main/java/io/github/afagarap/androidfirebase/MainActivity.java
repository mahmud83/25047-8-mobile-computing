/*
Copyright 2017 Abien Fred Agarap

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package io.github.afagarap.androidfirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * Data schema
 *
 * "students" {
 *      "201212051" : {
 *          "mFirstName" : "Patrick",
 *          "mLastName" : "Esperanza",
 *          "mMiddleName" : "Romulo"
 *      },
 *      "201320018" : {
 *          "mFirstName" : "Abien Fred",
 *          "mLastName" : "Agarap",
 *          "mMiddleName" : "Maranan"
 *      },
 *      "201411756" : {
 *          "mFirstName" : "Angel Diane",
 *          "mLastName" : "Estanislao",
 *          "mMiddleName" : "Piadozo"
 *      }
 *  }
 *
 * "students" {
 *     [student_number] {
 *         "mFirstName" : Value1,
 *         "mLastName" : Value2,
 *         "mMiddleName" : Value3
 *     }
 * }
 *
 */


public class MainActivity extends AppCompatActivity {
    final String TAG = MainActivity.class.getSimpleName();

    final String STUDENT_NUMBER = "STUDENT_NUMBER";
    final String FIRST_NAME = "FIRST_NAME";
    final String MIDDLE_NAME = "MIDDLE_NAME";
    final String LAST_NAME = "LAST_NAME";

    EditText mStudentNumberEditText;
    EditText mFirstNameEditText;
    EditText mMiddleNameEditText;
    EditText mLastNameEditText;
    EditText mStudentNumberQueryEditText;

    ListView mRecordsListView;

    Button mAddRecordButton;
    Button mSearchButton;
    Button mRetrieveRecords;

    String mStudentNumber;
    String mFirstName;
    String mMiddleName;
    String mLastName;

    List<String> mStudentRecords = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStudentNumberEditText = (EditText) findViewById(R.id.studentNumberAddEditText);
        mFirstNameEditText = (EditText) findViewById(R.id.firstNameEditText);
        mMiddleNameEditText = (EditText) findViewById(R.id.middleNameEditText);
        mLastNameEditText = (EditText) findViewById(R.id.lastNameEditText);
        mStudentNumberQueryEditText = (EditText) findViewById(R.id.studentNumberQueryEditText);

        mRecordsListView = (ListView) findViewById(R.id.recordsListView);

        mAddRecordButton = (Button) findViewById(R.id.addRecordButton);
        mSearchButton = (Button) findViewById(R.id.searchButton);
        mRetrieveRecords = (Button) findViewById(R.id.retrieveRecordsButton);

        mAddRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ref = instantiateFirebase("students");

                mStudentNumber = mStudentNumberEditText.getText().toString();
                mFirstName = mFirstNameEditText.getText().toString();
                mMiddleName = mMiddleNameEditText.getText().toString();
                mLastName = mLastNameEditText.getText().toString();

                // create a HashMap to contain the data to be added to Firebase
                Map <String, String> studentRecord = new HashMap<>();
                studentRecord.put(STUDENT_NUMBER, mStudentNumber);
                studentRecord.put(FIRST_NAME, mFirstName);
                studentRecord.put(MIDDLE_NAME, mMiddleName);
                studentRecord.put(LAST_NAME, mLastName);

                addRecord(ref, studentRecord);

                mStudentNumberEditText.setText("");
                mFirstNameEditText.setText("");
                mMiddleNameEditText.setText("");
                mLastNameEditText.setText("");
            }
        });

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ref = instantiateFirebase("students");

                mStudentNumber = mStudentNumberQueryEditText.getText().toString();

                getRecord(ref, mStudentNumber);
            }
        });

        mRetrieveRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ref = instantiateFirebase("programs");
                getAllRecord(ref);
            }
        });
    }

    public static class Student {
        public String mFirstName;
        public String mMiddleName;
        public String mLastName;

        public Student (String firstName, String middleName, String lastName) {
            this.mFirstName = firstName;
            this.mMiddleName = middleName;
            this.mLastName = lastName;
        }
    }

    /**
     * Returns an instantiated Firebase database
     * @param parent The string which refers to the parent node to retrieve, e.g.
     *               android-firebase-f066a {
     *                  programs {
     *                      arts {
     *                          major : value_1
     *                      }
     *                      science {
     *                          major : value_1
     *                      }
     *                  }
     *                  students {
     *                          student_number_n {
     *                                  mFirstName : value_1,
     *                                  mMiddleName : value_2,
     *                                  mLastName : value_3
     *                          }
     *                  }
     *               }
     *               `parent` may refer to either 'programs' or 'students, i.e. when calling
     *               DatabaseReference refStudents = instantiateFirebase("students");
     *
     *               or
     *
     *               DatabaseReference refPrograms = instantiateFirebase("programs");
     * @return
     */
    public DatabaseReference instantiateFirebase(String parent) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(parent);
        return myRef;
    }

    public void addRecord(DatabaseReference ref, Map map) {
        final String studentNumber = (String) map.get(STUDENT_NUMBER);
        final String firstName = (String) map.get(FIRST_NAME);
        final String middleName = (String) map.get(MIDDLE_NAME);
        final String lastName = (String) map.get(LAST_NAME);

        Student student = new Student(firstName, middleName, lastName);

        ref.child(studentNumber).setValue(student, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                Toast.makeText(MainActivity.this, "Success!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getRecord(DatabaseReference ref, final String studentNumber) {
        // ref already contains the child "students",
        // access a data in "students" according to studentNumber
        ref.child(studentNumber).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // dataSnapshot returns <Key, Value>
                // e.g. <"mFirstName", "Patrick">
                // the object must be parsed to String
                String firstName = (String) ((Map) dataSnapshot.getValue()).get("mFirstName");
                String middleName = (String) ((Map) dataSnapshot.getValue()).get("mMiddleName");
                String lastName = (String) ((Map) dataSnapshot.getValue()).get("mLastName");
                mStudentNumberEditText.setText(studentNumber);
                mFirstNameEditText.setText(firstName);
                mMiddleNameEditText.setText(middleName);
                mLastNameEditText.setText(lastName);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, databaseError.toString());
            }
        });
    }

    public void getAllRecord(DatabaseReference ref) {
        final HashMap<String, Object> singleRecord = new HashMap<>();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Convert the dataSnapshot (HashMap) object to ArrayList
                mapToList((Map)dataSnapshot.getValue());
                // makes the ListView real-time
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        // create adapter for the ListView
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, mStudentRecords);

        // connect the ListView to its adapter
        mRecordsListView.setAdapter(adapter);
    }

    public void mapToList(Map<String, Object> map) {
        mStudentRecords.clear();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            // extract HashMap key from Firebase database
            String key = entry.getKey();

            // extract HashMap value from Firebase database
            Object value = entry.getValue();

            // Since the HashMap value is also a HashMap, based on data schema, i.e.
            // "students" {
            //      [studentNumber] {
            //          "mFirstName" : value1,
            //          "mMiddleName" : value2,
            //          "mLastName" : value3
            //      }
            // }
            // extract the values from the HashMap studentNumber
            // String firstName = (String) ((Map)(value)).get("mFirstName");
            // String middleName = (String) ((Map)(value)).get("mMiddleName");
            // String lastName = (String) ((Map)(value)).get("mLastName");

            // Add the extracted HashMap values to the ArrayList
            /*mStudentRecords.add(String.format(Locale.ENGLISH, "%s : %s %s %s",
                    key, firstName, middleName, lastName));*/

            // Since the getAllRecords() now refers to the 'programs',
            // this is an example of retrieving the children of 'programs'.
            mStudentRecords.add(String.format(Locale.ENGLISH, "%s : %s", key, value));
        }
    }
}
