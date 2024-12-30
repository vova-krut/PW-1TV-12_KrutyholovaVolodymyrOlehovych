package com.example.test_1.ui.task2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.test_1.databinding.FragmentTask2Binding

class Task2Fragment : Fragment() {

    private var _binding: FragmentTask2Binding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ViewModelProvider(this)[Task2ViewModel::class.java]

        _binding = FragmentTask2Binding.inflate(inflater, container, false)
        val root: View = binding.root


        binding.buttonCalculateTask2.setOnClickListener { task2() }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun round(num: Double) = "%.4f".format(num)

    private fun task2()
    {

        val Hg = binding.Hg.text.toString().toDoubleOrNull()
        val Cg = binding.Cg.text.toString().toDoubleOrNull()
        val Og = binding.Og.text.toString().toDoubleOrNull()
        val Sg = binding.Sg.text.toString().toDoubleOrNull()
        val Qdaf = binding.Qdaf.text.toString().toDoubleOrNull()
        val Wg = binding.Wg.text.toString().toDoubleOrNull()
        val Ag = binding.Ag.text.toString().toDoubleOrNull()
        val Vg = binding.Vg.text.toString().toDoubleOrNull()

        if (Hg == null || Cg == null || Og == null || Sg == null || Qdaf == null || Wg == null || Ag == null || Vg == null) {
            binding.outputTask2.text = "Помилка в введених даних"

            return
        }

        val Cp = Cg * (100 - Wg - Ag) / 100;
        val Hp = Hg * (100 - Wg - Ag) / 100;
        val Op = Og * (100 - Wg - Ag) / 100;
        val Sp = Sg * (100 - Wg * 0.1 - Ag * 0.1) / 100;
        val Ap = Ag * (100 - Wg) / 100;
        val Vp = Vg * (100 - Wg) / 100;

        val Qp = Qdaf * ((100 - Wg - Ap) / 100) - 0.025 * Wg


        val text = "Склад горючої маси мазуту: H^Г=$Hg%, C^Г=$Cg%, S^Г=$Sg%, O^Г=$Og%, V^Г=$Vg мг/кг, W^Г=$Wg%, A^Г=$Ag%;" +
                " та нижчою теплотою згоряння Qi^daf=$Qdaf МД/кг:\n" +
                "Склад робочої маси мазуту: H^Р=${round(Hp)}%, C^Р=${round(Cp)}%, S^Р=${round(Sp)}%, O^Р=${round(Op)}%, V^Р=${round(Vp)} мг/кг, A^Р=${round(Ap)}%;\n" +
                "Нижча теплота згоряння мазуту на робочу масу для робочої маси: ${round(Qp)} Мдж/кг."
        binding.outputTask2.text = text;

    }
}