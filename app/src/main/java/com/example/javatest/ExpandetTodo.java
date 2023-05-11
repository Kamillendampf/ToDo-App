package com.example.javatest;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.javatest.databinding.ActivityExpandetTodoBinding;

public class ExpandetTodo extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityExpandetTodoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityExpandetTodoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }



}