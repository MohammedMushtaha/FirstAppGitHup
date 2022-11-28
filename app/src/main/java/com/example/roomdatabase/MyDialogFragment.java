package com.example.roomdatabase;

import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class MyDialogFragment extends androidx.fragment.app.DialogFragment {

    String header, message;
    static DeleteViewModel deleteViewModel;
    static int id;

    public static MyDialogFragment newInstance(String header, String message, Context context, int id1) {
        Bundle bundle = new Bundle();
        bundle.putString("header", header);
        bundle.putString("message", message);
        id = id1;
        deleteViewModel = new ViewModelProvider((ViewModelStoreOwner) context, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance((Application) context.getApplicationContext())).get(DeleteViewModel.class);

        MyDialogFragment dialogFragment = new MyDialogFragment();
        dialogFragment.setArguments(bundle);
        return dialogFragment;


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        header = bundle.getString("header");
        message = bundle.getString("message");


    }

//    @NonNull
//    @Override
//    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
//        alertDialog.setTitle(header);
//        alertDialog.setMessage(message);
//        alertDialog.setNeutralButton("yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                 Student student = new Student("", "", "");
//                student.setId(id);
//                deleteViewModel.delete(student);
//            }
//        });
//        return alertDialog.create();
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_dialog, container, false);

        Button button = view.findViewById(R.id.buttonDelete);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = new Student();
                student.setId(id);
                deleteViewModel.delete(student);
                dismiss();

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel notificationChannel = new NotificationChannel("idNotification", "name", NotificationManager.IMPORTANCE_DEFAULT);
                    notificationChannel.setDescription("myDescription");
                    NotificationManager notificationManager = getActivity().getSystemService(NotificationManager.class);
                    notificationManager.createNotificationChannel(notificationChannel);
                }
                NotificationCompat.Builder builderNotification = new NotificationCompat.Builder(getContext().getApplicationContext(), "idNotification");

                builderNotification.setContentText("Delete Done");
                builderNotification.setContentTitle("Delete");
                builderNotification.setSmallIcon(com.google.android.material.R.drawable.material_ic_keyboard_arrow_left_black_24dp);


                NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();

          bigPictureStyle.bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher_background));

                builderNotification.setStyle(bigPictureStyle);

                NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(getContext());
                Notification notification=builderNotification.build();
                notificationManagerCompat.notify(1,notification);
                notificationManagerCompat.notify(2,notification);
                notificationManagerCompat.notify(3,notification);


            }
        });
        return view;
    }
}