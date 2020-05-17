package com.example.doubtnutdemo.ui

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class Dialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return androidx.appcompat.app.AlertDialog.Builder(activity as MainActivity)
            .setTitle("Issue Comments")
            .setMessage("No Comments available")
            // positive button
            .setPositiveButton("OK"
            ) { dialog, which -> }
            .setNegativeButton("Cancel"
            ) { dialog, which -> }
            .create()
    }
}