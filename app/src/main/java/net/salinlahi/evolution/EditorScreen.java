package net.salinlahi.evolution;

import android.content.ClipData;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EditorScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_editor_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        CardView breadboardCard = findViewById(R.id.breadboard_card);
        CardView batteryCard = findViewById(R.id.battery_card);
        CardView ledCard = findViewById(R.id.led_card);
        CardView wireCard = findViewById(R.id.wire_card);
        LinearLayout editorLayout = findViewById(R.id.editor_layout);

        this.addDragAndDropFunctionality(breadboardCard, editorLayout);
        this.addDragAndDropFunctionality(batteryCard, editorLayout);
        this.addDragAndDropFunctionality(ledCard, editorLayout);
        this.addDragAndDropFunctionality(wireCard, editorLayout);

    }

    private void addDragAndDropFunctionality(CardView card, LinearLayout dropLocation) {
        // Set the long click listener for the draggable view
        card.setOnLongClickListener(v -> {
            // Create ClipData holding the drag data (you can pass actual data here)
            ClipData dragData = ClipData.newPlainText("simple text", "DragLabel");

            // Create a drag shadow
            View.DragShadowBuilder myShadow = new View.DragShadowBuilder(card);

            // Start the drag
            v.startDragAndDrop(dragData, myShadow, null, 0);

            return true;
        });

        // Set the drag listener for the drop target
        dropLocation.setOnDragListener((v, event) -> {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // Signal that drag has started
                    return true;

                case DragEvent.ACTION_DRAG_ENTERED:
                    // Change appearance of drop target
                    v.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
                    return true;

                case DragEvent.ACTION_DRAG_EXITED:
                    // Reset appearance
                    v.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                    return true;

                case DragEvent.ACTION_DROP:
                    // Handle the drop
                    Toast.makeText(EditorScreen.this, "Dropped!!", Toast.LENGTH_SHORT).show();
                    return true;

                case DragEvent.ACTION_DRAG_ENDED:
                    // Reset appearance
                    v.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                    return true;

                default:
                    break;
            }
            return false;
        });
    }
}