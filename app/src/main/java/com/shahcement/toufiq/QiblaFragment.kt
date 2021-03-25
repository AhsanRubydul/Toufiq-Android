package com.shahcement.toufiq

import android.content.Context.SENSOR_SERVICE
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import androidx.fragment.app.Fragment
import com.shahcement.toufiq.databinding.FragmentQiblaBinding


class QiblaFragment : Fragment(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private val accelerometerReading = FloatArray(3)
    private val magnetometerReading = FloatArray(3)

    private var smoothed = FloatArray(3)

    private val rotationMatrix = FloatArray(9)
    private val orientationAngles = FloatArray(3)

    private var currentDegree = 0f

    private var _binding: FragmentQiblaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentQiblaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initVariable()
    }

    private fun initVariable() {
        sensorManager = requireContext().getSystemService(SENSOR_SERVICE) as SensorManager
    }

    override fun onResume() {
        super.onResume()

        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)?.also { accelerometer ->
            sensorManager.registerListener(
                this,
                accelerometer,
                SensorManager.SENSOR_DELAY_GAME
            )
        }
        sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)?.also { magneticField ->
            sensorManager.registerListener(
                this,
                magneticField,
                SensorManager.SENSOR_DELAY_GAME
            )
        }
    }

    override fun onPause() {
        super.onPause()

        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            smoothed = filter(event.values, accelerometerReading)
            System.arraycopy(smoothed, 0, accelerometerReading, 0, accelerometerReading.size)
        } else if (event.sensor.type == Sensor.TYPE_MAGNETIC_FIELD) {
            smoothed = filter(event.values, magnetometerReading)
            System.arraycopy(smoothed, 0, magnetometerReading, 0, magnetometerReading.size)
        }

        SensorManager.getRotationMatrix(
            rotationMatrix,
            null,
            accelerometerReading,
            magnetometerReading
        )
        SensorManager.getOrientation(rotationMatrix, orientationAngles)

        val azimuthInRadians = orientationAngles[0].toDouble()
        val azimuthInDegrees = ((Math.toDegrees(azimuthInRadians) + 360) % 360).toFloat()

        val ra = RotateAnimation(
            currentDegree,
            -azimuthInDegrees,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )

        ra.duration = 250
        ra.fillAfter = true

        binding.ivCompass.startAnimation(ra)
        currentDegree = -azimuthInDegrees
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    private fun filter(input: FloatArray?, prev: FloatArray?): FloatArray {
        val alpha = 0.2f

        if (input == null || prev == null) {
            throw NullPointerException("input and prev float arrays must be non-NULL")
        }
        if (input.size != prev.size) {
            throw IllegalArgumentException("input and prev must be the same length")
        }
        for (i in input.indices) {
            prev[i] = prev[i] + alpha * (input[i] - prev[i])
        }

        return prev
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
