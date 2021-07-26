package com.example.note_me_down;

import androidx.appcompat.app.AppCompatActivity;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
     private EditText noteEdt;
     private Button increasesize, reducesize,bold,italic,underline;
     private TextView sizetext;
     float currsize;
    StickySavedNote note= new StickySavedNote();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noteEdt= findViewById(R.id.idedit);
        increasesize=findViewById(R.id.increasesize);
        reducesize=findViewById(R.id.reducesize);
        bold=findViewById(R.id.idbold);
        italic=findViewById(R.id.iditalic);
        underline=findViewById(R.id.idunderline);
        sizetext=findViewById(R.id.idtextsize);
        currsize = noteEdt.getTextSize();
       sizetext.setText(""+currsize);
//       noteEdt.setText(note.getStick(this));

        increasesize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             sizetext.setText(""+currsize);
             currsize++;
             noteEdt.setTextSize(currsize);
            }
        });
        reducesize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               sizetext.setText(""+currsize);
               currsize--;
               noteEdt.setTextSize(currsize);
            }
        });
        italic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)  {
                bold.setTextColor(getResources().getColor(R.color.white));
                bold.setBackgroundColor(getResources().getColor(R.color.purple_200));
                if(noteEdt.getTypeface().isItalic()){
                    noteEdt.setTypeface(Typeface.DEFAULT);
                    italic.setTextColor(getResources().getColor(R.color.white));
                    italic.setBackgroundColor(getResources().getColor(R.color.purple_200));
                }else
                {
                    italic.setTextColor(getResources().getColor(R.color.purple_200));
                    italic.setTextColor(getResources().getColor(R.color.white));
                    noteEdt.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
                }


            }
        });
        bold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               italic.setTextColor(getResources().getColor(R.color.white));
               italic.setBackgroundColor(getResources().getColor(R.color.purple_200));
               if(noteEdt.getTypeface().isBold())
               {
                   noteEdt.setTypeface(Typeface.DEFAULT);
                   bold.setTextColor(getResources().getColor(R.color.white));
                   bold.setBackgroundColor(getResources().getColor(R.color.purple_200));

               }
               else
               {
                   bold.setTextColor(getResources().getColor(R.color.purple_200));
                   bold.setBackgroundColor(getResources().getColor(R.color.white));
                   noteEdt.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
               }
            }
        });
        underline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(noteEdt.getPaintFlags()==0)
                {
                    underline.setTextColor(getResources().getColor(R.color.white));
                    underline.setBackgroundColor(getResources().getColor(R.color.purple_200));
                    noteEdt.setPaintFlags(noteEdt.getPaintFlags() & (+Paint.UNDERLINE_TEXT_FLAG));
                }else{
                    underline.setTextColor(getResources().getColor(R.color.purple_200));
                    underline.setBackgroundColor(getResources().getColor(R.color.white));
                    noteEdt.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
                }
            }
        });
    }

    public void saveButton(View view) {
        note.setNote(noteEdt.getText().toString(),this);
        updateWidget();
        Toast.makeText(this,"Note Saved!!", Toast.LENGTH_SHORT).show();
    }
    private void updateWidget(){
        AppWidgetManager appWidgetManager= AppWidgetManager.getInstance(this);
        RemoteViews remoteViews= new RemoteViews(this.getPackageName(),R.layout.widget_layout);
        ComponentName theWidget = new ComponentName(this, AppWidget.class);
        remoteViews.setTextViewText(R.id.idTextWidget,noteEdt.getText().toString());
        appWidgetManager.updateAppWidget(theWidget,remoteViews);
    }
}