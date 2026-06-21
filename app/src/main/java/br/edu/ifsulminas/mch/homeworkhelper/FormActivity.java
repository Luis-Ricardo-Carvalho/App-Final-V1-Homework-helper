package br.edu.ifsulminas.mch.homeworkhelper;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.widget.Button;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import br.edu.ifsulminas.mch.homeworkhelper.model.Subject;
import br.edu.ifsulminas.mch.homeworkhelper.model.Task;
import br.edu.ifsulminas.mch.homeworkhelper.model.persistence.TaskDAO;

public class FormActivity extends AppCompatActivity {

    public static final String TASK_KEY = "tarefa";
    private Task task = null;
    private Subject currentSubject = null;

    private EditText nameEditText;
    private EditText descEditText;
    private Button dateButton;
    private String selectedDateISO = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.form), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("SUBJECT_FOR_TASK")) {
            currentSubject = (Subject) intent.getSerializableExtra("SUBJECT_FOR_TASK");
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        nameEditText = findViewById(R.id.task_name);
        descEditText = findViewById(R.id.task_description);
        dateButton = findViewById(R.id.btn_date);

        if (intent != null && intent.hasExtra(TASK_KEY)) {
            task = (Task) intent.getSerializableExtra(TASK_KEY);
        }

        if (task != null) {
            nameEditText.setText(task.getName());
            descEditText.setText(task.getDescription());
            if (task.getDateSubmission() != null && !task.getDateSubmission().isEmpty()) {
                selectedDateISO = task.getDateSubmission();
                dateButton.setText(formatDateDisplay(selectedDateISO));
            }
        }

        dateButton.setOnClickListener(v -> showDatePicker());

        nameEditText.requestFocus();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_form_save, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        if (item.getItemId() == R.id.menu_edit_subject) {
            String name = nameEditText.getText().toString();
            String desc = descEditText.getText().toString();

            if (name.isBlank() || desc.isBlank() || selectedDateISO == null || selectedDateISO.isBlank()) {
                Toast.makeText(getBaseContext(),
                        "Por favor, preencha todos os campos da tarefa.",
                        Toast.LENGTH_SHORT).show();
            } else {
                boolean isInsert = false;
                if (task == null) {
                    task = new Task();
                    isInsert = true;
                }

                task.setName(name);
                task.setDescription(desc);
                task.setDateSubmission(selectedDateISO);
                task.setActive(true);

                if (isInsert && currentSubject != null) {
                    task.setSubjectId(currentSubject.getId());
                }

                TaskDAO dao = new TaskDAO(this);

                if (isInsert) {
                    dao.save(task);
                } else {
                    dao.update(task);
                }

                Toast.makeText(getBaseContext(),
                        "Tarefa salva com sucesso",
                        Toast.LENGTH_SHORT).show();

                finish();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();

        if (selectedDateISO != null && !selectedDateISO.isEmpty()) {
            try {
                SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                Date date = isoFormat.parse(selectedDateISO);
                calendar.setTime(date);
            } catch (ParseException e) {
                // mantém data atual
            }
        }

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year, month, dayOfMonth) -> {
                    Calendar selected = Calendar.getInstance();
                    selected.set(year, month, dayOfMonth);
                    SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                    selectedDateISO = isoFormat.format(selected.getTime());
                    dateButton.setText(formatDateDisplay(selectedDateISO));
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );

        datePickerDialog.show();
    }

    private String formatDateDisplay(String isoDate) {
        try {
            SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date date = isoFormat.parse(isoDate);
            SimpleDateFormat displayFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            return displayFormat.format(date);
        } catch (ParseException e) {
            return isoDate;
        }
    }
}