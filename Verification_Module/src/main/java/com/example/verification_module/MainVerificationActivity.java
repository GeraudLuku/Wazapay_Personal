package com.example.verification_module;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


import com.blongho.country_data.World;
import com.example.verification_module.R;

import java.util.Timer;
import java.util.TimerTask;

import static com.example.verification_module.VerificationPreferences.getAddVerificationStateFlag;
import static com.example.verification_module.VerificationPreferences.getEmailVerificationStateFlag;
import static com.example.verification_module.VerificationPreferences.getIdentVerificationStateFlag;
import static com.example.verification_module.VerificationPreferences.getPhoneVerificationStateFlag;
import static com.example.verification_module.VerificationPreferences.setAddVerificationStateFlag;
import static com.example.verification_module.VerificationPreferences.setEmailVerificationStateFlag;
import static com.example.verification_module.VerificationPreferences.setIdentVerificationStateFlag;
import static com.example.verification_module.VerificationPreferences.setPhoneVerificationStateFlag;
import static com.example.verification_module.VerificationPreferences.verificationContext;

public class MainVerificationActivity extends AppCompatActivity {


    //main view container for each
    public static View content_email_boot, content_phone_boot, content_ident_boot,content_address_boot
            ,content_payment_boot;


    //section cards
    public static CardView emailCard, phoneCard, identCard, addCard,paymCard;


    //the section image that shows completioon and the text that shows the position
    public static View emailDone, phoneDone, identDone, emailPosition, phonePosition, identPosition
            ,addPosition,addDone,paymPosition,paymDone;


    //section lables for all five sections
    public static TextView headEmail,headPhone, headIdent, headAdd,headPayment;



    //this variable is used to switch between pages
    public static int verifyPosition = 0;




    /* getMyViews
     *
     * This method gets the views for the entire activity ansd sets onclick listeners for the
     * corresponding buttons.
     *
     * The views are collected in batches with the first batch of views pointing to views with global
     * importance. Views particular to a verification boot (the subsection of a verification process)
     * are collected thereafter and in the order in which they appear on the UI
     *
     * */

    public void getMyViews() {

        //1. Views for global use in the activity

        //(1.1) pointers to boots
        content_email_boot = findViewById(R.id.content_email_boot);
        content_phone_boot = findViewById(R.id.content_phone_boot);
        content_ident_boot = findViewById(R.id.content_ident_boot);
        content_address_boot = findViewById(R.id.content_address_boot);
        content_payment_boot = findViewById(R.id.content_paym_boot);

        //(1.2) side bar circles with numbers and tick image
        emailCard = findViewById(R.id.ver_email_card);
        phoneCard = findViewById(R.id.ver_phone_card);
        identCard = findViewById(R.id.ver_ident_card);
        addCard = findViewById(R.id.ver_add_card);
        paymCard = findViewById(R.id.ver_paym_card);

        emailDone = findViewById(R.id.ver_email_done);
        emailPosition = findViewById(R.id.ver_email_position);

        phoneDone = findViewById(R.id.ver_phone_done);
        phonePosition = findViewById(R.id.ver_phone_position);

        identDone = findViewById(R.id.ver_ident_done);
        identPosition = findViewById(R.id.ver_ident_position);

        addDone = findViewById(R.id.ver_add_done);
        addPosition = findViewById(R.id.ver_add_position);

        paymDone = findViewById(R.id.ver_paym_done);
        paymPosition = findViewById(R.id.ver_paym_position);

        //(1.3) Section labels
        headEmail = findViewById(R.id.ver_head_email_number);
        headPhone = findViewById(R.id.ver_head_phone_number);
        headIdent = findViewById(R.id.ver_head_ident);
        headAdd = findViewById(R.id.ver_head_add);
        headPayment = findViewById(R.id.ver_head_paym);


        //2. Views specific to a particular verification sub-process

        // 2.1 Email verification views

       getEmailSpecificViews();

        //2.2 Phone verification views

        getPhoneSpecificViews();



        //2.3 Identity Verification views

        getIdentSpecificViews();


        //2.4 Address Verification views

        getAddSpecificViews();

        //2.5 Payment Verification views
        getPaymentSpecificViews();
    }



    /**
     *
     * Method to check for valid email input
     * */

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


    /***
     *
     * Method that manages view swaps for section label onclicks
     *
     * hides the current view and request the desired one
     * */

