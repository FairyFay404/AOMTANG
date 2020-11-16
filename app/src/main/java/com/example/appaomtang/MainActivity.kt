package com.example.appaomtang

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView=findViewById<BottomNavigationView>(R.id.nav)
        val navController = findNavController(R.id.fragment)

        bottomNavigationView.setupWithNavController(navController)



    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main_setting,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        var selectedOption =""
        when(item?.itemId){
            R.id.walletselect -> {
                selectedOption = "walletselect"
                //val walletselection = Intent(this, wallet::class.java)
                //startActivity(walletselection)
                Toast.makeText(this, ""+selectedOption, Toast.LENGTH_SHORT).show()
            }

            R.id.timeselect -> {
                selectedOption = "timeselect"
                //val timeselection = Intent(this, reportActivity::class.java)
                //startActivity(timeselection)
                Toast.makeText(this, ""+selectedOption, Toast.LENGTH_SHORT).show()
            }

            R.id.typeselect -> {
                selectedOption = "typeselect"
                //val typeselection = Intent(this, note::class.java)
                //startActivity(typeselection)
                Toast.makeText(this, ""+selectedOption, Toast.LENGTH_SHORT).show()
            }
        }


        return super.onOptionsItemSelected(item)
    }

}