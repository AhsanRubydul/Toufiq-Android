package com.shahcement.tawfiq.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.barteksc.pdfviewer.util.FitPolicy
import com.shahcement.tawfiq.databinding.ActivityPdfViewerBinding

class PdfViewerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPdfViewerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPdfViewerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = ""

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        intent?.extras?.let {
            val file = it.getString("file", null)

            file?.let { assetName ->
                binding.pdfView.fromAsset("$assetName.pdf")
                    .defaultPage(0)
                    .pageFitPolicy(FitPolicy.WIDTH)
                    .load()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}