package com.example.note_me_down;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilterReader;
import java.io.IOException;
import java.util.ConcurrentModificationException;

public class StickySavedNote {
    String getStick(Context context) {
        File file = new File(context.getFilesDir().getPath() + "/note.txt");
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return text.toString();
    }
     void setNote (String texttosave, Context context)
     {
         String  text= texttosave;
         FileOutputStream fout=null;
         try {
             fout=context.getApplicationContext().openFileOutput("note.txt",Context.MODE_PRIVATE);
             fout.write(text.getBytes());
         } catch (IOException e) {
             e.printStackTrace();
         } finally {
             if(fout!=null)
             {
                 try {
                     fout.close();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }
         }
     }
}
