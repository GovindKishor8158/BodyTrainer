package com.govind.admin.bodytrainer.DietChart;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.govind.admin.bodytrainer.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 19-Mar-19.
 */

public class Thufrag extends Fragment {
    @BindView(R.id.foodlay)RelativeLayout foodlay;
    @BindView(R.id.wholegg)TextView breakfast1;
    @BindView(R.id.eggwhite)TextView breakfast2;
    @BindView(R.id.cookedoats)TextView breakfast3;
    @BindView(R.id.honey)TextView breakfast4;
    @BindView(R.id.banana)TextView breakfast5;
    @BindView(R.id.proteinpowder)TextView breakfast6;
    @BindView(R.id.milk)TextView breakfast7;

    @BindView(R.id.wholegg1)TextView snack1;
    @BindView(R.id.eggwhite1)TextView snack2;
    @BindView(R.id.cookedoats1)TextView snack3;
    @BindView(R.id.honey1)TextView snack4;
    @BindView(R.id.banana1)TextView snack5;
    @BindView(R.id.proteinpowder1)TextView snack6;
    @BindView(R.id.milk1)TextView snack7;


    @BindView(R.id.wholegg2)TextView lunch1;
    @BindView(R.id.eggwhite2)TextView lunch2;
    @BindView(R.id.cookedoats2)TextView lunch3;
    @BindView(R.id.honey2)TextView lunch4;
    @BindView(R.id.banana2)TextView lunch5;
    @BindView(R.id.proteinpowder2)TextView lunch6;
    @BindView(R.id.milk2)TextView lunch7;



    @BindView(R.id.wholegg3)TextView snacksec1;
    @BindView(R.id.eggwhite3)TextView snacksec2;
    @BindView(R.id.cookedoats3)TextView snacksec3;
    @BindView(R.id.honey3)TextView snacksec4;
    @BindView(R.id.banana3)TextView snacksec5;
    @BindView(R.id.proteinpowder3)TextView snacksec6;
    @BindView(R.id.milk3)TextView snacksec7;


    @BindView(R.id.wholegg4)TextView dinner1;
    @BindView(R.id.eggwhite4)TextView dinner2;
    @BindView(R.id.cookedoats4)TextView dinner3;
    @BindView(R.id.honey4)TextView dinner4;
    @BindView(R.id.banana4)TextView dinner5;
    @BindView(R.id.proteinpowder4)TextView dinner6;
    @BindView(R.id.milk4)TextView dinner7;


    @BindView(R.id.wholegg5)TextView supper1;
    @BindView(R.id.eggwhite5)TextView supper2;
    @BindView(R.id.cookedoats5)TextView supper3;
    @BindView(R.id.honey5)TextView supper4;
    @BindView(R.id.banana5)TextView supper5;
    @BindView(R.id.proteinpowder5)TextView supper6;
    @BindView(R.id.milk5)TextView supper7;
    @BindView(R.id.daytext)TextView daytext;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_monday, container, false);
        ButterKnife.bind(this, view);
        daytext.setText("Thursday");
        foodlay.setBackgroundResource(R.drawable.food4);
        breakfast1.setText("Whole eggs: "+"3");
        breakfast2.setText("Egg white(scrambled): "+"3");
        breakfast3.setText("Multigrain Toast: "+"4slices");
        breakfast4.setText("Extra lean turkey bacon: "+"4slices");
        breakfast5.setText("Avocado: "+"1/4");
        breakfast6.setVisibility(view.GONE);
        breakfast7.setVisibility(view.GONE);

        snack1.setText("Apple: "+"2");
        snack2.setText("Natural Peanut Butter: "+"2tbsp");
        snack3.setVisibility(view.GONE);
        snack4.setVisibility(view.GONE);
        snack5.setVisibility(view.GONE);
        snack6.setVisibility(view.GONE);
        snack7.setVisibility(view.GONE);


        lunch1.setText("Baked Potato(skin on) ");
        lunch2.setText("Tuna: "+"185g");
        lunch3.setText("Sweet corn "+"1cup");
        lunch4.setText("Low fat mayo: "+"1tbsp");
        lunch5.setText("Chili: "+"To taste");
        lunch6.setVisibility(view.GONE);
        lunch7.setVisibility(view.GONE);


        snacksec1.setText("Protein Powder: "+"1 serving");
        snacksec2.setText("Milk: "+"300ml");
        snacksec3.setVisibility(view.GONE);
        snacksec4.setVisibility(view.GONE);
        snacksec5.setVisibility(view.GONE);
        snacksec6.setVisibility(view.GONE);
        snacksec7.setVisibility(view.GONE);


        dinner1.setText("Wholemeal burger buns: "+"2");
        dinner2.setText("Lean steak: "+"200g");
        dinner3.setText("Fried egg: "+"1");
        dinner4.setText("Beetroot: "+"2slice");
        dinner5.setText("Cheese: "+"1 slice");
        dinner6.setText("Low fat mayo: "+"1tbsp");
        dinner7.setText("Sweet potatoes: "+"2large");

        supper1.setText("Tuna: "+"185g");
        supper2.setText("Olive Oil: "+"1 tbsp");
        supper3.setVisibility(view.GONE);
        supper4.setVisibility(view.GONE);
        supper5.setVisibility(view.GONE);
        supper6.setVisibility(view.GONE);
        supper7.setVisibility(view.GONE);
        //fetchdailytips();
        return view;
    }
}
