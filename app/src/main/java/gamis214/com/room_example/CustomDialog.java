package gamis214.com.room_example;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import gamis214.com.room_example.DataBase.Entity.Word;
import gamis214.com.room_example.ViewModel.WordViewModel;

public class CustomDialog extends DialogFragment implements View.OnClickListener {

    private EditText editText;
    private Button btn;
    private WordViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.custom_dialog,container,false);
        editText    = view.findViewById(R.id.editText);
        btn         = view.findViewById(R.id.btn);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        ((MainActivity)getActivity()).addWord(new Word(editText.getText().toString()));
        dismiss();
    }
}
