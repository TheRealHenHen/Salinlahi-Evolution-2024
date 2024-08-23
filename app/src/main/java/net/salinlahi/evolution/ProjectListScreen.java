package net.salinlahi.evolution;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProjectListScreen extends AppCompatActivity {

    private LinearLayout projectListContainer;

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

        Button newProjectButton = findViewById(R.id.newProjectButton);
        newProjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewProject();
            }
        });
    }

    private void addNewProject() {
        // Create a new TextView or any other view to represent a project
        TextView newProjectView = new TextView(this);
        newProjectView.setText("New Project");
        newProjectView.setTextSize(18);
        newProjectView.setPadding(16, 16, 16, 16);

        // Optionally, set an OnClickListener if you want to handle clicks on each project
        newProjectView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event, e.g., open the project, etc.
            }
        });

        // Add the new view to the container
        projectListContainer.addView(newProjectView);
    }
}
