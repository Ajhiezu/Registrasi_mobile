package com.ajhiezu.registrasi;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private EditText txtTgl;
    private EditText txtFullname;
    private EditText txtUsername;
    private EditText txtEmail;
    private EditText txtPassword;
    private EditText txtConfirmPsw;
    private EditText txtPhone;
    private EditText txtAlamat;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ambil Spinner dari layout
        Spinner spinner = findViewById(R.id.listView);

        // Buat array dengan isi "Female" dan "Male"
        String[] genderList = {"Female", "Male"};

        // Buat ArrayAdapter menggunakan layout sederhana untuk spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                genderList
        );

        // Set dropdown layout style
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set adapter ke Spinner
        spinner.setAdapter(adapter);

        // Ambil EditText untuk semua input
        txtTgl = findViewById(R.id.txt_tgl);
        txtFullname = findViewById(R.id.txt_fullname);
        txtUsername = findViewById(R.id.txt_username);
        txtEmail = findViewById(R.id.txt_email);
        txtPassword = findViewById(R.id.txt_password);
        txtConfirmPsw = findViewById(R.id.txt_confirmpsw);
        txtPhone = findViewById(R.id.txt_nohp);
        txtAlamat = findViewById(R.id.txt_alamat);

        // Set OnClickListener untuk menampilkan DatePickerDialog
        txtTgl.setOnClickListener(view -> showDatePickerDialog());

        // Inisialisasi dan set OnClickListener untuk tombol
        btnSubmit = findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(view -> {
            // Ambil nilai dari EditText
            String fullname = txtFullname.getText().toString().trim();
            String username = txtUsername.getText().toString().trim();
            String email = txtEmail.getText().toString().trim();
            String password = txtPassword.getText().toString().trim();
            String confirmPassword = txtConfirmPsw.getText().toString().trim();
            String tanggalLahir = txtTgl.getText().toString().trim();
            String phone = txtPhone.getText().toString().trim();
            String alamat = txtAlamat.getText().toString().trim();

            boolean isValid = true; // Flag untuk mengecek semua input

            // Cek jika ada kolom yang kosong dan tampilkan pesan error
            if (fullname.isEmpty()) {
                txtFullname.setError("Harap diisi kolom ini");
                isValid = false;
            }
            if (username.isEmpty()) {
                txtUsername.setError("Harap diisi kolom ini");
                isValid = false;
            }
            if (email.isEmpty()) {
                txtEmail.setError("Harap diisi kolom ini");
                isValid = false;
            }
            if (password.isEmpty()) {
                txtPassword.setError("Harap diisi kolom ini");
                isValid = false;
            }
            if (confirmPassword.isEmpty()) {
                txtConfirmPsw.setError("Harap diisi kolom ini");
                isValid = false;
            }
            if (tanggalLahir.isEmpty()) {
                txtTgl.setError("Harap diisi kolom ini");
                isValid = false;
            }
            if (phone.isEmpty()) {
                txtPhone.setError("Harap diisi kolom ini");
                isValid = false;
            }
            if (alamat.isEmpty()) {
                txtAlamat.setError("Harap diisi kolom ini");
                isValid = false;
            }

            // Jika semua kolom terisi, lanjutkan ke aktivitas berikutnya
            if (isValid) {
                // Tampilkan Toast dengan pesan selamat datang
                String message = "Selamat datang " + fullname + " dengan E-mail " + email + " , " + tanggalLahir;
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();

                // Arahkan ke activity login setelah tombol diklik
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showDatePickerDialog() {
        // Ambil tanggal saat ini
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Buat DatePickerDialog dengan tema spinner
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                MainActivity.this,
                android.R.style.Theme_Holo_Light_Dialog, // Gunakan tema spinner
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    // Set tanggal yang dipilih ke EditText
                    String date = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    txtTgl.setText(date);
                },
                year, month, day
        );

        // Tampilkan DatePickerDialog
        datePickerDialog.show();
    }
}