    private void hideCurrentView() {
        switch (verifyPosition) {
            case 0:
                content_email_boot.setVisibility(View.GONE);
                emailAlertText.setVisibility(View.GONE);
                if (emailVerificationStateFlag ==0){
                    //restore default color if the process is at the start stage
                    headEmail.setTextColor(getResources().getColor(R.color.colordefault));
                    emailCard.setCardBackgroundColor(getResources().getColor(R.color.circleColor));
                }
                break;
            case 1:
                content_phone_boot.setVisibility(View.GONE);
                phoneAlertText.setVisibility(View.GONE);
                if (emailVerificationStateFlag ==0){
                    //restore default color if the process is at the start stage
                    headPhone.setTextColor(getResources().getColor(R.color.colordefault));
                    phoneCard.setCardBackgroundColor(getResources().getColor(R.color.circleColor));
                }
                break;
            case 2:
                content_ident_boot.setVisibility(View.GONE);
                identAlertText.setVisibility(View.GONE);
                if (identVerificationStateFlag ==0){
                    //restore default color if the process is at the start stage
                    headIdent.setTextColor(getResources().getColor(R.color.colordefault));
                    identCard.setCardBackgroundColor(getResources().getColor(R.color.circleColor));
                }
                break;
            case 3:
                content_address_boot.setVisibility(View.GONE);
                addAlertText.setVisibility(View.GONE);
                if ( addVerificationStateFlag ==0){
                    //restore default color if the process is at the start stage
                    headAdd.setTextColor(getResources().getColor(R.color.colordefault));
                    addCard.setCardBackgroundColor(getResources().getColor(R.color.circleColor));
                }
                handler.removeCallbacksAndMessages(null);
                break;
            default:
                content_payment_boot.setVisibility(View.GONE);

                if ( paymentVerificationStateFlag ==0){
                    //restore default color if the process is at the start stage
                    paymMethodsList.setVisibility(View.GONE);
                    headPayment.setTextColor(getResources().getColor(R.color.colordefault));
                    paymCard.setCardBackgroundColor(getResources().getColor(R.color.circleColor));
                }

                break;


        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_verification);

        World.init(getApplicationContext());

        verificationContext = this;

        // TODO: DELETE THIS BEFORE YOU DEPLOY
        setPhoneVerificationStateFlag(0);
        setEmailVerificationStateFlag(0);
        setIdentVerificationStateFlag(0);
        setAddVerificationStateFlag(0);

        getMyViews();


    }


    /**
     * Manage email boot.
     * <p>
     * The email boot has views stacked and are revealed and hidden respectively,
     * depending on what state of the verification process the email verification is at.
     */

    public static int emailVerificationStateFlag = 0; //show state of the process

    //email view variables

    public static View emailRoot, emailCode, emailPending; // main view of the email verifier

    public static TextView emailAlertText; // textview below the section lable and the button text at the buttom

    public static Button emailButtonMain, emailButtonText; // fat green button in the email boot

    public static EditText emailInput,emailCodeInput;



