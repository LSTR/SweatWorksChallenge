package com.lester.demo.presentation.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.ContactsContract;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.lester.demo.data.model.UserLocationE;

import java.util.Locale;

public abstract class BaseActivity extends AppCompatActivity {

    protected int CODE_ADD_CONTACT = 123;

    public void mailToUser(String email) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        i.putExtra(Intent.EXTRA_TEXT, "Hi, ");
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (ActivityNotFoundException ignored) {}
    }

    public void callToUser(String data) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+data));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 12321);
            return;
        }
        try{
            startActivity(intent);
        }catch(Exception ignored){}
    }

    public void showUserLocation(UserLocationE currentPosition) {
        String uri = String.format(Locale.ENGLISH, "geo:%f,%f", currentPosition.getCoordinates().getLatitude(), currentPosition.getCoordinates().getLongitude());
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }

    public void addContact(String name, String phone, String sec_phone, String email) {
        Intent contactIntent = new Intent(ContactsContract.Intents.Insert.ACTION);
        contactIntent.setType(ContactsContract.RawContacts.CONTENT_TYPE);

        contactIntent
                .putExtra(ContactsContract.Intents.Insert.NAME, name)
                .putExtra(ContactsContract.Intents.Insert.PHONE, phone)
                .putExtra(ContactsContract.Intents.Insert.SECONDARY_PHONE, sec_phone)
                .putExtra(ContactsContract.Intents.Insert.EMAIL, email);

        startActivityForResult(contactIntent, CODE_ADD_CONTACT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == CODE_ADD_CONTACT) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "Added Contact", Toast.LENGTH_SHORT).show();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "Cancelled Added Contact", Toast.LENGTH_SHORT).show();
            }
        }
    }
}