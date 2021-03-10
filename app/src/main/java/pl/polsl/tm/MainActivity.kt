package pl.polsl.tm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.abs
import kotlin.math.round

class MainActivity : AppCompatActivity(), View.OnClickListener {

    enum class Operation {
        Add, Subtract
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonAdd.setOnClickListener(this)
        buttonSub.setOnClickListener(this)


       ArrayAdapter.createFromResource(
            this,
            R.array.spinnerItems,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
            spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
               override fun onItemSelected(parent: AdapterView<*>,
                                           view: View, position: Int, id: Long) {
                   when(position){
                       0 -> calculate(Operation.Add)
                       1 -> calculate(Operation.Subtract)
                   }
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
        }
    }

    override fun onClick(v: View? ){
        when(v?.id){
            buttonAdd.id -> calculate(Operation.Add)
            buttonSub.id -> calculate(Operation.Subtract)
        }
    }

    private fun calculate(o: Operation){
        var aRe : Double = 0.0
        var bRe : Double = 0.0
        var aIm : Double = 0.0
        var bIm : Double = 0.0
        if(aNumberRe.text.toString().isNotEmpty()){
            aRe = aNumberRe.text.toString().toDouble()
        }
        if(aNumberIm.text.toString().isNotEmpty()){
            aIm = aNumberIm.text.toString().toDouble()
        }
        if(bNumberRe.text.toString().isNotEmpty()){
            bRe = bNumberRe.text.toString().toDouble()
        }
        if(bNumberIm.text.toString().isNotEmpty()){
            bIm = bNumberIm.text.toString().toDouble()
        }


        var reResult: Double = 0.0
        var imResult: Double = 0.0

        when(o){
            Operation.Add -> {reResult = aRe + bRe
                              imResult = aIm + bIm
                                }
            Operation.Subtract -> {reResult = aRe - bRe
                                   imResult = aIm - bIm
                                    }
        }

        imResult = round(imResult*100.0)/100.0
        reResult = round(reResult*100.0)/100.0

        if(imResult>=0)
            Toast.makeText(applicationContext,"Wynik: $reResult + $imResult", Toast.LENGTH_LONG).show()
        else{
            imResult = abs(imResult)
            Toast.makeText(applicationContext,"Wynik: $reResult - $imResult", Toast.LENGTH_LONG).show()
        }
    }
}