    //view getter for the email boot
    public void getEmailSpecificViews(){
        emailRoot = findViewById(R.id.ver_email_root);

        emailPending = findViewById(R.id.ver_email_pendingView);

        emailCode = findViewById(R.id.ver_email_codeView);


        emailAlertText = findViewById(R.id.ver_email_alertText);

        emailButtonText = findViewById(R.id.ver_email_buttonText);
        emailButtonMain = findViewById(R.id.ver_email_buttonMain);

        emailInput = findViewById(R.id.ver_email_input);
        emailCodeInput = findViewById(R.id.emailCodeInput);


        emailButtonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEmailButtonMainClicked();
            }
        });
        emailButtonText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEmailButtonTextClicked();
            }
        });

    }


    //this method gets the views for managing email verification and sets onclick listeners for
    //the corresponding buttons
    public void initializeEmailVerifications() {

        emailVerificationStateFlag = getEmailVerificationStateFlag();

        headEmail.setTextColor(getResources().getColor(R.color.colorHighlight));
        emailCard.setCardBackgroundColor(getResources().getColor(R.color.colorHighlight));

        content_email_boot.setVisibility(View.VISIBLE);

        switch (emailVerificationStateFlag) {
            case 0: //submit

                emailAlertText.setText("A code will be sent to your email.");
                emailAlertText.setTextColor(getResources().getColor(R.color.colordefault));
                emailAlertText.setVisibility(View.VISIBLE);

                emailInput.setVisibility(View.VISIBLE);
                emailCode.setVisibility(View.GONE);

                emailInput.setText(emailInput.getText());//line reactivates the text change listener



                emailButtonText.setTextColor(getResources().getColor(R.color.verTrans));

                break;
            case 1: // code and resend
                emailInput.setVisibility(View.GONE);
                emailCode.setVisibility(View.VISIBLE);

                emailAlertText.setText("Enter the code sent to your email");
                emailAlertText.setTextColor(getResources().getColor(R.color.pending));

                emailButtonText.setText("Cancel");

                emailButtonText.setTextColor(getResources().getColor(android.R.color.black));
                break;
            case 3: // failed
                emailAlertText.setText("We could not verify the email you provided.");
                emailAlertText.setTextColor(getResources().getColor(R.color.warning));

                emailCode.setVisibility(View.GONE);
                emailPending.setVisibility(View.VISIBLE);

                emailButtonText.setTextColor(getResources().getColor(R.color.colordefault));
                emailButtonMain.setText("Continue");
                break;

            case 2: // completed
                emailAlertText.setText("Your check has been completed");
                emailAlertText.setTextColor(getResources().getColor(R.color.colorHighlight));

                content_email_boot.setVisibility(View.GONE);
                content_phone_boot.setVisibility(View.VISIBLE);

                emailDone.setVisibility(View.VISIBLE);
                emailPosition.setVisibility(View.GONE);

                phoneCard.setCardBackgroundColor(getResources().getColor(R.color.colorHighlight));
                headPhone.setTextColor(getResources().getColor(R.color.colorHighlight));
                setEmailVerificationStateFlag(4);
                break;
                default:
                    emailAlertText.setText("Your check has been completed");
                    emailAlertText.setTextColor(getResources().getColor(R.color.Primary));

                    emailCode.setVisibility(View.GONE);
                    emailPending.setVisibility(View.VISIBLE);

                    emailButtonText.setTextColor(getResources().getColor(R.color.colordefault));
                    emailButtonMain.setText("Continue");
                    break;
        }

    }


    //method manages clicks on the emails main button
    private void onEmailButtonMainClicked() {

        if (emailVerificationStateFlag == 0){

            //manage email submission . . .

            String email = emailInput.getText().toString().trim();

            if ( isEmailValid(email) ){
                //TODO : submit email to server
                setEmailVerificationStateFlag(emailVerificationStateFlag + 1);
                initializeEmailVerifications();
            }else{
                if (!TextUtils.isEmpty(email))
                    emailInput.setError("Invalid email");
                else
                    emailInput.setError("Please enter your email");
            }

        }else if (emailVerificationStateFlag == 1){

            //TODO : manage code submission


            /*** TODO: the following lines only execute if the code uploaded is valid
             *  need to use and if statement to update the flag either by two (incase of failure) or one
             * (in case of success) .
             * */
            setEmailVerificationStateFlag(emailVerificationStateFlag + 1);
            initializeEmailVerifications();

        }


    }

    //method managing clicks on the text button
    private void onEmailButtonTextClicked() {

        if (  emailVerificationStateFlag != 0 ){
            setEmailVerificationStateFlag(emailVerificationStateFlag - 1);
            initializeEmailVerifications();
        }

    }


    //method to manage email section label onclick
    public void onEmailVerificationClicked(View view) {
        if (verifyPosition != 0 && emailVerificationStateFlag != 2) {
            hideCurrentView();
            verifyPosition = 0;

            initializeEmailVerifications();

        }
    }


    // resend email code

    public void onResendEmailCode(View view) {
        Toast.makeText(this, "Resend Email Code .  . . ", Toast.LENGTH_SHORT).show();
    }














    /**
     * Manage phone boot.
     * <p>
     * The phone boot has views stacked and are revealed and hidden respectively,
     * depending on what stage of  verification the phone number verification is at.
     **/


