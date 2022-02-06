package com.example.basicactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MenuActivity : AppCompatActivity() {
    private lateinit var menuB: Button
    private lateinit var helloTV: TextView
    private lateinit var helloIV: ImageView
    private var timeOfLesson: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        menuB = findViewById(R.id.menuB)
        helloIV = findViewById(R.id.helloIV)
        helloTV = findViewById(R.id.helloTV)
        menuB.setOnClickListener {
            showPopup(menuB)
        }
        helloIV.setOnClickListener {
            makeNotification(101,"channelID")
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
                R.id.ten -> {
                    timeOfLesson = "Ваше занятие с 10:00 по 12:00"
                    helloTV.text = timeOfLesson

                }
                R.id.twelve -> {
                    timeOfLesson = "Ваше занятие с 12:00 по 14:00"
                    helloTV.text = timeOfLesson
                }
                R.id.two -> {
                    popupMenu.setOnMenuItemClickListener {it -> R.id.submenugroup
                        when (it.itemId) {
                            R.id.twoSub -> {
                                timeOfLesson = "Ваше занятие с 14:00 по 15:00"
                                helloTV.text = timeOfLesson
                            }
                            R.id.treeSub -> {
                                timeOfLesson = "Ваше занятие с 15:00 по 16:00"
                                helloTV.text = timeOfLesson
                            }
                        }
                        true
                    }
                }
                R.id.four -> {
                    timeOfLesson = "Ваше занятие с 16:00 по 18:00"
                    helloTV.text = timeOfLesson
                }
                R.id.six -> {
                    timeOfLesson = "Ваше занятие с 18:00 по 20:00"
                    helloTV.text = timeOfLesson
                }
            }
            true
        }
    }
    private fun makeNotification(not_id: Int, channelID: String) {
        val builder = NotificationCompat.Builder(this, channelID)
            .setSmallIcon(R.drawable.ic_baseline_back_hand_24)
            .setContentTitle("Запись на занятие")
            .setContentText(timeOfLesson)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            notify(not_id, builder.build())
        }
    }
}