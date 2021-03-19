package com.anikrakib.blooddonation.Fragment;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.anikrakib.blooddonation.Activity.SignUpActivity;
import com.anikrakib.blooddonation.R;
import com.anikrakib.blooddonation.databinding.FragmentAgeBinding;
import com.shawnlin.numberpicker.NumberPicker;

import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public class AgeFragment extends Fragment {
    FragmentAgeBinding fragmentAgeBinding;
    private static final String[] month = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    int age,date;
    String monthName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentAgeBinding = FragmentAgeBinding.inflate(getLayoutInflater());

        setupWheelPicker(fragmentAgeBinding.agePicker,0,70,18,6,true);
        setupWheelPicker(fragmentAgeBinding.datePicker,1,31,1,6,true);
        setupWheelPicker(fragmentAgeBinding.monthPicker,0,0,0,3,false);

        //create calendar object for current day
        long currentTime = System.currentTimeMillis();
        Calendar now = Calendar.getInstance();
        now.setTimeInMillis(currentTime);
        int year = now.get(Calendar.YEAR);

        fragmentAgeBinding.datePicker.setOnScrollListener(new NumberPicker.OnScrollListener() {
            @Override
            public void onScrollStateChange(NumberPicker picker, int scrollState) {
                if (scrollState == SCROLL_STATE_IDLE) {
                    date = picker.getValue();
                    Log.d("year","year "+year+date+age+monthName);
                }
            }
        });
        fragmentAgeBinding.agePicker.setOnScrollListener(new NumberPicker.OnScrollListener() {
            @Override
            public void onScrollStateChange(NumberPicker picker, int scrollState) {
                if (scrollState == SCROLL_STATE_IDLE) {
                    age = picker.getValue();
                    Log.d("year","year "+(year-age)+" "+date+age+monthName);
                }
            }
        });
        fragmentAgeBinding.monthPicker.setOnScrollListener(new NumberPicker.OnScrollListener() {
            @Override
            public void onScrollStateChange(NumberPicker picker, int scrollState) {
                if (scrollState == SCROLL_STATE_IDLE) {
                    monthName = month[(picker.getValue()-1)];
                    Log.d("year","year "+year+date+age+monthName);
                }
            }
        });

        fragmentAgeBinding.nextButton.setOnClickListener(v->{
            SignUpActivity.userDataModel.setAge(age);
            SignUpActivity.userDataModel.setDateOfMonth(date);
            SignUpActivity.userDataModel.setNameOfMonth(monthName);
            SignUpActivity.userDataModel.setYear((year-age));
            Log.d("data","Address:--"+SignUpActivity.userDataModel.toString());

            UploadImageFragment uploadImageFragment = new UploadImageFragment();
            assert getFragmentManager() != null;
            getFragmentManager().beginTransaction()
                    .replace(SignUpActivity.activitySignUpBinding.container.getId(),uploadImageFragment)
                    .commit();

            Log.d("data","ALL Data:--"+ SignUpActivity.userDataModel.toString());
        });




        return fragmentAgeBinding.getRoot();
    }

    public void setupWheelPicker(NumberPicker numberPicker, int minNumb, int maxNumb, int setValue, int showTotalNumb, boolean number){
        //final int[] value = new int[1];
        // Set formatter
        numberPicker.setFormatter(getString(R.string.number_picker_formatter));
        // Set selected text color
        numberPicker.setSelectedTextColorResource(R.color.primaryTextColor);
        // Set selected text size
        numberPicker.setSelectedTextSize(R.dimen.text_size_45sp);
        numberPicker.setFormatter(R.string.number_picker_formatter);
        // Set selected typeface
        numberPicker.setSelectedTypeface(R.string.sofia_soft, Typeface.BOLD);
        // Set text color
        numberPicker.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.thirdTextColor));
        // Set text size
        numberPicker.setTextSize(getResources().getDimension(R.dimen.text_size_35sp));
        // Set typeface
        numberPicker.setTypeface(R.string.sofia_soft, Typeface.BOLD);

        if(number){
            // Set value
            numberPicker.setMaxValue(maxNumb);
            numberPicker.setMinValue(minNumb);
            numberPicker.setValue(setValue);
        }else {
            //Set string values

            numberPicker.setMinValue(1);
            numberPicker.setMaxValue(month.length);
            numberPicker.setDisplayedValues(month);
        }

        // Set fading edge enabled
        numberPicker.setFadingEdgeEnabled(false);
        numberPicker.setSelectedTextAlign(NumberPicker.CENTER);
        numberPicker.setWheelItemCount(showTotalNumb);

        // Set scroller enabled
        numberPicker.setScrollerEnabled(true);

        // Set wrap selector wheel
        numberPicker.setWrapSelectorWheel(true);

        // Set accessibility description enabled
        numberPicker.setAccessibilityDescriptionEnabled(true);


//        // OnValueChangeListener
//        fragmentAgeBinding.agePicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
//            @Override
//            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
//                Log.d("year","age "+newVal);
//            }
//        });


//        // OnScrollListener
//        numberPicker.setOnScrollListener(new NumberPicker.OnScrollListener() {
//            @Override
//            public void onScrollStateChange(NumberPicker picker, int scrollState) {
//                if (scrollState == SCROLL_STATE_IDLE) {
//                    Log.d("TAG", String.format(Locale.US, "newVal: %d", picker.getValue()));
//                }
//            }
//        });

    }

}