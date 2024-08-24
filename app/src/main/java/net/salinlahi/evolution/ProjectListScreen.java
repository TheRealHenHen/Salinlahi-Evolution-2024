package net.salinlahi.evolution;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.DialogInterface;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ProjectListScreen extends AppCompatActivity {

    private LinearLayout projectListContainer;
    private int projectCount = 0; // Counter for project numbers

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_project_list_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        projectListContainer = findViewById(R.id.projectListContainer);

        FloatingActionButton newProjectButton = findViewById(R.id.newProjectButton);
        newProjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewProject();
            }
        });
    }

    private void addNewProject() {
        // Increment the project count
        projectCount++;

        // Create a new horizontal LinearLayout to hold the project name, play button, and menu button
        LinearLayout newProjectLayout = new LinearLayout(this);
        newProjectLayout.setOrientation(LinearLayout.HORIZONTAL);
        newProjectLayout.setGravity(Gravity.CENTER_VERTICAL); // Center items vertically
        newProjectLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newProjectLayout.setPadding(25, 20, 25, 20);

        // Create a TextView for the project name
        TextView newProjectView = new TextView(this);
        newProjectView.setText("Project " + projectCount); // Set project name with counter
        newProjectView.setTextSize(25);
        newProjectView.setLayoutParams(new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1));  // Weight 1 to take up the available space

        // Create a Button or ImageButton for the play icon
        ImageButton playButton = new ImageButton(this);
        playButton.setImageResource(android.R.drawable.ic_media_play); // Use a play icon
        playButton.setBackgroundColor(Color.TRANSPARENT); // Make the button background transparent
        playButton.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        // Create an ImageButton for the three-dot menu
        ImageButton menuButton = new ImageButton(this);
        menuButton.setImageResource(android.R.drawable.ic_menu_more); // Use a three-dot menu icon
        menuButton.setBackgroundColor(Color.TRANSPARENT);
        menuButton.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        // Set an OnClickListener for the menu button to show a PopupMenu
        menuButton.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(ProjectListScreen.this, menuButton);
            MenuInflater inflater = popupMenu.getMenuInflater();
            inflater.inflate(R.menu.project_menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(item -> {
                int id = item.getItemId();
                if (id == R.id.rename) {
                    showRenameDialog(newProjectView);
                    return true;
                } else if (id == R.id.delete) {
                    projectListContainer.removeView(newProjectLayout);
                    return true;
                } else {
                    return false;
                }
            });
            popupMenu.show();
        });

        // Add the TextView, Play Button, and Menu Button to the LinearLayout
        newProjectLayout.addView(newProjectView);
        newProjectLayout.addView(playButton);
        newProjectLayout.addView(menuButton);

        // Add the new LinearLayout to the container
        projectListContainer.addView(newProjectLayout);
    }


    private void showRenameDialog(TextView projectView) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Rename Project");

        // Set up the input
        final EditText input = new EditText(this);
        input.setText(projectView.getText().toString());
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                projectView.setText(input.getText().toString());
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
}
