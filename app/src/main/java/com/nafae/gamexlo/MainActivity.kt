package com.nafae.gamexlo

import android.content.res.Resources
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.content.Context
import android.widget.Toast
import androidx.core.content.ContextCompat.getColor
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random as KotlinRandomRandom

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val autoPlay:Button = findViewById(R.id.auto_play)
        val towplayer:Button = findViewById(R.id.two_player)
        autoPlay.setOnClickListener {
            auto_play=true
            two_play=false
            autoPlay.setVisibility(View.INVISIBLE)
            towplayer.setVisibility(View.INVISIBLE)
        }
        towplayer.setOnClickListener {
            two_play = true
            auto_play=false
            autoPlay.setVisibility(View.INVISIBLE)
            towplayer.setVisibility(View.INVISIBLE)
        }

    }
    fun Selectbtn(view:View){



         var choisebtn = view as Button
        if (endgame){choisebtn.isEnabled=false
        }else{var Cellid =0
        when(choisebtn.id){
            R.id.btn1 -> Cellid=1
            R.id.btn2 -> Cellid=2
            R.id.btn3 -> Cellid=3
            R.id.btn4 -> Cellid=4
            R.id.btn5 -> Cellid=5
            R.id.btn6 -> Cellid=6
            R.id.btn7 -> Cellid=7
            R.id.btn8 -> Cellid=8
            R.id.btn9 -> Cellid=9
        }
        Playgame(Cellid, choisebtn)
        Log.d("the id of btn : ",Cellid.toString())}
    }
    var auto_play = false
    var two_play = false
    var endgame = false
    var player1= ArrayList<Int>()
    var player2 = ArrayList<Int>()
    var activePlaye = 1
    var co =0
    fun Playgame(Cellid:Int , choisebtn:Button){
        if (auto_play){
            if (activePlaye==1){
                choisebtn.text ="X"
                choisebtn.setBackgroundResource(R.drawable.border_raduis2)
                player1.add(Cellid)
                activePlaye = 2
                co++
                if (co>4) {
                    if (checkwener(player1)) {
                        Toast.makeText(this, "player 1 win the game ,congratulations", Toast.LENGTH_SHORT)
                            .show()
                        endgame = true
                        replay()
                    } else if (checkwener(player2)) {
                        Toast.makeText(this, "player 2 win the game ,congratulations", Toast.LENGTH_SHORT)
                            .show()
                        endgame = true
                        replay()
                    } else if (player2.size + player1.size == 9) {
                        Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show()
                        endgame= true
                        replay()
                    }
                    else{
                        autoplay()
                    }
                }
                else{
                    autoplay()
                }


            }
            else{
                choisebtn.text ="O"
                choisebtn.setBackgroundResource(R.drawable.border_raduis1)
                player2.add(Cellid)
                activePlaye = 1
                co++
                if (co>4){
                    if(checkwener(player1)) {Toast.makeText(this,"player 1 win the game ,congratulations",Toast.LENGTH_SHORT).show()
                    endgame=true
                    replay()
                }
                else if (checkwener(player2)) {Toast.makeText(this,"player 2 win the game ,congratulations",Toast.LENGTH_SHORT).show()
                    endgame=true
                    replay()
                }
                else if (player2.size+player1.size == 9) {Toast.makeText(this,"Draw",Toast.LENGTH_SHORT).show()
                    replay()}
            }
            }
            choisebtn.isEnabled=false;
        }else if (two_play){
            if (activePlaye==1){
                choisebtn.text ="X"
                choisebtn.setBackgroundResource(R.drawable.border_raduis2)
                player1.add(Cellid)
                activePlaye = 2
                co++
            }
            else{

                choisebtn.text ="O"
                choisebtn.setBackgroundResource(R.drawable.border_raduis1)
                player2.add(Cellid)
                activePlaye = 1
                co++
            }
            choisebtn.isEnabled=false;
            if (co>4){
                if(checkwener(player1)) {Toast.makeText(this,"player 1 win the game ,congratulations",Toast.LENGTH_SHORT).show()
                    endgame=true
                    replay()
                }
                else if (checkwener(player2)) {Toast.makeText(this,"player 2 win the game ,congratulations",Toast.LENGTH_SHORT).show()
                    endgame=true
                    replay()
                }
                else if (player2.size+player1.size == 9) {Toast.makeText(this,"Draw",Toast.LENGTH_SHORT).show()
                    replay()
                    endgame =true
                }
            }
        }
        else{
            Toast.makeText(this,"please, choise what do you wont play?",Toast.LENGTH_SHORT).show()
        }

    }

    fun replay() {
        val btn_replay = findViewById(R.id.btn_replay) as Button
        btn_replay.setVisibility(View.VISIBLE)
        val autoPlay:Button = findViewById(R.id.auto_play)
        autoPlay.setVisibility(View.VISIBLE)
        val towplayer:Button = findViewById(R.id.two_player)
        towplayer.setVisibility(View.VISIBLE)
        btn_replay.setOnClickListener {
            val btn_l = ArrayList<Button>()
            btn_l.add(findViewById(R.id.btn1) as Button)
            btn_l.add(findViewById(R.id.btn2) as Button)
            btn_l.add(findViewById(R.id.btn3) as Button)
            btn_l.add(findViewById(R.id.btn4) as Button)
            btn_l.add(findViewById(R.id.btn5) as Button)
            btn_l.add(findViewById(R.id.btn6) as Button)
            btn_l.add(findViewById(R.id.btn7) as Button)
            btn_l.add(findViewById(R.id.btn8) as Button)
            btn_l.add(findViewById(R.id.btn9) as Button)
            for (i in 0..8){
                btn_l[i].text =""
                btn_l[i].setBackgroundResource(R.drawable.border_raduis)
                btn_l[i].isEnabled=true
                activePlaye = 1
                player1.clear()
                player2.clear()
            }
            endgame =false;co=0
            btn_replay.setVisibility(View.INVISIBLE)
            autoPlay.setVisibility(View.INVISIBLE)
            towplayer.setVisibility(View.INVISIBLE)
        }
    }


    fun checkwener(player:ArrayList<Int>):Boolean{

        if(player.contains(1) && player.contains(2) && player.contains(3)){
            return true}
        else if (player.contains(4) && player.contains(5) && player.contains(6)){
            return true
        }
        else if (player.contains(7) && player.contains(8) && player.contains(9)){
            return true
        }
        else if (player.contains(1) && player.contains(4) && player.contains(7)){
            return true
        }
        else if (player.contains(5) && player.contains(2) && player.contains(8)){
            return true
        }
        else if (player.contains(6) && player.contains(9) && player.contains(3)){
            return true
        }
         else if (player.contains(1) && player.contains(5) && player.contains(9)){
            return true
        }
        else if(player.contains(3) && player.contains(5) && player.contains(7)){
            return true
        }
        else{
            return false}
    }
    fun autoplay(){
        val empety = ArrayList<Int>()
        for (Cellid in 1..9){
            if (!(player1.contains(Cellid)||player2.contains(Cellid))){
                empety.add(Cellid)
            }
        }
        val r= Random()
        val randIndex = r.nextInt(empety.size-0)+0
        var buselect:Button?
        when(empety[randIndex]){
            1->buselect=findViewById(R.id.btn1)
            2->buselect=findViewById(R.id.btn2)
            3->buselect=findViewById(R.id.btn3)
            4->buselect=findViewById(R.id.btn4)
            5->buselect=findViewById(R.id.btn5)
            6->buselect=findViewById(R.id.btn6)
            7->buselect=findViewById(R.id.btn7)
            8->buselect=findViewById(R.id.btn8)
            9->buselect=findViewById(R.id.btn9)
            else->buselect=findViewById(R.id.btn1)
        }
        Playgame(empety[randIndex],buselect)
    }
}