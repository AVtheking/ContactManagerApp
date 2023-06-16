package com.example.contactmanagerapp.ViewUi

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ExpandableListView.OnChildClickListener
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.contactmanagerapp.R
import com.example.contactmanagerapp.ViewModel.UserViewModel
import com.example.contactmanagerapp.databinding.AddUpdateBinding
import com.example.contactmanagerapp.databinding.CardviewItemBinding
import com.example.contactmanagerapp.room.User

class MyRecyclerviewAdapter(context: Context,private val usersList: List<User>,
       private val clickListener:(User)->Unit,private val userViewModel: UserViewModel):
       RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val binding:CardviewItemBinding=DataBindingUtil.inflate(layoutInflater,R.layout.cardview_item,
            parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
         return usersList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(usersList[position],clickListener,userViewModel)
    }

}
class MyViewHolder(val binding:CardviewItemBinding):RecyclerView.ViewHolder(binding.root)
{
  fun bind(user:User,clickListener: (User) -> Unit,userViewModel: UserViewModel){
         binding.textview.text=user.name
         binding.textview2.text=user.number
         binding.constraint.setOnClickListener{
             val dialog= Dialog(binding.root.context)
             dialog.setContentView(R.layout.add_update)
             val update=dialog.findViewById<TextView>(R.id.txtadd)
             val btn=dialog.findViewById<Button>(R.id.btnAdd)
             update.text="Update Contact"
             btn.text="Update"
             val dialogBinding = AddUpdateBinding.inflate(LayoutInflater.from(binding.root.context), null, false)
             dialogBinding.userViewModel = userViewModel



             dialog.show()

             binding.root.setOnClickListener{
                 clickListener(user)
             }
         }
  }
}