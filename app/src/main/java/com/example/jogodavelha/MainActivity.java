package com.example.jogodavelha;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button[][] buttons = new Button[3][3];
    private boolean player = true;
    private int cont = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout gridLayout = findViewById(R.id.gridLayout);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onGridButtonClick((Button) v);
                    }
                });
            }
        }

        Button resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
    }

    private void onGridButtonClick(Button button) {
        if (!button.getText().toString().equals("")) {
            return;
        }
        if (player) {
            button.setText("X");
        } else {
            button.setText("O");
        }

        cont++;

        if (Checar()) {
            if (player) {
                Toast.makeText(this, "Jogador 1 venceu!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Jogador 2 venceu!", Toast.LENGTH_SHORT).show();
            }

        } else if (cont == 9) {
            Toast.makeText(this, "Empate!", Toast.LENGTH_SHORT).show();
        }
        else {
            player = !player;
        }
    }

    private boolean Checar() {
        String[][] aux = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                aux[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (aux[i][0].equals(aux[i][1]) && aux[i][0].equals(aux[i][2]) && !aux[i][0].equals("")) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (aux[0][i].equals(aux[1][i]) && aux[0][i].equals(aux[2][i]) && !aux[0][i].equals("")) {
                return true;
            }
        }

        if (aux[0][0].equals(aux[1][1]) && aux[0][0].equals(aux[2][2]) && !aux[0][0].equals("")) {
            return true;
        }

        return aux[0][2].equals(aux[1][1]) && aux[0][2].equals(aux[2][0]) && !aux[0][2].equals("");
    }


    private void reset() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        player = true;
        cont = 0;
    }
}
