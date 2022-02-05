package com.example.basicactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.PopupMenu
import android.widget.TextView

class MenuActivity : AppCompatActivity() {
    private lateinit var menuB: Button
    private lateinit var helloTV: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        menuB = findViewById(com.example.basicactivity.R.id.menuB)
        helloTV = findViewById(R.id.helloTV)
        menuB.setOnClickListener {
            showPopup(menuB)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val layout = findViewById<View>(R.id.root_layout)
        when(item.itemId) {
            R.id.night -> {
                layout.setBackgroundResource(R.color.black)
            }
            R.id.warm -> {
                layout.setBackgroundResource(R.color.warm)
            }
            R.id.light -> {
                layout.setBackgroundResource(R.color.white)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private  fun showPopup(view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.inflate(R.menu.popupmenu)
        popupMenu.show()

        popupMenu.setOnMenuItemClickListener{ it -> R.id.menugroup1
            when (it.itemId) {
                R.id.menu1 -> {
                    helloTV.text = "You choose Popup 1"
                }
                R.id.menu2 -> {
                    helloTV.text = "You choose Popup 2"
                }
                R.id.menu3 -> {
                    popupMenu.setOnMenuItemClickListener {it -> R.id.submenugroup
                        when (it.itemId) {
                            R.id.submenu -> {
                                helloTV.text = "You choose Submenu 1"
                            }
                            R.id.submenu2 -> {
                                helloTV.text = "You choose Submenu 2"
                            }
                            R.id.submenu3 -> {
                                helloTV.text = "You choose Submenu 3"
                            }
                        }
                        true
                    }
                }
                R.id.menu4 -> {
                    helloTV.text = "You choose Popup 4"
                }
                R.id.menu5 -> {
                    helloTV.text = "You choose Popup 5"
                }
                R.id.menu6 -> {
                    helloTV.text = "You choose Popup 6"
                }
                }
                true
        }

    }
}