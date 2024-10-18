package com.example.sendulamark_kopapirollo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button buttonRock;
    private Button buttonPaper;
    private Button buttonScissors;
    private int player;
    private int ai;
    private int playerScore;
    private int aiScore;
    private Random random;
    private AlertDialog alertDialog;
    private ImageView playerPick;
    private ImageView aiPick;
    private TextView textViewResult;

    //EXTRÁK
    private TextView drawTextView;
    private int drawCounter;
    private ImageView aiImageViewHeart1;
    private ImageView aiImageViewHeart2;
    private ImageView aiImageViewHeart3;
    private ImageView playerImageViewHeart1;
    private ImageView playerImageViewHeart2;
    private ImageView playerImageViewHeart3;
    private ImageView imageViewRockClick;
    private ImageView imageViewPaperClick;
    private ImageView imageViewScissorsClick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();

        imageViewRockClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkWin(0);
            }
        });

        imageViewPaperClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkWin(1);
            }
        });

        imageViewScissorsClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkWin(2);
            }
        });

//        buttonRock.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                checkWin(0);
//            }
//        });
//
//        buttonPaper.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                checkWin(1);
//            }
//        });
//
//        buttonScissors.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                checkWin(2);
//            }
//        });
    }

    public void init() {
        player = 0;
        ai = 0;
        playerScore = 0;
        aiScore = 0;
        drawCounter = 0;
        random = new Random();
        playerPick = findViewById(R.id.imageViewPlayerPick);
        aiPick = findViewById(R.id.imageViewAiPick);
        //buttonRock = findViewById(R.id.buttonRock);
        //buttonPaper = findViewById(R.id.buttonPaper);
        //buttonScissors = findViewById(R.id.buttonScissors);

        //EXTRÁK
        imageViewRockClick = findViewById(R.id.imageViewRockClick);
        imageViewPaperClick = findViewById(R.id.imageViewPaperClick);
        imageViewScissorsClick = findViewById(R.id.imageViewScissorsClick);
        drawTextView = findViewById(R.id.drawTextView);
        aiImageViewHeart1 = findViewById(R.id.aiImageViewHeart1);
        aiImageViewHeart2 = findViewById(R.id.aiImageViewHeart2);
        aiImageViewHeart3 = findViewById(R.id.aiImageViewHeart3);
        playerImageViewHeart1 = findViewById(R.id.playerImageViewHeart1);
        playerImageViewHeart2 = findViewById(R.id.playerImageViewHeart2);
        playerImageViewHeart3 = findViewById(R.id.playerImageViewHeart3);


        //textViewResult = findViewById(R.id.textViewResult);
        if (playerScore >= aiScore) {
            alertDialog = new AlertDialog.Builder(this)
                    .setTitle("Győzelem")
                    .setMessage("Szeretne új játékot kezdeni?")
                    .setPositiveButton("IGEN", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            newGame();
                        }
                    }).setNegativeButton("NEM", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    }).setCancelable(false).create();
        } else {
            alertDialog = new AlertDialog.Builder(this)
                    .setTitle("Vereség")
                    .setMessage("Szeretne új játékot kezdeni?")
                    .setPositiveButton("IGEN", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            newGame();
                        }
                    }).setNegativeButton("NEM", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    }).setCancelable(false).create();
        }
    }

    public void checkWin(int playerNum) {

        player = playerNum;
        setPlayerImage(player);
        ai = random.nextInt(3);
        setAiImage(ai);
        if (player == ai) {
            Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();
            drawCounter++;
            drawTextView.setText(String.format("Döntetlenek száma: %d", drawCounter));
        } else if (player == 0 && ai == 2 || player == 1 && ai == 0 || player == 2 && ai == 1) {
            Toast.makeText(this, "Nyertél!", Toast.LENGTH_SHORT).show();
            playerScore++;
            //textViewResult.setText("Eredmény: Ember: %d, " Computer: %d", playerScore, aiScore);
            if (playerScore == 1) {
                aiImageViewHeart1.setImageResource(R.drawable.heart1);
            } else if (playerScore == 2) {
                aiImageViewHeart2.setImageResource(R.drawable.heart1);
            } else if (playerScore == 3) {
                aiImageViewHeart3.setImageResource(R.drawable.heart1);
            }

        } else {
            Toast.makeText(this, "A gép nyert!", Toast.LENGTH_SHORT).show();
            aiScore++;
            //textViewResult.setText("Eredmény: Ember: %d, " Computer: %d", playerScore, aiScore);
            if (aiScore == 1) {
                playerImageViewHeart1.setImageResource(R.drawable.heart1);
            } else if (aiScore == 2) {
                playerImageViewHeart2.setImageResource(R.drawable.heart1);
            } else if (aiScore == 3) {
                playerImageViewHeart3.setImageResource(R.drawable.heart1);
            }
        }

        if (playerScore == 3 || aiScore == 3) {
            alertDialog.show();

        }
    }

    private void setPlayerImage(int player) {
        switch (player) {
            case 0:
                playerPick.setImageResource(R.drawable.rock);
                break;
            case 1:
                playerPick.setImageResource(R.drawable.paper);
                break;
            case 2:
                playerPick.setImageResource(R.drawable.scissors);
                break;
        }
    }

    private void setAiImage(int ai) {
        switch (ai) {
            case 0:
                aiPick.setImageResource(R.drawable.rock);
                break;
            case 1:
                aiPick.setImageResource(R.drawable.paper);
                break;
            case 2:
                aiPick.setImageResource(R.drawable.scissors);
                break;
        }
    }


    public void newGame() {
        player = 0;
        ai = 0;
        playerScore = 0;
        aiScore = 0;
        textViewResult.setText("");
        playerPick.setImageResource(R.drawable.rock);
        aiPick.setImageResource(R.drawable.rock);
    }
}