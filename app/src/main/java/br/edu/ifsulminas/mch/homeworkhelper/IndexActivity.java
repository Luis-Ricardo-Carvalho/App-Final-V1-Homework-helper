package br.edu.ifsulminas.mch.homeworkhelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import br.edu.ifsulminas.mch.homeworkhelper.model.Subject;
import br.edu.ifsulminas.mch.homeworkhelper.model.Task;
import br.edu.ifsulminas.mch.homeworkhelper.model.persistence.TaskDAO;

public class IndexActivity extends AppCompatActivity {

    private ListView subject_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_index);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.index), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Setup dos componente
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> {

            Intent formIntent = new Intent(IndexActivity.this, FormSubjectActivity.class);
            startActivity(formIntent);
        });

        subject_list = findViewById(R.id.subject_list);
        registerForContextMenu(subject_list);

        subject_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Subject subject = (Subject) subject_list.getItemAtPosition(position);

                Intent formActIntent = new Intent(IndexActivity.this, FormSubjectActivity.class);

                // mudar par
                formActIntent.putExtra(FormSubjectActivity.SUBJECT_KEY, subject);

                startActivity(formActIntent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        updateTasksList();
    }

    private void updateTasksList(){
        TaskDAO dao = new TaskDAO(this);
        List<Task> tasks = dao.listAll();

        ArrayAdapter<Task> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, tasks);
        subject_list.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        //super.onCreateContextMenu(menu, v, menuInfo);
        MenuItem itemDelete = menu.add("Concluir Tarefa");

        itemDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {

                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Task task = (Task) subject_list.getItemAtPosition(info.position);

                TaskDAO dao = new TaskDAO(IndexActivity.this);
                dao.delete(task);
                updateTasksList();
                return true;
            }
        });
    }
}
