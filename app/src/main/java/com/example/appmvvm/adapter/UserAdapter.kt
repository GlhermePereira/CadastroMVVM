package com.example.appmvvm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appmvvm.databinding.ItemUserBinding
import com.example.appmvvm.model.StatusUsuario
import com.example.appmvvm.model.User

class UserAdapter(private val users: List<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.binding.tvLogin.text = user.login
        holder.binding.tvStatus.text = when (user.status) {
            StatusUsuario.ATIVO -> "Ativo"
            StatusUsuario.INATIVO -> "Inativo"
            StatusUsuario.BLOQUEADO -> "Bloqueado"
        }    }

    override fun getItemCount(): Int = users.size

    class UserViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)
}
