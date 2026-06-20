package br.edu.ifsulminas.mch.homeworkhelper;

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

import br.edu.ifsulminas.mch.homeworkhelper.model.Subject;
import br.edu.ifsulminas.mch.homeworkhelper.model.persistence.SubjectDAO;

public class FormSubjectActivity extends AppCompatActivity {

    public static final String SUBJECT_KEY = "subject";
    private Subject subject = null;

    private EditText nameEditText;
    private EditText teacherEditText;
    private EditText schoolYearEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form_subject);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.form_subject), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent calingIntent = getIntent();
        subject = (Subject) calingIntent.getSerializableExtra(SUBJECT_KEY);

        nameEditText = findViewById(R.id.subject_name);
        teacherEditText = findViewById(R.id.subject_teacher);
        schoolYearEditText = findViewById(R.id.subject_schoolYear);

        if (subject != null) {
            nameEditText.setText(subject.getName());
            teacherEditText.setText(subject.getTeacher());
            schoolYearEditText.setText(subject.getSchoolYear());
        }

        nameEditText.requestFocus();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_form,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.menu_edit_subject){
            String name = nameEditText.getText().toString();
            String teacher = teacherEditText.getText().toString();
            String schoolYear = schoolYearEditText.getText().toString();

            if ("".equals(name) || name.isBlank()){
                Toast.makeText(getBaseContext(),
                        "O nome da Diciplina não pode ser vazia",
                        Toast.LENGTH_SHORT).show();
            } else if ("".equals(teacher) || teacher.isBlank()){
                Toast.makeText(getBaseContext(),
                        "O Profesor da Diciplina não pode ser vazio",
                        Toast.LENGTH_SHORT).show();
            } else if ("".equals(schoolYear) || schoolYear.isBlank()) {
                Toast.makeText(getBaseContext(),
                        "A Escolaridade da Diciplina não pode ser vazia",
                        Toast.LENGTH_SHORT).show();
            }else {
                boolean isInsert = false;
                if(subject == null) {
                    subject = new Subject();
                    isInsert = true;
                }

                subject.setName(name);
                subject.setTeacher(teacher);
                subject.setSchoolYear(schoolYear);

                SubjectDAO dao = new SubjectDAO(this);

                if (isInsert)
                    dao.save(subject);
                else dao.update(subject);

                Toast.makeText(getBaseContext(),
                        "Diciplina salva com sucesso",
                        Toast.LENGTH_SHORT).show();

                finish();
            }
        }

        return super.onOptionsItemSelected(item);
    }
}