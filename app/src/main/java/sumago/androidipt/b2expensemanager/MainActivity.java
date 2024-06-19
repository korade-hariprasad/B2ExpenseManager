package sumago.androidipt.b2expensemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

import sumago.androidipt.b2expensemanager.database.DbHelper;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    DbHelper dbHelper;
    FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFragment(new HomeFragment());
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        dbHelper=new DbHelper(this);
        fabAdd=findViewById(R.id.fabAdd);
        //dbHelper.onCreate(dbHelper.getWritableDatabase());
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.action_home) {
                    loadFragment(new HomeFragment());

                }
                if (item.getItemId() == R.id.action_analytics) {
                    loadFragment(new AnalyticsFragment());
                }
                if (item.getItemId() == R.id.action_more) {
                    loadFragment(new MoreFragment());

                }
                if (item.getItemId() == R.id.action_options) {
                    loadFragment(new OptionsFragment());
                }
                return true;
            }
        });
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this, AddExpenseActivity.class);
                startActivity(intent);
            }
        });
    }
    public void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.commit();
    }
}