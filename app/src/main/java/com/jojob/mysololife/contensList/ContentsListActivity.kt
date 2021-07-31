package com.jojob.mysololife.contensList

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.jojob.mysololife.R
import com.jojob.mysololife.utils.FBRef

class ContentsListActivity : AppCompatActivity() {

    private lateinit var myRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contents_list)

        val items = ArrayList<ContentModel>()
        val itemKeyList = ArrayList<String>()

        val rvAdapter = ContentsRVAdapter(baseContext, items, itemKeyList)

        val database = Firebase.database

        val category = intent.getStringExtra("category")



        if(category == "category1") {
             myRef = database.getReference("contents")
        }
        else if(category == "category2") {
             myRef = database.getReference("contents2")
        }

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for(dataModel in dataSnapshot.children){
                    Log.d("ContentsListActivity", dataModel.toString())
                    Log.d("ContentsListActivity", dataModel.key.toString())

                    val item = dataModel.getValue(ContentModel::class.java)
                    items.add(item!!)
                    itemKeyList.add(dataModel.key.toString())
                }
                // 동기화
                rvAdapter.notifyDataSetChanged()
                Log.d("ContentsListActivity", items.toString())
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("ContentsListActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }
        myRef.addValueEventListener(postListener)

        val rv : RecyclerView = findViewById(R.id.recyclerView)

        rv.adapter = rvAdapter
        rv.layoutManager = GridLayoutManager(this, 2)

        getBookmarkData()
    }

    private fun getBookmarkData() {
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for(dataModel in dataSnapshot.children){
                    Log.d("getBookmarkData", dataModel.key.toString())
                    Log.d("getBookmarkData", dataModel.toString())
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("ContentsListActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }
        FBRef.bookmarkRef.addValueEventListener(postListener)
    }

}