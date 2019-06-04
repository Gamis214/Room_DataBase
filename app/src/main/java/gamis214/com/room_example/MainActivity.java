package gamis214.com.room_example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import gamis214.com.room_example.DataBase.Entity.Word;
import gamis214.com.room_example.ViewModel.WordViewModel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private WordViewModel viewModel;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView    = findViewById(R.id.recyclerview);
        fab             = findViewById(R.id.fab);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fab.setOnClickListener(this);
        adapter = new CustomAdapter();
        recyclerView.setAdapter(adapter);
        initViewModel();
    }

    private void initViewModel(){
        viewModel = ViewModelProviders.of(this).get(WordViewModel.class);
        viewModel.getAllWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                adapter.setNewListWords(words);
            }
        });
    }

    public void addWord(Word word){
        viewModel.insert(word);
    }

    @Override
    public void onClick(View view) {
        new CustomDialog().show(getSupportFragmentManager(),"dialog");
    }
}
