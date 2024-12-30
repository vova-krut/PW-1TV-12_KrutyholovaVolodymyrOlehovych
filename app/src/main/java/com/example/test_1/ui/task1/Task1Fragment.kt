package com.example.test_1.ui.task1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.test_1.databinding.FragmentTask1Binding
import java.util.Optional

class Task1Fragment : Fragment() {

    private var _binding: FragmentTask1Binding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ViewModelProvider(this)[Task1ViewModel::class.java]

        _binding = FragmentTask1Binding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.buttonCalculateTask1.setOnClickListener { task1() }

        return root
    }

    private fun round(num: Double) = "%.4f".format(num)

    private fun task1()
    {
        val Hp = binding.Hp.text.toString().toDoubleOrNull()
        val Cp = binding.Cp.text.toString().toDoubleOrNull()
        val Sp = binding.Sp.text.toString().toDoubleOrNull()
        val Np = binding.Np.text.toString().toDoubleOrNull()
        val Op = binding.Op.text.toString().toDoubleOrNull()
        val Wp = binding.Wp.text.toString().toDoubleOrNull()
        val Ap = binding.Ap.text.toString().toDoubleOrNull()

        if (Hp == null || Cp == null || Sp == null || Np == null || Op == null || Wp == null || Ap == null) {
            binding.outputTask1.text = "Помилка в введених даних"

            return
        }

        val Krs = 100 / (100 - Wp)
        val Krg = 100 / (100 - Wp - Ap)

        val Hc = Hp * Krs
        val Cc = Cp * Krs
        val Sc = Sp * Krs
        val Nc = Np * Krs
        val Oc = Op * Krs
        val Ac = Ap * Krs

        val Hg = Hp * Krg
        val Cg = Cp * Krg
        val Sg = Sp * Krg
        val Ng = Np * Krg
        val Og = Op * Krg

        val Qrn = ((339 * Cp + 1030 * Hp - 108.8 * (Op - Sp) - 25 * Wp) / 1000)
        val Qsn = ((Qrn + 0.025 * Wp) * 100 / (100 - Wp))
        val Qhn = ((Qrn + 0.025 * Wp) * 100 / (100 - Wp - Ap))

        val text = "Компонентний склад - H^P: $Hp%, C^P: $Cp%, S^P: $Sp%, N^P: $Np%, O^P: $Op%, W^P: $Wp%, A^P: $Ap% \n" +
                "Коефіцієнт переходу від робочої до сухої маси -  ${round(Krs)} \n" +
                "Коефіцієнт переходу від робочої до горючої маси -  ${round(Krg)} \n" +
                "Склад сухої маси палива -  H^C: ${round(Hc)}%, C^C: ${round(Cc)}%, S^C: ${round(Sc)}%, N^C: ${round(Nc)}%, O^C: ${round(Oc)}%, A^C: ${round(Ac)}% \n" +
                "Склад горючої маси палива - H^G: ${round(Hg)}%, C^G: ${round(Cg)}%, S^G: ${round(Sg)}%, N^G: ${round(Ng)}%, O^G: ${round(Og)}% \n" +
                "Н. теплота згоряння для робочої маси - ${round(Qrn)} МДж/кг \n" +
                "Н. теплота згоряння для сухої маси - ${round(Qsn)} МДж/кг \n" +
                "Н. теплота згоряння для горючої маси - ${round(Qhn)} МДж/кг"


        binding.outputTask1.text = text;
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}