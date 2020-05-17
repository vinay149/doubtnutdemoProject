import android.content.Context
import com.example.doubtnutdemo.DoubtNutDemoApplication

class TestDoubtNutDemo: DoubtNutDemoApplication() {

    var instance:Context = this

    companion object{
        fun getContext():Context{
            return instance
        }
    }
}