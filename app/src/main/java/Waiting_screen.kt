import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.app_server.Mysocket.Companion.mysocket
import com.example.app_server.R
import com.example.app_server.Room_info
import kotlinx.android.synthetic.main.waiting.*
import org.json.JSONObject

class Waiting_screen : AppCompatActivity() {

    var current : Int = 1
    var max : Int = 6
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.waiting)

        mysocket.emit("enter-lobby")    //플레이어가 들어왔을 때,
        mysocket.on("enter-lobby") {arg ->
            val jsondata  = JSONObject(arg[0].toString())

            get_set_data(jsondata)
        }

        mysocket.on("new-player-enter-room") {arg ->
                current++
                set_layouts(current,max)
        }

        /*game_start.setOnClickListener({
            mysocket.emit("게임 시작 버튼 클릭") {

            }
        })*/

    }
    fun set_layouts(current : Int, max : Int) {
        waiting_message_box.text = "플레이어를 기다리는 중 ... " + getString(current) + "/" + getString(max)
    }

    fun get_set_data( inputobject : JSONObject)  {
        val keys = inputobject.keys()
        while (keys.hasNext()) {
            val key = keys.next();
            val value = inputobject.get(key)
            val jsonobject2 = JSONObject(value.toString())

            val playerNum = jsonobject2.getInt("playerNum")
            val listdata = arrayListOf<String>()
            val jsonarray2 = jsonobject2.getJSONArray("players")
            var j=0
            if(jsonarray2 != null)
            {
                set_layouts(jsonarray2.length(),playerNum)
                current = jsonarray2.length()
                max = playerNum
            }
        /*var room_info : Room_info = Room_info(name.toString(), players, playerNum)*/
        }
    }
}