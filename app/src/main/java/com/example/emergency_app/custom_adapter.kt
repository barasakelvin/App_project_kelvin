package com.example.emergency_app

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class Customadapter(var context: Context, var data:ArrayList<User>):BaseAdapter(){
    private class ViewHolder(row:View?){
      var mtxtname: TextView
      var mtxtfnum: TextView
      var mtxtemail: TextView
      var mtxtloc: TextView
      var mtxtdes: TextView

      init {
          this.mtxtname = row?.findViewById(R.id.Txt_name) as TextView
          this.mtxtfnum = row?.findViewById(R.id.Txt_fnum) as TextView
          this.mtxtemail = row?.findViewById(R.id.Txt_email) as TextView
          this.mtxtloc = row?.findViewById(R.id.Txt_loc) as TextView
          this.mtxtdes = row?.findViewById(R.id.Txt_des) as TextView
      }
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?):View{
        val view: View?
        val viewHolder:ViewHolder
        if (convertView == null){
            val layout = LayoutInflater.from(context)
            view = layout.inflate(R.layout.activity_view,parent,false)
            viewHolder =ViewHolder(view)
            view?.tag = viewHolder
        }else{
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        val item:User = getItem(position) as User
        viewHolder.mtxtname.text =item.name
        viewHolder.mtxtemail.text =item.email
        viewHolder.mtxtfnum.text =item.number
        viewHolder.mtxtloc.text =item.locate
        viewHolder.mtxtdes.text =item.des


        return view as View
    }

    override fun getItem(position: Int): Any {
        return data.get(position)
    }
    override  fun getItemId(position: Int): Long {
        return position.toLong()
    }
    override fun getCount(): Int {
        return data.count()
    }
}