//phone view variables


    public static View phoneCode, phonePickninput; // main view of the email verifier

    public static TextView phoneAlertText, phoneButtonText, phoneStepCount, phonePayText; // textview below the section lable and the button text at the buttom

    public static Button phoneButtonMain; // fat green button in the email boot

    public static EditText phoneInput, phoneCodeInput;

    public static ImageView phoneButtonPen;

    private com.hbb20.CountryCodePicker phoneCodePicker;


    public static int phoneVerificationStateFlag = 0;


    //view getter for the address boot
    public void getPhoneSpecificViews(){

        phonePickninput = findViewById(R.id.ver_phone_pickninput);

        phonePayText = findViewById(R.id.ver_phone_payText);

        phoneCodePicker = findViewById(R.id.ver_phone_code_picker);

        phoneCode = findViewById(R.id.ver_phone_codeView);

        phoneAlertText = findViewById(R.id.ver_phone_alertText);


        phoneInput = findViewById(R.id.ver_phone_input);
        phoneInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        phoneCodeInput = findViewById(R.id.ver_phone_codeEdt);


        phoneButtonText = findViewById(R.id.ver_phone_buttonText);
        phoneButtonText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPhoneButtonTextClicked();
            }
        });

        phoneButtonMain = findViewById(R.id.ver_phone_buttonMain);
        phoneButtonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPhoneButtonMainClicked();
            }
        });

        phoneButtonPen = findViewById(R.id.ver_phone_buttonPen);


    }


    public void initializePhoneNumberVerification() {

        headPhone.setTextColor(getResources().getColor(R.color.colorHighlight));
        phoneCard.setCardBackgroundColor(getResources().getColor(R.color.colorHighlight));

        content_phone_boot.setVisibility(View.VISIBLE);

        phoneVerificationStateFlag = getPhoneVerificationStateFlag();

        switch (phoneVerificationStateFlag) {
            case 0:
                phoneAlertText.setText("A code will be sent to the number you give");
                phoneAlertText.setVisibility(View.VISIBLE);
                phonePickninput.setVisibility(View.VISIBLE);

                phoneButtonPen.setVisibility(View.GONE);
                phonePayText.setVisibility(View.GONE);
                break;
            case 1:
                phoneAlertText.setText("");

                phonePickninput.setVisibility(View.GONE);
                phonePayText.setVisibility(View.VISIBLE);

                phoneButtonMain.setText("Pay");
                phoneButtonText.setText("Cancel");
                break;
            case 2:
                phoneAlertText.setText("Enter the code sent to your phone.");
                phoneAlertText.setTextColor(getResources().getColor(R.color.pending));
                phoneCode.setVisibility(View.VISIBLE);
                phonePayText.setVisibility(View.GONE);


                phoneButtonMain.setText("Continue");
                break;

            case 3:
                phonePayText.setText("+237 659 055 124");

                phoneAlertText.setText("We could not verify this number.");
                phoneAlertText.setTextColor(getResources().getColor(R.color.warning));
                phoneAlertText.setVisibility(View.VISIBLE);

                phoneButtonPen.setVisibility(View.VISIBLE);

                phonePayText.setVisibility(View.VISIBLE);

                phoneCode.setVisibility(View.GONE);

                phoneButtonText.setVisibility(View.GONE);
                phoneButtonMain.setText("Try Again");
                break;

            default:
                phoneAlertText.setText("Your check has been completed");
                phoneAlertText.setTextColor(getResources().getColor(R.color.colordefault));
                phoneAlertText.setVisibility(View.VISIBLE);

                content_phone_boot.setVisibility(View.GONE);
                content_ident_boot.setVisibility(View.VISIBLE);

                phoneDone.setVisibility(View.VISIBLE);
                phonePosition.setVisibility(View.GONE);

                headIdent.setTextColor(getResources().getColor(R.color.colorHighlight));
                identCard.setCardBackgroundColor(getResources().getColor(R.color.colorHighlight));
                break;
        }
    }


    //method to manage the phone section label onclick

    public void onPhoneVerificationClicked(View view) {
        if (verifyPosition != 1) {
            hideCurrentView();
            verifyPosition = 1;

            initializePhoneNumberVerification();
        }
    }

    public void onPhoneButtonMainClicked(){

        setPhoneVerificationStateFlag(phoneVerificationStateFlag+1);
        initializePhoneNumberVerification();

    }

    public void onPhoneButtonTextClicked(){

        if (phoneVerificationStateFlag >0 ){
            setPhoneVerificationStateFlag(phoneVerificationStateFlag-1);
            initializePhoneNumberVerification();
        }

    }


















    /*
     *
     *Manage identity verification
     *
     *
     * */


    public static int identVerificationStateFlag = 0;

    //constant for Camera request handling
    private static final int identFront_Camera_Request = 1888;
    private static final int identBack_Camera_Request = 1889;
    private static final int identUserImage_Camera_Request = 1890;
    private static final int identUserImageAndID_Camera_Request = 1891;


    //identity view variables


    public static View identPage1, identPage2, identHidenList;

    public static TextView seeText;

    public static ImageView identToggleImage;

    public static boolean identToggleState = false;

    public static Button identUploadButton;
    public static TextView identTextButton, identAlertText;


    public static ImageView id1Img1,id1Img2,id1Img3,id1Img4; //uploadplace holders
    public static ImageView id1Info1,id1Info2,id1Info3,id1Info4; //information icons
    public static ImageView id2Image1,id2Image2,id2Image3,id2Image4; // pageTwo images
    public static ImageView id2Edit1,id2Edit2,id2Edit3,id2Edit4; //pageTwo editor pens



    //view getter for the identity boot
    public void getIdentSpecificViews(){

        identVerificationStateFlag = getIdentVerificationStateFlag();

        identHidenList = findViewById(R.id.ver_ident_hidenList); //the subset of the list for see more buttons

        seeText = findViewById(R.id.ver_ident_seeText);

        identToggleImage = findViewById(R.id.ver_ident_toggleImage);

        identPage1 = findViewById(R.id.ver_ident_page1);
        identPage2 = findViewById(R.id.ver_ident_page2);

        identUploadButton = findViewById(R.id.ver_ident_buttonMain);
        identUploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (photos[0] != null && photos[1] != null && photos[2] != null && photos[3] !=null){
                    identUploadButton.setBackground(getResources().getDrawable(R.drawable.continue_btn_background));
                    setIdentVerificationStateFlag(identVerificationStateFlag+1);
                    initializeIdentityVerification();
                }

            }
        });
        identTextButton = findViewById(R.id.ver_ident_buttonText);

        identAlertText = findViewById(R.id.ver_ident_alertText);

        id1Img1 = findViewById(R.id.ver_ident1_image1);
        id1Img2 = findViewById(R.id.ver_ident1_image2);
        id1Img3 = findViewById(R.id.ver_ident1_image3);
        id1Img4 = findViewById(R.id.ver_ident1_image4);


        id1Info1 = findViewById(R.id.ver_ident1_info1);
        id1Info2 = findViewById(R.id.ver_ident1_info2);
        id1Info3 = findViewById(R.id.ver_ident1_info3);
        id1Info4 = findViewById(R.id.ver_ident1_info4);


        id2Image1 = findViewById(R.id.ver_ident2_image1);
        id2Image2 = findViewById(R.id.ver_ident2_image2);
        id2Image3 = findViewById(R.id.ver_ident2_image3);
        id2Image4 = findViewById(R.id.ver_ident2_image4);

        id2Edit1 = findViewById(R.id.ver_ident2_edit1);
        id2Edit2 = findViewById(R.id.ver_ident2_edit2);
        id2Edit3 = findViewById(R.id.ver_ident2_edit3);
        id2Edit4 = findViewById(R.id.ver_ident2_edit4);


    }


    public static Bitmap[] photos = new Bitmap[4];

    public void initializeIdentityVerification() {

        headIdent.setTextColor(getResources().getColor(R.color.colorHighlight));
        identCard.setCardBackgroundColor(getResources().getColor(R.color.colorHighlight));


        identVerificationStateFlag = getIdentVerificationStateFlag();

        content_ident_boot.setVisibility(View.VISIBLE);
        identAlertText.setVisibility(View.VISIBLE);
        identAlertText.setText("Upload required images.");


        if (identVerificationStateFlag == 0){
            identPage1.setVisibility(View.VISIBLE);
            identPage2.setVisibility(View.GONE);
        }else if (identVerificationStateFlag == 1){
            identPage2.setVisibility(View.VISIBLE);
            identPage1.setVisibility(View.GONE);


            id2Image1.setImageBitmap(photos[0]);
            id2Image2.setImageBitmap(photos[1]);
            id2Image3.setImageBitmap(photos[2]);
            id2Image4.setImageBitmap(photos[3]);


            identUploadButton.setText("Next");
        }else if (identVerificationStateFlag == 2){

            content_ident_boot.setVisibility(View.GONE);

            content_address_boot.setVisibility(View.VISIBLE);

        }


    }


    //method to get images

    public void onUploadImageClicked(View view) {

        Intent camera = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

        int id = view.getId();

        if (id == R.id.ver_ident1_image1)
            startActivityForResult(camera, identFront_Camera_Request);
        else if (id == R.id.ver_ident1_image2)
            startActivityForResult(camera, identBack_Camera_Request);
        else if (id ==  R.id.ver_ident1_image3)
            startActivityForResult(camera, identUserImage_Camera_Request);
        else if (id ==  R.id.ver_ident1_image4)
            startActivityForResult(camera, identUserImageAndID_Camera_Request);

    }



    //methods to get image strings

    //method to manage the info pop up
    public void onIdentInfoIconClicked(View view) {

        AlertDialog.Builder adb = new AlertDialog.Builder(view.getContext());
        View dview = getLayoutInflater().inflate(R.layout.identity_verification_popup,null);
        adb.setView(dview);


        // TODO : Find the textview and swap the text depending on what is clicked.
        // use a switch statement and pass the view id as the arguement.



        AlertDialog dial = adb.create();

        adb.setCancelable(true);

        dial.show();

    }


    //method to manage the see more and see less feature of the identity verification boot
    public void onIdentListToggle(View view) {

        identToggleState = !identToggleState;

        identHidenList.setVisibility(identToggleState ? View.VISIBLE : View.GONE);

        seeText.setText(identToggleState ? "see less. . ." : "see more. . .");

        identToggleImage.setImageDrawable(getResources().getDrawable(identToggleState ?
                R.drawable.ic_drop_up : R.drawable.ic_drop_down));

    }


    //method to manage identity section label onclick listener
    public void onIdentityVerificationClicked(View view) {
        if (verifyPosition != 2) {
            hideCurrentView();
            verifyPosition = 2;

            initializeIdentityVerification();
        }
    }

    public void onEditIDImageClicked(View view) {

        Context context;
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        View mview = LayoutInflater.from(this).inflate(R.layout.ident_image_editor, null);
        adb.setView(mview);

        final AlertDialog dial = adb.create();

        ImageView img = mview.findViewById(R.id.ver_ident_ie_image);
        Button button = mview.findViewById(R.id.ver_ident_ie_button);

        final int id = view.getId();
        final Intent camera = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dial.dismiss();
                if (id == R.id.ver_ident2_edit1){
                    startActivityForResult(camera, identFront_Camera_Request);
                }else if (id == R.id.ver_ident2_edit2){
                    startActivityForResult(camera, identBack_Camera_Request);
                }else if (id == R.id.ver_ident2_edit3){
                    startActivityForResult(camera, identUserImage_Camera_Request);
                }else if (id == R.id.ver_ident2_edit4){
                    startActivityForResult(camera, identUserImageAndID_Camera_Request);
                }
            }
        });

        if (id == R.id.ver_ident2_edit1){
            img.setImageBitmap(photos[0]);
        }else if (id == R.id.ver_ident2_edit2){
            img.setImageBitmap(photos[1]);
        }else if (id == R.id.ver_ident2_edit3){
            img.setImageBitmap(photos[2]);
        }else if (id == R.id.ver_ident2_edit4){
            img.setImageBitmap(photos[3]);
        }



        dial.show();

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == identFront_Camera_Request && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            id1Img1.setImageBitmap(photo);
            id1Info1.setVisibility(View.GONE);
            photos[0]= photo;
        }else if (requestCode == identBack_Camera_Request && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            id1Img2.setImageBitmap(photo);
            id1Info2.setVisibility(View.GONE);
            photos[1]= photo;
        }else if (requestCode == identUserImage_Camera_Request && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            id1Img3.setImageBitmap(photo);
            id1Info3.setVisibility(View.GONE);
            photos[2]= photo;
        }else if (requestCode == identUserImageAndID_Camera_Request && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            id1Img4.setImageBitmap(photo);
            id1Info4.setVisibility(View.GONE);
            photos[3]= photo;
        }else if (requestCode ==  Utility_Camera_Request){
            utilityBitmap = (Bitmap) data.getExtras().get("data");
            addImageIcon.setVisibility(View.GONE);
            addInfoIcon.setVisibility(View.GONE);
            addUtitlityImage.setImageBitmap(utilityBitmap);
            addUtitlityImage.setVisibility(View.VISIBLE);
        }

        if (photos[0] != null && photos[1] != null && photos[2] != null && photos[3] !=null)
            identUploadButton.setBackground(getResources().getDrawable(R.drawable.continue_btn_background));
        else
            identUploadButton.setBackground(getResources().getDrawable(R.drawable.continue_not_btn_background));
    }















    /*
     * Manage address verification
     *
     * */

    public static int addVerificationStateFlag = 0;

    public static int Utility_Camera_Request = 2221;

    public static Bitmap utilityBitmap = null ;

    //adress view variables

    private static View addressPage1,addressPage2;

    public static TextView addAlertText;
    public static Button addMainButton,addTextButton;

    //page one views
    public static EditText addCountryInput,addRegionInput,addCityInput,addHomeInput;

    public static ImageView addInfoIcon, addImageIcon, addUtitlityImage;

    //page two variables
    private TextView addCountryText,addRegionText,addCityText,addHomeText;
    private ImageView addCountryFlag;

    //handler and switch to check that all input is inserted befor activating submit button
    public static boolean addInputComplete = false;
    public static Handler handler ;

    private static ListView addCountryList ;

    private String countryString,regionString,cityString,homeString;

    //view getter for the address boot
    public void getAddSpecificViews(){

        addressPage1 = findViewById(R.id.ver_address_page1);
        addressPage2 = findViewById(R.id.ver_address_page2);

        addAlertText = findViewById(R.id.ver_add_alertText);

        addMainButton = findViewById(R.id.ver_add_buttonMain);
        addTextButton = findViewById(R.id.ver_add_buttonText);
        addTextButton.setVisibility(View.GONE);

        addCountryInput = findViewById(R.id.ver_add_countryInput);
        addRegionInput = findViewById(R.id.ver_add_regionInput);
        addCityInput = findViewById(R.id.ver_add_cityInput);
        addHomeInput = findViewById(R.id.ver_add_homeInput);

        addInfoIcon = findViewById(R.id.ver_add_infoIcon);
        addImageIcon = findViewById(R.id.ver_add_imageIcon);
        addUtitlityImage = findViewById(R.id.ver_add_utilityImage);

        addCountryList = findViewById(R.id.ver_add_countryList);

        //page two
        addCountryFlag = findViewById(R.id.ver_add2_countryFlag);

        addRegionText = findViewById(R.id.ver_add_RegionText);
        addCityText = findViewById(R.id.ver_add_CityText);
        addHomeText = findViewById(R.id.ver_add_homeText);
        addCountryText = findViewById(R.id.ver_add_countryText);




        addCountryInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                addCountryList.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        addInfoIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                AlertDialog.Builder adb = new AlertDialog.Builder(context);

                adb.setTitle("WaZaPAY");
                adb.setMessage("What is the utitlity image. Because me and some people do not know. . .");

                AlertDialog dial =  adb.create();

                dial.show();
            }
        });

        addImageIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent camera = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(camera, Utility_Camera_Request);

            }
        });


        addMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (addInputComplete && addVerificationStateFlag == 0){
                    setAddVerificationStateFlag(addVerificationStateFlag + 1);
                    initializeAdressVerification();
                }else{}
            }
        });


    }


    public void initializeAdressVerification(){

        addVerificationStateFlag = getAddVerificationStateFlag();

        headAdd.setTextColor(getResources().getColor(R.color.colorHighlight));
        addCard.setCardBackgroundColor(getResources().getColor(R.color.colorHighlight));
        content_address_boot.setVisibility(View.VISIBLE);


        switch (addVerificationStateFlag){
            case 0:
                inputCheck();
                addressPage2.setVisibility(View.GONE);
                addressPage1.setVisibility(View.VISIBLE);
                break;
            case 1:
                addressPage2.setVisibility(View.VISIBLE);
                addressPage1.setVisibility(View.GONE);
                handler.removeCallbacksAndMessages(null);
                addUtitlityImage.setImageBitmap(utilityBitmap);
                addTextButton.setVisibility(View.VISIBLE);


                addCountryFlag.setImageResource(World.getFlagOf(countryString));


                addCountryText.setText(countryString);
                addRegionText.setText(regionString);
                addCityText.setText(cityString);
                addHomeText.setText(homeString);
                break;
        }
    }

    //method to manage address section label onclick
    public void onAddressVerificationClicked(View view) {

        if (verifyPosition != 3) {

            hideCurrentView();
            verifyPosition = 3;

            initializeAdressVerification();

        }

    }



    private void inputCheck(){
        handler =  new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {

                countryString = addCountryInput.getText().toString().trim();
                regionString = addRegionInput.getText().toString().trim();
                cityString = addCityInput.getText().toString().trim() ;
                homeString = addHomeInput.getText().toString().trim() ;


                if (TextUtils.isEmpty(countryString) ||
                        TextUtils.isEmpty(regionString) ||
                        TextUtils.isEmpty(cityString) ||
                        TextUtils.isEmpty(homeString) ||
                        utilityBitmap == null || !isCountry(countryString) ){
                    addInputComplete = false;
                    addMainButton.setBackground(getResources().getDrawable(R.drawable.continue_not_btn_background));
                }else{
                    addInputComplete = true;
                    addMainButton.setBackground(getResources().getDrawable(R.drawable.continue_btn_background));
                }

                handler.postDelayed(this,100);
            }
        });

    }


    //method to check if the specified country is valid
    private boolean isCountry(String ctr){
        int flag = World.getFlagOf(ctr);

        if (flag == World.getFlagOf("failure"))
            return false;
        else return true;
    }


















    /*
     *
     * manage Payment verification     *
     *
     * */


    private View paymMethodsList,paymPage1,paymPage2,paymPage3,paymPage4,paymPage5,paymControls;

    private int paymentVerificationStateFlag=0 ;

    private void getPaymentSpecificViews(){
        paymMethodsList = findViewById(R.id.ver_paym_methodsList);

        paymPage1 = findViewById(R.id.ver_paym_page1);

        paymPage2 = findViewById(R.id.ver_paym_page2);

        paymPage3 = findViewById(R.id.ver_paym_page3);

        paymPage4 = findViewById(R.id.ver_paym_page4);

        paymPage5 = findViewById(R.id.ver_paym_page5);

        paymControls = findViewById(R.id.ver_paym_controls);

    }


    private void initializePaymentVerification() {


        headPayment.setTextColor(getResources().getColor(R.color.colorHighlight));
        paymCard.setCardBackgroundColor(getResources().getColor(R.color.colorHighlight));
        content_payment_boot.setVisibility(View.VISIBLE);

        phoneVerificationStateFlag = getPhoneVerificationStateFlag();

        if (phoneVerificationStateFlag == 0){
            paymPage1.setVisibility(View.VISIBLE);
        }else if (phoneVerificationStateFlag == 1){
            //momo verification
            paymControls.setVisibility(View.VISIBLE);
            paymPage1.setVisibility(View.GONE);
            identPage2.setVisibility(View.VISIBLE);
        }else if (phoneVerificationStateFlag == 2){
            //confirm momo
            paymControls.setVisibility(View.VISIBLE);
            paymPage2.setVisibility(View.GONE);
            paymPage3.setVisibility(View.VISIBLE);
        }else if (phoneVerificationStateFlag == 3){
            // card verification
        }else if (phoneVerificationStateFlag == 4){

        }

    }

    //method to manage the payment section label's onclick listener
    public void onPaymentVerificationClicked(View view) {

        if (verifyPosition != 4) {

            hideCurrentView();
            verifyPosition = 4;

            initializePaymentVerification();

        }


    }



    //toggle payment methods list
    public void onPaymentMethodDropDown(View view) {

        int id = view.getId();

        if (id == R.id.ver_paym_dropdown){
            paymMethodsList.setVisibility(View.VISIBLE);
        }else{
            paymMethodsList.setVisibility(View.GONE);
        }

    }


    public void onPaymentmethodClicked(View view) {

        int id = view.getId();

        switch (id){
            case R.id.ver_paym_list_momo:
                setPhoneVerificationStateFlag(1);
                break;
            case R.id.ver_paym_list_ormo:
                setPhoneVerificationStateFlag(1);
                break;
            case R.id.ver_paym_list_card:
                setPhoneVerificationStateFlag(3);
                break;
            default:
                setPhoneVerificationStateFlag(4);
                break;
        }

    }
}
