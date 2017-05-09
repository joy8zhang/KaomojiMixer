package com.example.joysenpai.kaomoji;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.icu.text.DecimalFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.joysenpai.kaomoji.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ImageView appImageView;
    private Drawable drawable;
    private Drawable [] drawables = null;

    private double valueOne = Double.NaN;
    private double valueTwo;

    private static final char ADDITION = '+';
    private static final char SUBTRACTION = '-';
    private static final char MULTIPLICATION = '*';
    private static final char DIVISION = '/';

    private char CURRENT_ACTION;


    private DecimalFormat decimalFormat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        decimalFormat = new DecimalFormat("#.##########");


        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        // Set up ImageView
        appImageView = (ImageView) findViewById(R.id.imageCry);
        drawables = new Drawable[] {
                getResources().getDrawable(R.drawable.cry) // images are added here
        };


        /* Kaomoji Components */

        /* Arm and Face Components */

        //(//

        binding.buttonFace1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "(");
            }
        });

        //)//

        binding.buttonFace2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + ")");
            }
        });

        //ヽ//
        binding.buttonArm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "\u30fd");
            }
        });

        //ノ//
        binding.buttonArm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "\uff89");
            }
        });



        /* Eye Components */

        //ಠ//

        binding.buttonEye1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "\u0CA0");
            }
        });

        //ಥ//

        binding.buttonEye2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "\u0ca5");
            }
        });



        //◕//

        binding.buttonEye3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "\u25D5");
            }
        });

        //╥//

        binding.buttonEye4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "\u2565");
            }
        });

        /* Mouth Components */


        //∀//

        binding.buttonMouth1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "\u2200");
            }
        });

        //ω//

        binding.buttonMouth2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "\u03C9");
            }
        });

        //ᴥ//

        binding.buttonMouth3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "\u1d25");
            }
        });

        // Д//
        binding.buttonMouth4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "\u0414");
            }
        });

        /* Decorative Components */

        // o//
        binding.buttonDeco1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "o");
            }
        });

        // *//
        binding.buttonDeco2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "*");
            }
        });


        // ▽//
        binding.buttonDeco3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "\u25bd");
            }
        });



        /* Features */

        // When Add button is pressed, activate addImage helper
        binding.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                CURRENT_ACTION = ADDITION;
                addImage();

            }
        });

        // When Make button is pressed, output text from editText to infoTextView
        // (aka on calculator screen)
        binding.buttonMake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.infoTextView.setText(binding.editText.getText());


            }
        });

        // When Clear button is pressed, clear everything including anything from
        // editText, infoTextView and images
        binding.buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.editText.setText("");
                binding.infoTextView.setText("");
                appImageView.setImageDrawable(null);


            }
        });

        // When Del button is pressed, delete sequence of characters in editText
        // one by one until either mistake or every character is removed
        binding.buttonDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.editText.getText().length() > 0) {
                    CharSequence currentText = binding.editText.getText();
                    binding.editText.setText(currentText.subSequence(0, currentText.length() - 1)); //remove one char at time

                } else { // clear everything
                    binding.editText.setText("");
                    binding.infoTextView.setText("");
                }

            }

        });

        // copy
        binding.buttonCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("copied", binding.infoTextView.getText());
                clipboard.setPrimaryClip(clip);

            }

        });

    }
    /* When Add button is pressed with specific kaomojis, an image appears. */

    private void addImage() {

        //Outputs a crying image when button Add is clicked with kaomoji containing crying 'T' unicode

        if (CURRENT_ACTION == ADDITION && binding.editText.getText().toString().contains("\u2565")) {

                appImageView.setImageDrawable(getResources().getDrawable(R.drawable.cry));

        } else {
            binding.infoTextView.setText("NO IMAGE FOUND");
        }


    }


}
