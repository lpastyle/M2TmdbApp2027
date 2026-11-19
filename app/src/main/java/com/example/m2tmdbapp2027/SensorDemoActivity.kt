package com.example.m2tmdbapp2027

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.m2tmdbapp2027.databinding.ActivitySensorDemoBinding

private const val SAMPLING_INTERVAL_US = 1000000 // one second

class SensorDemoActivity : AppCompatActivity(),
    OnSensorItemClickListener,
    SensorEventListener
{
    private val LOGTAG = SensorDemoActivity::class.simpleName

    private lateinit var binding: ActivitySensorDemoBinding
    private lateinit var sensorListAdapter: SensorListAdapter
    private val sensors: ArrayList<Sensor> = ArrayList()
    private lateinit var sensorManager: SensorManager
    private var currentSensor: Sensor? = null

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

        if (sensors.isNotEmpty()) onSensorItemClicked(sensors[0])

    }

    private fun registerSensorListener() {
        currentSensor?.let {

            val registered = sensorManager.registerListener(
                this,
                it,
                SAMPLING_INTERVAL_US
            )
            if (!registered) {
                Log.w(LOGTAG, "unable to register a listener for '${it.name}'")

            }
        }
    }

    private fun unRegisterSensorListener() {
        sensorManager.unregisterListener(this)
    }

    override fun onSensorItemClicked(sensor: Sensor?) {
        Log.d(LOGTAG,"onSensorItemClicked('${sensor?.name}')")
        unRegisterSensorListener()
        currentSensor = sensor
        registerSensorListener()

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        Log.i(LOGTAG, "onAccuracyChanged($accuracy)")
    }

    override fun onSensorChanged(event: SensorEvent?) {
        Log.i(LOGTAG, "onSensorChanged(${event?.values?.size} values)")
    }

    // activity life cycle management:
    // unregister sensors listener to save power
    override fun onResume() {
        super.onResume()
        registerSensorListener()
    }

    override fun onPause() {
        super.onPause()
        unRegisterSensorListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        unRegisterSensorListener()
    }
}