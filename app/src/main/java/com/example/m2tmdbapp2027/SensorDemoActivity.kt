package com.example.m2tmdbapp2027

import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.m2tmdbapp2027.databinding.ActivitySensorDemoBinding

class SensorDemoActivity : AppCompatActivity(), OnSensorItemClickListener {
    private val LOGTAG = SensorDemoActivity::class.simpleName

    private lateinit var binding: ActivitySensorDemoBinding
    private lateinit var sensorListAdapter: SensorListAdapter
    private val sensors: ArrayList<Sensor> = ArrayList()
    private lateinit var sensorManager: SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySensorDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Init sensor list RV
        binding.sensorListRv.setHasFixedSize(true)
        binding.sensorListRv.layoutManager = LinearLayoutManager(this)
        sensorListAdapter = SensorListAdapter(
            sensors,
            this
        )
        binding.sensorListRv.adapter = sensorListAdapter

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        sensors.addAll(sensorManager.getSensorList(Sensor.TYPE_ALL))
        sensorListAdapter.notifyItemRangeChanged(0, sensors.size)

    }

    override fun onSensorItemClicked(sensor: Sensor?) {
        Log.d(LOGTAG,"onSensorItemClicked('${sensor?.name}')")
    }
}