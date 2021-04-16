package com.wakeapp.inventario_offline.utils;

import android.content.Context;

import com.wakeapp.inventario_offline.R;
import com.wakeapp.inventario_offline.databinding.ActivityRateAppBinding;

public class Functions {

    public static void changueStarRate(Context context, int rate, ActivityRateAppBinding binding){
        switch (rate){
            case 1:
                binding.rateStar1.setImageResource(R.drawable.ic_star_check);
                binding.rateStar2.setImageResource(R.drawable.ic_star);
                binding.rateStar3.setImageResource(R.drawable.ic_star);
                binding.rateStar4.setImageResource(R.drawable.ic_star);
                binding.rateStar5.setImageResource(R.drawable.ic_star);
                Local.setData("admin", context, "rate", "1");
                break;
            case 2:
                binding.rateStar1.setImageResource(R.drawable.ic_star_check);
                binding.rateStar2.setImageResource(R.drawable.ic_star_check);
                binding.rateStar3.setImageResource(R.drawable.ic_star);
                binding.rateStar4.setImageResource(R.drawable.ic_star);
                binding.rateStar5.setImageResource(R.drawable.ic_star);
                Local.setData("admin", context, "rate", "2");
                break;
            case 3:
                binding.rateStar1.setImageResource(R.drawable.ic_star_check);
                binding.rateStar2.setImageResource(R.drawable.ic_star_check);
                binding.rateStar3.setImageResource(R.drawable.ic_star_check);
                binding.rateStar4.setImageResource(R.drawable.ic_star);
                binding.rateStar5.setImageResource(R.drawable.ic_star);
                Local.setData("admin", context, "rate", "3");
                break;
            case 4:
                binding.rateStar1.setImageResource(R.drawable.ic_star_check);
                binding.rateStar2.setImageResource(R.drawable.ic_star_check);
                binding.rateStar3.setImageResource(R.drawable.ic_star_check);
                binding.rateStar4.setImageResource(R.drawable.ic_star_check);
                binding.rateStar5.setImageResource(R.drawable.ic_star);
                Local.setData("admin", context, "rate", "4");
                break;
            case 5:
                binding.rateStar1.setImageResource(R.drawable.ic_star_check);
                binding.rateStar2.setImageResource(R.drawable.ic_star_check);
                binding.rateStar3.setImageResource(R.drawable.ic_star_check);
                binding.rateStar4.setImageResource(R.drawable.ic_star_check);
                binding.rateStar5.setImageResource(R.drawable.ic_star_check);
                Local.setData("admin", context, "rate", "5");
                break;
        }
    }
}
