package com.jojob.mysololife.board

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import com.jojob.mysololife.R
import com.jojob.mysololife.utils.FBAuth

class BoardListViewAdapter(val boardList: MutableList<BoardModel>) : BaseAdapter() {
    override fun getCount(): Int {
        return boardList.size
    }

    override fun getItem(position: Int): Any {
        return boardList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView

        //https://www.google.com/search?q=listview+android+view+%EC%9E%AC%ED%99%9C%EC%9A%A9&rlz=1C5CHFA_enKR943KR943&oq=listview+android+view+%EC%9E%AC%ED%99%9C%EC%9A%A9&aqs=chrome..69i57j0i333l4.2493j0j7&sourceid=chrome&ie=UTF-8
        view = LayoutInflater.from(parent?.context).inflate(R.layout.board_list_item, parent, false)

        val itemLinearLayoutView = view?.findViewById<LinearLayout>(R.id.itemView)
        if (boardList[position].uid.equals(FBAuth.getUid())) {
            itemLinearLayoutView?.setBackgroundColor(Color.parseColor("#ffd400"))
        }

        val title = view?.findViewById<TextView>(R.id.titleArea)
        title!!.text = boardList[position].title

        val content = view?.findViewById<TextView>(R.id.contentArea)
        content!!.text = boardList[position].content

        val time = view?.findViewById<TextView>(R.id.timeArea)
        time!!.text = boardList[position].time

        return view!!
    }
}