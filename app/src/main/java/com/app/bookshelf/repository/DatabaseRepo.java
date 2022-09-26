package com.app.bookshelf.repository;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.app.bookshelf.model.MyStoriesResult;
import com.app.bookshelf.model.MyUsers;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lobna Babker on 9/8/2022.
 */
public class DatabaseRepo {
    DatabaseReference  mDatabaseReferance ,mReference ,mrefDatabase,ref;
    FirebaseAuth mAuth  = FirebaseAuth.getInstance();
    String  user_id ;
    private StorageReference mStorageRef;
    private ArrayList<MyStoriesResult> mList;
    private ArrayList<MyUsers> List;
    MutableLiveData<List<MyStoriesResult>> listMutableLiveData ;
    public MutableLiveData<List<MyUsers>> UserlistMutableLiveData;

    public DatabaseRepo() {
        listMutableLiveData = new MutableLiveData<>();
        UserlistMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<MyUsers>> getUserlistMutableLiveData() {
        return UserlistMutableLiveData;
    }

    public MutableLiveData<List<MyStoriesResult>> getListMutableLiveData() {
        return listMutableLiveData;
    }

    //get Favoraite
    public void getFavoritesArticle(String id){
        mList = new ArrayList();
        mrefDatabase = FirebaseDatabase.getInstance().getReference("Users").child(id).child("MyFavorite");
        mrefDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    MyStoriesResult result = new MyStoriesResult();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        result.setTitle(snapshot.child("title").getValue().toString());
                        result.setUrl(snapshot.child("url").getValue().toString());
                        result.setByline(snapshot.child("author").getValue().toString());
                        result.setPublished_date(snapshot.child("publishDate").getValue().toString());
                        result.setMedia(snapshot.child("image").getValue().toString());
                        mList.add(result);
                        listMutableLiveData.setValue(mList);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    //add FavoriteArticle to database
    public void addMyFavoriteToDatabase(String sectionName , String url ,String title ,String author ,String date,String image ){
        user_id = mAuth.getCurrentUser().getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference  ref  = database.getReference("Users").child(user_id).child("MyFavorite").push();
        String pushedId = ref.getKey();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Map<String, Object> FaValues = new HashMap<>();
                FaValues.put("sectionName", sectionName);
                FaValues.put("url", url);
                FaValues.put("title", title);
                FaValues.put("author", author);
                FaValues.put("image",image);
                FaValues.put("id",pushedId);
                FaValues.put("publishDate",date);
                FaValues.put("isFavorite","yes");
                ref.updateChildren(FaValues);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    //add user to firebase
    public void addUserToDatabase(String email, String name, String id, String gender, Uri imagePath){
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        mDatabaseReferance  = database.getReference("Users").child(id);
        mDatabaseReferance.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Map<String, Object> userValues = new HashMap<>();
                userValues.put("fullName" ,name );
                userValues.put("userId",id);
                userValues.put("email" , email);
                userValues.put("gender",gender);
                mDatabaseReferance.updateChildren(userValues);
                if(imagePath != null) {
                   UploadProfileImageTofirebaseStorage(imagePath, id);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
    //Upload user profile picture to Storage
    public  void UploadProfileImageTofirebaseStorage(Uri imagePath ,String id) {
        user_id = mAuth.getCurrentUser().getUid();
        mStorageRef = FirebaseStorage.getInstance().getReference("users").child(user_id);
        if (imagePath != null) {
            UploadTask uploadTask = mStorageRef.putFile(imagePath);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Task<Uri> downloadUrl = mStorageRef.getDownloadUrl();

                    downloadUrl.addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String profileImageURL = uri.toString();
                            SaveimageToFirebaseDatabase( profileImageURL , id);

                        }
                    });

                }
            });
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.i("Login", "upload:failure", e);
                }
            });
        }

    }

    public void SaveimageToFirebaseDatabase( String profileImageURL,String id) {
        mReference = FirebaseDatabase.getInstance().getReference("Users").child(id);
        Map<String, Object> userValues = new HashMap<>();
        userValues .put("profileImageURL", profileImageURL);
        mReference.updateChildren(userValues);

    }

    public void getUserInfo(String id){
        List= new ArrayList<>();
        ref = FirebaseDatabase.getInstance().getReference("Users").child(id);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    MyUsers user = new MyUsers();
                    user.setName(snapshot.child("fullName").getValue().toString());
                    user.setEmail(snapshot.child("email").getValue().toString());
                    user.setImage(snapshot.child("profileImageURL").getValue().toString());
                    List.add(user);
                    UserlistMutableLiveData.setValue(List);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}
