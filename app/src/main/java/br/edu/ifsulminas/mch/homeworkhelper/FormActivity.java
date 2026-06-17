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

import br.edu.ifsulminas.mch.homeworkhelper.model.Task;
import br.edu.ifsulminas.mch.homeworkhelper.model.persistence.TaskDAO;

public class FormActivity  extends AppCompatActivity {

    public static final String TASK_KEY = "tarefa";
    private Task task = null;

    private EditText descEditText;

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

        // Suporte a Action bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent calingIntent = getIntent();
        task = (Task) calingIntent.getSerializableExtra(TASK_KEY);

        descEditText = findViewById(R.id.task_description);

        if (task != null)
            descEditText.setText(task.getDescription());

        descEditText.requestFocus();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_form,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.menu_save_task){
            String desc = descEditText.getText().toString();

            if ("".equals(desc) || desc.isBlank()){
                Toast.makeText(getBaseContext(),
                        "Descrição da tarefa não pode ser vazia",
                        Toast.LENGTH_SHORT).show();
            } else {
                boolean isInsert = false;
                if(task == null) {
                    task = new Task();
                    isInsert = true;
                }

                task.setDescription(desc);
                task.setActive(true);

                TaskDAO dao = new TaskDAO(this);

                if (isInsert)
                    dao.save(task);
                else dao.update(task);

                Toast.makeText(getBaseContext(),
                        "Tarefa salva com sucesso",
                        Toast.LENGTH_SHORT).show();

                finish();
            }
        }

        return super.onOptionsItemSelected(item);
    }
}