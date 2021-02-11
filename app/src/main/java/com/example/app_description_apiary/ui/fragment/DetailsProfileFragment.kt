package com.example.app_description_apiary.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.example.app_description_apiary.R
import com.example.app_description_apiary.data.DetailsUser
import com.example.app_description_apiary.databinding.FragmentDetailsProfileBinding
import com.example.app_description_apiary.extensions.setupWithViewPager
import com.example.app_description_apiary.ui.adapter.ScreenPagerAdapter
import com.example.app_description_apiary.ui.components.DescriptionProfileUserFragment
import com.squareup.picasso.Picasso


class DetailsProfileFragment(
) : Fragment() {
    private lateinit var binding: FragmentDetailsProfileBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_details_profile, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val details = arguments?.getParcelable<DetailsUser>(DATA)
        binding.tvNamePersonTitileProfile.text = details?.name
        Picasso.get().load(details?.urlImage).into(binding.ivPersonDetailsProfile)

        val listTitles = listOf("Perfil", "Status", "Mensagens")
        val listFragments = listOf(
            DescriptionProfileUserFragment.newInstance("Liderança,\nTrabalho em equipe,\nfala bem em público"),
            DescriptionProfileUserFragment.newInstance("Pendente"),
            DescriptionProfileUserFragment.newInstance("Por favor verifique seu email")
        )

        val adapterViewPager = ScreenPagerAdapter(this, listFragments)
        binding.viewPager.adapter = adapterViewPager
        binding.tbViewPager.setupWithViewPager(binding.viewPager, listTitles)
    }

    companion object {
        const val DATA = "DATA"
    }


}