package com.jojob.mysololife.board

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.jojob.mysololife.R
import com.jojob.mysololife.contensList.BookmarkModel
import com.jojob.mysololife.databinding.ActivityBoardWriteBinding
import com.jojob.mysololife.utils.FBAuth
import com.jojob.mysololife.utils.FBRef

class BoardWriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBoardWriteBinding

    private val TAG = BoardWriteActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_write)

        binding.writebtn.setOnClickListener {
            val title = binding.titleArea.text.toString()
            val content = binding.contentArea.text.toString()
            val uid = FBAuth.getUid()
            val time = FBAuth.getTime()

            Log.d(TAG, title)
            Log.d(TAG, content)

            if (!title.isEmpty()) {
                if (!content.isEmpty()) {
                    FBRef.boardRef
                        .push()
                        .setValue(BoardModel(title, content, uid, time))

                    Toast.makeText(this, "게시글 업로드 완료", Toast.LENGTH_LONG).show()

                    finish()
                } else {
                    Toast.makeText(this, "내용을 입력해주세요", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "제목을 입력해주세요", Toast.LENGTH_SHORT).show()
            }
        }
    }
}