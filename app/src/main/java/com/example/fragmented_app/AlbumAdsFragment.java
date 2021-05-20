package com.example.fragmented_app;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class AlbumAdsFragment extends Fragment {


    private static final int PICK_IMAGE_REQUEST = 1;

    private Button mButtonChooseImage;
    private Button mButtonUpload;
    private List<String> mKeylist;
    private List<String> mNameslist;
    private List<String> mImageslist;
    private List<String> mPriceslist;

    // private TextView mTextViewShowUploads;
    private EditText mTxt_name, mTxt_description, mTxt_price, mTxt_iglink, mTxt_fblink,
            mTxt_linkedinlink, mTxt_websitelink, mTxt_youtubelink, mTxt_phone1,
            mTxt_phone2, mTxt_phone3, mTxt_email1, mTxt_email2, mTxt_email3;


    private EditText mTxt_name1, mTxt_name2, mTxt_name3, mTxt_name4, mTxt_name5, mTxt_name6, mTxt_name7, mTxt_name8, mTxt_name9, mTxt_name10,
            mTxt_name11, mTxt_name12, mTxt_name13, mTxt_name14, mTxt_name15, mTxt_name16;

    private EditText mPrice1, mPrice2, mPrice3, mPrice4, mPrice5, mPrice6, mPrice7, mPrice8, mPrice9, mPrice10, mPrice11, mPrice12, mPrice13, mPrice14,
            mPrice15, mPrice16;

    private ImageView mImageView1, mImageView2, mImageView3, mImageView4, mImageView5, mImageView6, mImageView7, mImageView8, mImageView9, mImageView10,
            mImageView11, mImageView12, mImageView13, mImageView14, mImageView15, mImageView16;

    private ProgressBar mProgressBar;
    private CheckBox Cbclothes, Cbfootware, Cbhardware, Cbrealestate, Cbposter, Cbvehicles, Cbsoftware;
    private Uri mImageUri1, mImageUri2, mImageUri3, mImageUri4, mImageUri5, mImageUri6, mImageUri7, mImageUri8, mImageUri9, mImageUri10, mImageUri11,
            mImageUri12, mImageUri13, mImageUri14, mImageUri15, mImageUri16;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private FirebaseAuth firebaseAuth;
    private StorageTask mUploadTask;
    private String userID;
    private Button logoutbtn;
    private TextInputEditText mTxt_whatsapplink;
    private TextInputLayout mTxt_inputlayout;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.album_ads, container, false);

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        mButtonChooseImage = getActivity().findViewById(R.id.button_choose_image_upload);
        mButtonUpload = getActivity().findViewById(R.id.button_upload_image_upload);
        //   mTextViewShowUploads = findViewById(R.id.text_view_show_uploads);
        mTxt_name1 = getActivity().findViewById(R.id.txt_title1);
        mTxt_name2 = getActivity().findViewById(R.id.txt_title12);
        mTxt_name3 = getActivity().findViewById(R.id.txt_title3);
        mTxt_name4 = getActivity().findViewById(R.id.txt_title4);
        mTxt_name5 = getActivity().findViewById(R.id.txt_title5);
        mTxt_name6 = getActivity().findViewById(R.id.txt_title6);
        mTxt_name7 = getActivity().findViewById(R.id.txt_title7);
        mTxt_name8 = getActivity().findViewById(R.id.txt_title8);
        mTxt_name9 = getActivity().findViewById(R.id.txt_title9);
        mTxt_name10 = getActivity().findViewById(R.id.txt_title10);
        mTxt_name11 = getActivity().findViewById(R.id.txt_title11);
        mTxt_name12 = getActivity().findViewById(R.id.txt_title12);
        mTxt_name13 = getActivity().findViewById(R.id.txt_title13);
        mTxt_name14 = getActivity().findViewById(R.id.txt_title14);
        mTxt_name15 = getActivity().findViewById(R.id.txt_title15);
        mTxt_name16 = getActivity().findViewById(R.id.txt_title16);


        mImageView1 = getActivity().findViewById(R.id.image_view1);
        mImageView2 = getActivity().findViewById(R.id.image_view2);
        mImageView3 = getActivity().findViewById(R.id.image_view3);
        mImageView4 = getActivity().findViewById(R.id.image_view4);
        mImageView5 = getActivity().findViewById(R.id.image_view5);
        mImageView6 = getActivity().findViewById(R.id.image_view6);
        mImageView7 = getActivity().findViewById(R.id.image_view7);
        mImageView8 = getActivity().findViewById(R.id.image_view8);
        mImageView9 = getActivity().findViewById(R.id.image_view9);
        mImageView10 = getActivity().findViewById(R.id.image_view10);
        mImageView11 = getActivity().findViewById(R.id.image_view11);
        mImageView12 = getActivity().findViewById(R.id.image_view12);
        mImageView13 = getActivity().findViewById(R.id.image_view13);
        mImageView14 = getActivity().findViewById(R.id.image_view14);
        mImageView15 = getActivity().findViewById(R.id.image_view15);
        mImageView16 = getActivity().findViewById(R.id.image_view16);

        mPrice1 = getActivity().findViewById(R.id.txt_price1);
        mPrice2 = getActivity().findViewById(R.id.txt_price2);
        mPrice3 = getActivity().findViewById(R.id.txt_price3);
        mPrice4 = getActivity().findViewById(R.id.txt_price4);
        mPrice5 = getActivity().findViewById(R.id.txt_price5);
        mPrice6 = getActivity().findViewById(R.id.txt_price6);
        mPrice7 = getActivity().findViewById(R.id.txt_price7);
        mPrice8 = getActivity().findViewById(R.id.txt_price8);
        mPrice9 = getActivity().findViewById(R.id.txt_price9);
        mPrice10 = getActivity().findViewById(R.id.txt_price10);
        mPrice11 = getActivity().findViewById(R.id.txt_price11);
        mPrice12 = getActivity().findViewById(R.id.txt_price12);
        mPrice13 = getActivity().findViewById(R.id.txt_price13);
        mPrice14 = getActivity().findViewById(R.id.txt_price14);
        mPrice15 = getActivity().findViewById(R.id.txt_price15);
        mPrice16 = getActivity().findViewById(R.id.txt_price16);

        mTxt_description = getActivity().findViewById(R.id.text_description);

        mProgressBar = getActivity().findViewById(R.id.progress_bar);

        mTxt_iglink = getActivity().findViewById(R.id.instagram_input_link);
        mTxt_fblink = getActivity().findViewById(R.id.facebook_link_input);
        mTxt_websitelink = getActivity().findViewById(R.id.website_link_input);
        mTxt_whatsapplink = getActivity().findViewById(R.id.whatsapp_link_input);
        mTxt_youtubelink = getActivity().findViewById(R.id.youtube_link_input);
        mTxt_linkedinlink = getActivity().findViewById(R.id.linkedin_link_input);
        mTxt_inputlayout = getActivity().findViewById(R.id.txtInputInputLayout);


        mTxt_phone1 = getActivity().findViewById(R.id.telephone1_input);
        mTxt_phone2 = getActivity().findViewById(R.id.telephone2_input);
        mTxt_phone3 = getActivity().findViewById(R.id.telephone3_input);

        mTxt_email1 = getActivity().findViewById(R.id.email1_input);
        mTxt_email2 = getActivity().findViewById(R.id.email2_input);
        mTxt_email3 = getActivity().findViewById(R.id.email3_input);

        logoutbtn = getActivity().findViewById(R.id.checkboxes_logoutbutton);

        mStorageRef = FirebaseStorage.getInstance().getReference("Ad_uploads");
        firebaseAuth = FirebaseAuth.getInstance();
        userID = firebaseAuth.getCurrentUser().getUid();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Ad_uploads");

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();//logout
                startActivity(new Intent(getActivity().getApplicationContext(), Login.class));
                getActivity().finish();
            }
        });

        mImageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });

        mImageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });

        mImageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });

        mImageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });

        mImageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });

        mImageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });

        mImageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });

        mImageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });

        mImageView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });

        mImageView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });

        mImageView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });

        mImageView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });

        mImageView13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });

        mImageView14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });

        mImageView15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });

        mImageView16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });


        /*mButtonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(getActivity(), "Upload in progress", Toast.LENGTH_SHORT).show();
                } else {
                    uploadFile();
                }
            }
        });*/

    }


    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK
       //         && data != null && data.getData() != null
        ) {

            mImageUri1 = data.8getData();
            mImageUri2 = data.getData();
            mImageUri3 = data.getData();
            mImageUri4 = data.getData();
            mImageUri5 = data.getData();
            mImageUri6 = data.getData();
            mImageUri7 =data.getData();
            mImageUri8 = data.getData();
            mImageUri9 = data.getData();
            mImageUri10= data.getData();
            mImageUri11 = data.getData();
            mImageUri12 = data.getData();
            mImageUri13 = data.getData();
            mImageUri14 = data.getData();
            mImageUri15 = data.getData();
            mImageUri16 = data.getData();


           /* mImageUri1 = data.getClipData().getItemAt(1).getUri();
            mImageUri2 = data.getClipData().getItemAt(2).getUri();
            mImageUri3 = data.getClipData().getItemAt(3).getUri();
            mImageUri4 = data.getClipData().getItemAt(4).getUri();
            mImageUri5 = data.getClipData().getItemAt(5).getUri();
            mImageUri6 = data.getClipData().getItemAt(6).getUri();
            mImageUri7 = data.getClipData().getItemAt(7).getUri();
            mImageUri8 = data.getClipData().getItemAt(8).getUri();
            mImageUri9 = data.getClipData().getItemAt(9).getUri();
            mImageUri10 = data.getClipData().getItemAt(10).getUri();
            mImageUri11 = data.getClipData().getItemAt(11).getUri();
            mImageUri12 = data.getClipData().getItemAt(12).getUri();
            mImageUri13 = data.getClipData().getItemAt(13).getUri();
            mImageUri14 = data.getClipData().getItemAt(14).getUri();
            mImageUri15 = data.getClipData().getItemAt(15).getUri();
            mImageUri16 = data.getClipData().getItemAt(16).getUri();
*/
            Picasso.get().load(mImageUri1).into(mImageView1);
            Picasso.get().load(mImageUri2).into(mImageView2);
            Picasso.get().load(mImageUri3).into(mImageView3);
            Picasso.get().load(mImageUri4).into(mImageView4);
            Picasso.get().load(mImageUri5).into(mImageView5);
            Picasso.get().load(mImageUri6).into(mImageView6);
            Picasso.get().load(mImageUri7).into(mImageView7);
            Picasso.get().load(mImageUri8).into(mImageView8);
            Picasso.get().load(mImageUri9).into(mImageView9);
            Picasso.get().load(mImageUri10).into(mImageView10);
            Picasso.get().load(mImageUri11).into(mImageView11);
            Picasso.get().load(mImageUri12).into(mImageView12);
            Picasso.get().load(mImageUri13).into(mImageView13);
            Picasso.get().load(mImageUri14).into(mImageView14);
            Picasso.get().load(mImageUri15).into(mImageView15);
            Picasso.get().load(mImageUri16).into(mImageView16);


        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getActivity().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile() {

        Calendar cdate = Calendar.getInstance();
        SimpleDateFormat currentdate = new SimpleDateFormat("dd-MMMM-yyyy");
        final String savedate = currentdate.format(cdate.getTime());

        Calendar ctime = Calendar.getInstance();
        SimpleDateFormat currenttime = new SimpleDateFormat("HH:mm:ss");
        final String savetime = currenttime.format(ctime.getTime());


        String time = savedate + ":" + savetime;

        /*if (mImageUri != null) {

            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis() + "." +
                    getFileExtension(mImageUri));

            mUploadTask = fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {

                                Upload upload = new Upload(
                                        mTxt_description.getText().toString().trim(), time,
                                        userID,
                                        mTxt_iglink.getText().toString().trim(),
                                        mTxt_fblink.getText().toString().trim(),
                                        mTxt_websitelink.getText().toString().trim(),
                                        mTxt_youtubelink.getText().toString().trim(),
                                        mTxt_linkedinlink.getText().toString().trim(),
                                        mTxt_inputlayout.getPrefixText().toString().concat(mTxt_whatsapplink.getEditableText().toString().trim()),
                                        mTxt_phone1.getText().toString().trim(),
                                        mTxt_phone2.getText().toString().trim(),
                                        mTxt_phone3.getText().toString().trim(),
                                        mTxt_email1.getText().toString().trim(),
                                        mTxt_email2.getText().toString().trim(),
                                        mTxt_email3.getText().toString().trim());

                                @Override
                                public void onSuccess(Uri uri) {

                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            mProgressBar.setProgress(0);
                                        }
                                    }, 500);

                                    Toast.makeText(getActivity(), "Upload successful", Toast.LENGTH_LONG).show();
                                    String uploadId1 = mDatabaseRef.push().getKey();


//                                    ArrayList<String> idslist = new ArrayList<>();

                                    mPriceslist.add(mPrice1.getText().toString().trim());
                                    mPriceslist.add(mPrice2.getText().toString().trim());
                                    mPriceslist.add(mPrice3.getText().toString().trim());
                                    mPriceslist.add(mPrice4.getText().toString().trim());
                                    mPriceslist.add(mPrice5.getText().toString().trim());
                                    mPriceslist.add(mPrice6.getText().toString().trim());
                                    mPriceslist.add(mPrice7.getText().toString().trim());
                                    mPriceslist.add(mPrice8.getText().toString().trim());
                                    mPriceslist.add(mPrice9.getText().toString().trim());
                                    mPriceslist.add(mPrice10.getText().toString().trim());
                                    mPriceslist.add(mPrice11.getText().toString().trim());
                                    mPriceslist.add(mPrice12.getText().toString().trim());
                                    mPriceslist.add(mPrice13.getText().toString().trim());
                                    mPriceslist.add(mPrice14.getText().toString().trim());
                                    mPriceslist.add(mPrice15.getText().toString().trim());
                                    mPriceslist.add(mPrice16.getText().toString().trim());

                                    mNameslist.add(mTxt_name1.getText().toString().trim());
                                    mNameslist.add(mTxt_name2.getText().toString().trim());
                                    mNameslist.add(mTxt_name3.getText().toString().trim());
                                    mNameslist.add(mTxt_name4.getText().toString().trim());
                                    mNameslist.add(mTxt_name5.getText().toString().trim());
                                    mNameslist.add(mTxt_name6.getText().toString().trim());
                                    mNameslist.add(mTxt_name7.getText().toString().trim());
                                    mNameslist.add(mTxt_name8.getText().toString().trim());
                                    mNameslist.add(mTxt_name9.getText().toString().trim());
                                    mNameslist.add(mTxt_name10.getText().toString().trim());
                                    mNameslist.add(mTxt_name11.getText().toString().trim());
                                    mNameslist.add(mTxt_name12.getText().toString().trim());
                                    mNameslist.add(mTxt_name13.getText().toString().trim());
                                    mNameslist.add(mTxt_name14.getText().toString().trim());
                                    mNameslist.add(mTxt_name15.getText().toString().trim());
                                    mNameslist.add(mTxt_name16.getText().toString().trim());


                                    String uri1 = uri.toString();
                                    String uri2 = uri.toString();
                                    String uri3 = uri.toString();
                                    String uri4 = uri.toString();
                                    String uri5 = uri.toString();
                                    String uri6 = uri.toString();
                                    String uri7 = uri.toString();
                                    String uri8 = uri.toString();
                                    String uri9 = uri.toString();
                                    String uri10 = uri.toString();
                                    String uri11 = uri.toString();
                                    String uri12 = uri.toString();
                                    String uri13 = uri.toString();
                                    String uri14 = uri.toString();
                                    String uri15 = uri.toString();
                                    String uri16 = uri.toString();

                                    mImageslist.add(uri1);
                                    mImageslist.add(uri2);
                                    mImageslist.add(uri3);
                                    mImageslist.add(uri4);
                                    mImageslist.add(uri5);
                                    mImageslist.add(uri6);
                                    mImageslist.add(uri7);
                                    mImageslist.add(uri8);
                                    mImageslist.add(uri9);
                                    mImageslist.add(uri10);
                                    mImageslist.add(uri11);
                                    mImageslist.add(uri12);
                                    mImageslist.add(uri13);
                                    mImageslist.add(uri14);
                                    mImageslist.add(uri15);
                                    mImageslist.add(uri16);


                                    Upload uploads2 = new Upload(mNameslist, mPriceslist, mImageslist);


                                    for (String key : mKeylist) {

                                        key = mDatabaseRef.push().getKey();
                                        mDatabaseRef.child(key).setValue(uploads2);
                                    }

                                    mDatabaseRef.child(uploadId1).setValue(upload);

                                }

                            });

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            mProgressBar.setProgress((int) progress);
                        }
                    });

        } else {
            Toast.makeText(getActivity(), "No file selected", Toast.LENGTH_SHORT).show();
        }
        */

    /*private void openImagesActivity() {
        Intent intent = new Intent(this, UploadImage.class);
        startActivity(intent);
    }
*/
    }

}