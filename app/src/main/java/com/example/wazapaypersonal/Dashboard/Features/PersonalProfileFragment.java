package com.example.wazapaypersonal.Dashboard.Features;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.wazapaypersonal.Dashboard.CountryToPhonePrefix;
import com.example.wazapaypersonal.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jwang123.flagkit.FlagKit;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.Objects;

import es.dmoral.toasty.Toasty;


public class PersonalProfileFragment extends Fragment {

    private BottomNavigationView mBottomNavigationView;

    private EditText mFirstNameEditTxt,
            mSecNameEditTxt,
            mUserNameEditTxt,
            mEmailEditTxt,
            mPhoneNumEditTxt,
            mEntryDateTxt;
    private Spinner mSexSpinner;
    private EditText mCountryEditTxt,
            mRegionEditTxt,
            mCityEditTxt;

    private Button mUploadBtn;
    private ImageView mProfileImg;
    private FloatingActionButton mSelectImageBtn;

    private TextView mUsername,
            mUserAlias;

    public PersonalProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //onscroll hide bottom navigation view
        ScrollView scrollView = view.findViewById(R.id.scrollView);
        if (scrollView != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                    @Override
                    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                        if (scrollY > oldScrollY) {
                            hideBottomNavigationView(mBottomNavigationView);
                        }
                        if (scrollY < oldScrollY) {
                            showBottomNavigationView(mBottomNavigationView);
                        }
                    }
                });
            }

        }

        mBottomNavigationView = getActivity().findViewById(R.id.bottom_nav);
    }

    private void hideBottomNavigationView(BottomNavigationView view) {
        view.animate().translationY(view.getHeight());
    }

    private void showBottomNavigationView(BottomNavigationView view) {
        view.animate().translationY(0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_personal_profile, container, false);

//        setHasOptionsMenu(true);
//        Toolbar toolbar = view.findViewById(R.id.toolbar);
//        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);


        //get country ISO code
        final CountryToPhonePrefix countryToPhonePrefix = new CountryToPhonePrefix(view.getContext());
//        Toasty.success(view.getContext(), countryToPhonePrefix.getCountryIsoCode("+237674753811")).show();

        //initialize all edit text
        mFirstNameEditTxt = view.findViewById(R.id.editText);
        mSecNameEditTxt = view.findViewById(R.id.editText1);
        mUserNameEditTxt = view.findViewById(R.id.editText2);

        mEmailEditTxt = view.findViewById(R.id.editText4);

        mPhoneNumEditTxt = view.findViewById(R.id.editText3);


        String alpha2 = countryToPhonePrefix.getCountryIsoCode(mPhoneNumEditTxt.getText().toString().replaceAll("\\s", ""));
        if (alpha2 != null) {
            Drawable drawable = FlagKit.drawableWithFlag(getContext(), alpha2.toLowerCase());
            //change flag size
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 90, 70, true));
            mPhoneNumEditTxt.setCompoundDrawablesWithIntrinsicBounds(d, null, getResources().getDrawable(R.drawable.ic_edit), null);
        }

        mPhoneNumEditTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                //if there is no number clear flag image
                if (s.toString().contains("")) {
                    mPhoneNumEditTxt.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_edit), null);
                }

                String alpha2 = countryToPhonePrefix.getCountryIsoCode(s.toString().replaceAll("\\s", ""));
                if (alpha2 != null) {
                    Drawable drawable = FlagKit.drawableWithFlag(getContext(), alpha2.toLowerCase());
                    //change flag size
                    Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                    Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 90, 70, true));
                    mPhoneNumEditTxt.setCompoundDrawablesWithIntrinsicBounds(d, null, getResources().getDrawable(R.drawable.ic_edit), null);
                }
            }
        });

        mEntryDateTxt = view.findViewById(R.id.editText5);
        mSexSpinner = view.findViewById(R.id.spinner);

        mCountryEditTxt = view.findViewById(R.id.editText7);
        mRegionEditTxt = view.findViewById(R.id.editText8);
        mCityEditTxt = view.findViewById(R.id.editText9);

        mProfileImg = view.findViewById(R.id.profile);

        mUploadBtn = view.findViewById(R.id.button);
        mUploadBtn.setOnClickListener(mOnClickListener);

        mSelectImageBtn = view.findViewById(R.id.choose_image);
        mSelectImageBtn.setOnClickListener(mOnClickListener);

        mUsername = view.findViewById(R.id.username);
        mUserAlias = view.findViewById(R.id.user_alias);

        //get country ISO to load country image in edittext


        return view;
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button:
                    //upload data
                    Toasty.success(getContext(), "data updated").show();
                    break;
                case R.id.choose_image:
                    //image picker
                    CropImage.activity()
                            .setGuidelines(CropImageView.Guidelines.ON)
                            .setMinCropResultSize(512, 512)
                            .setAspectRatio(1, 1)
                            .start(getContext(), PersonalProfileFragment.this);
                    break;
            }
        }
    };

    private Uri mPickedImageUri;

    //get the result form the mImageView cropper and displays it on the mImageView view
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {

            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if (resultCode == Activity.RESULT_OK) {

                mPickedImageUri = result.getUri();
                Glide.with(getContext()).load(mPickedImageUri).into(mProfileImg);

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toasty.error(Objects.requireNonNull(getActivity(), "Cant Be Null"), result.getError().toString(), 2000, true).show();
            }
        }
    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                Navigation.findNavController(getView()).popBackStack();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
}
