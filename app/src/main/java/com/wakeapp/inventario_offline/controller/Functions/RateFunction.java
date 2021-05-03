package com.wakeapp.inventario_offline.controller.Functions;

import android.content.Context;

import com.wakeapp.inventario_offline.R;
import com.wakeapp.inventario_offline.databinding.ActivityRateAppBinding;
import com.wakeapp.inventario_offline.utils.Local;

public class RateFunction {

    public static void setRateStar(Context context, ActivityRateAppBinding binding){
        binding.star.rateStar1.setOnClickListener(v-> RateFunction.changueStarRate(context, 1, binding));
        binding.star.rateStar2.setOnClickListener(v-> RateFunction.changueStarRate(context, 2, binding));
        binding.star.rateStar3.setOnClickListener(v-> RateFunction.changueStarRate(context, 3, binding));
        binding.star.rateStar4.setOnClickListener(v-> RateFunction.changueStarRate(context, 4, binding));
        binding.star.rateStar5.setOnClickListener(v-> RateFunction.changueStarRate(context, 5, binding));
    }

    public static void changueStarRate(Context context, int rate, ActivityRateAppBinding binding){
        switch (rate){
            case 1:
                binding.star.rateStar1.setImageResource(R.drawable.ic_star_check);
                binding.star.rateStar2.setImageResource(R.drawable.ic_star);
                binding.star.rateStar3.setImageResource(R.drawable.ic_star);
                binding.star.rateStar4.setImageResource(R.drawable.ic_star);
                binding.star.rateStar5.setImageResource(R.drawable.ic_star);
                Local.setData("admin", context, "rate", "1");
                break;
            case 2:
                binding.star.rateStar1.setImageResource(R.drawable.ic_star_check);
                binding.star.rateStar2.setImageResource(R.drawable.ic_star_check);
                binding.star.rateStar3.setImageResource(R.drawable.ic_star);
                binding.star.rateStar4.setImageResource(R.drawable.ic_star);
                binding.star.rateStar5.setImageResource(R.drawable.ic_star);
                Local.setData("admin", context, "rate", "2");
                break;
            case 3:
                binding.star.rateStar1.setImageResource(R.drawable.ic_star_check);
                binding.star.rateStar2.setImageResource(R.drawable.ic_star_check);
                binding.star.rateStar3.setImageResource(R.drawable.ic_star_check);
                binding.star.rateStar4.setImageResource(R.drawable.ic_star);
                binding.star.rateStar5.setImageResource(R.drawable.ic_star);
                Local.setData("admin", context, "rate", "3");
                break;
            case 4:
                binding.star.rateStar1.setImageResource(R.drawable.ic_star_check);
                binding.star.rateStar2.setImageResource(R.drawable.ic_star_check);
                binding.star.rateStar3.setImageResource(R.drawable.ic_star_check);
                binding.star.rateStar4.setImageResource(R.drawable.ic_star_check);
                binding.star.rateStar5.setImageResource(R.drawable.ic_star);
                Local.setData("admin", context, "rate", "4");
                break;
            case 5:
                binding.star.rateStar1.setImageResource(R.drawable.ic_star_check);
                binding.star.rateStar2.setImageResource(R.drawable.ic_star_check);
                binding.star.rateStar3.setImageResource(R.drawable.ic_star_check);
                binding.star.rateStar4.setImageResource(R.drawable.ic_star_check);
                binding.star.rateStar5.setImageResource(R.drawable.ic_star_check);
                Local.setData("admin", context, "rate", "5");
                break;
        }
    }
}
