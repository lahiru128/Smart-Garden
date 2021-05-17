package com.example.smartgarden.TRACKERS;

import com.example.smartgarden.models.Step;

import java.util.ArrayList;
import java.util.List;

public class Tracker {
    List<Step> TomatoStepslist = new ArrayList<>();
    List<Step> ChiliStepList = new ArrayList<>();
    List<Step> BrinjalStepList = new ArrayList<>();

    public Tracker() {
        TomatoStepslist.add(new Step("ns0", "", "0", "", "","", ""));
        TomatoStepslist.add(new Step("nsi", "Dig soil and put seeds and water", "0", "", "","", ""));
        TomatoStepslist.add(new Step("nsw", "Keep Watering", "2", "", "","", ""));
        TomatoStepslist.add(new Step("nsf", "Put Fertilizer", "8", "", "","", ""));
        TomatoStepslist.add(new Step("check1t", "Are there any new leaves or roots?", "0", "bool", "Yes","No", "nsf"));
        TomatoStepslist.add(new Step("ns1", "Support the tomato plant using cases, stakes and trellises.", "0", "", "","", ""));
        TomatoStepslist.add(new Step("ns2", "Moist the soil using water", "4", "", "","", ""));
        TomatoStepslist.add(new Step("ns3", "Add crushed egg shells to the soil", "0", "", "","", ""));
        TomatoStepslist.add(new Step("ns4", "Keep watering", "5", "", "","", ""));
        TomatoStepslist.add(new Step("ns5", "Control weed around the tomato plant", "0", "", "","", ""));
        TomatoStepslist.add(new Step("check2t", "Check the number of leaves", "0", "bool", "10-13 Leaves","Few Leaves", "check2"));
        TomatoStepslist.add(new Step("ns6", "Apply urea to the plant", "0", "", "","", ""));
        TomatoStepslist.add(new Step("check3t", "Are there any flowers", "0", "bool", "Yes","No", ""));
        TomatoStepslist.add(new Step("ns7", "Continue watering", "3", "", "","", ""));
        TomatoStepslist.add(new Step("ns8", "Keep watering", "3", "", "","", ""));
        TomatoStepslist.add(new Step("ns9", "Add egg shells to the soil", "0", "", "","", ""));
        TomatoStepslist.add(new Step("ns10", "Keep watering", "3", "", "","", ""));
        TomatoStepslist.add(new Step("check4t", "Are tomatoes mature green?", "0", "bool", "Yes","No", ""));
        TomatoStepslist.add(new Step("nsfinal", "Harvest time!", "0", "", "","", ""));

        ChiliStepList.add(new Step("ns0", "", "0", "", "","", ""));
        ChiliStepList.add(new Step("nsi", "Dig soil and put seeds and spray water \n - Instructions - \n + You will need some good potting compost" +
                "\n + Fill the plant pot around 3/4 full with compost \n + Make sure the soil has no hard lumps on it \n + Place a few of the seeds lightly on the top of the soil" +
                "\n Ensure the seeds are evenly spaced out.", "0", "", "","", ""));
        ChiliStepList.add(new Step("nsw", "Water the planted seeds", "21", "", "","", ""));
        ChiliStepList.add(new Step("check1c", "Each seed produces a green shoot?", "0", "bool", "Yes","No", "nsw"));
        ChiliStepList.add(new Step("nsf", "Put fertilizer", "0", "", "","", ""));
        ChiliStepList.add(new Step("ns1", "Keep Watering", "7", "", "","", ""));
        ChiliStepList.add(new Step("check2c", "Are the leaves getting bigger?", "0", "bool", "Good","Not good", "ns1"));
        ChiliStepList.add(new Step("ns2", "Continue Watering", "14", "", "","", ""));
        ChiliStepList.add(new Step("check3c", "Is the plant stronger and starting to grow leaves?", "0", "bool", "Yes","No", ""));
        ChiliStepList.add(new Step("ns3", "Put fertilizer and keep watering again", "28", "", "","", ""));
        ChiliStepList.add(new Step("check4c", "Has plant grown taller and bushy?", "0", "bool", "Yes","No", "ns3"));
        ChiliStepList.add(new Step("ns4", "Add chillie fertilizer", "0", "", "","", ""));
        ChiliStepList.add(new Step("ns5", "Continue watering", "42", "", "","", ""));
        ChiliStepList.add(new Step("check5c", "Have buds started opening and flower?", "0", "bool", "Yes","No", "ns5"));
        ChiliStepList.add(new Step("ns6", "Keep watering", "14", "", "","", ""));
        ChiliStepList.add(new Step("check6c", "Check whether there is first fruit", "0", "bool", "Yes","No", "ns6"));
        ChiliStepList.add(new Step("ns7", "Keep watering and fertilizing", "28", "", "","", ""));
        ChiliStepList.add(new Step("check7c", "Many chilies have started to mature?", "0", "bool", "Yes","No", "ns7"));
        ChiliStepList.add(new Step("nsfinal", "Harvest time!", "0", "", "","", ""));

        BrinjalStepList.add(new Step("ns0", "", "0", "", "","", ""));
        BrinjalStepList.add(new Step("nsi", "Put seed in 10-15cm beds", "0", "", "","", ""));
        BrinjalStepList.add(new Step("nsj", "Cover with a thin soil layer", "0", "", "","", ""));
        BrinjalStepList.add(new Step("nsw", "Start watering and fertilizing", "14", "", "","", ""));
        BrinjalStepList.add(new Step("ns1", "Add Urea", "0", "", "","", ""));
        BrinjalStepList.add(new Step("check1", "Have the sprouts appeared?", "0", "bool", "Yes","No", "ns1"));
        BrinjalStepList.add(new Step("nsf", "Continue Watering", "35", "", "","", ""));
        BrinjalStepList.add(new Step("ns2", "Remove weeds", "0", "", "","", ""));
        BrinjalStepList.add(new Step("check2", "Have the leaves gotten bigger?", "0", "bool", "Yes","No", "ns2"));
        BrinjalStepList.add(new Step("ns3", "Keep watering", "28", "", "","", ""));
        BrinjalStepList.add(new Step("ns4", "Apply urea as fertilizer", "0", "", "","", ""));
        BrinjalStepList.add(new Step("check3", "Are there any flowers or buds?", "0", "bool", "Yes","No", "ns4"));
        BrinjalStepList.add(new Step("ns5", "Continue Watering", "28", "", "","", ""));
        BrinjalStepList.add(new Step("check4", "Have any fruits appeared?", "0", "bool", "Yes","No", ""));
        ChiliStepList.add(new Step("ns6", "Wait before harvesting", "75", "", "","", ""));
        ChiliStepList.add(new Step("nsfinal", "Harvest time!", "0", "", "","", ""));
    }

    public List<Step> getTomatoStepslist() {
        return TomatoStepslist;
    }
    public void setTomatoStepslist(List<Step> tomatoStepslist) {
        this.TomatoStepslist = tomatoStepslist;
    }
    public List<Step> getChiliStepList() {
        return ChiliStepList;
    }

    public void setChiliStepList(List<Step> chiliStepList) {
        ChiliStepList = chiliStepList;
    }

    public List<Step> getBrinjalStepList() {
        return BrinjalStepList;
    }

    public void setBrinjalStepList(List<Step> brinjalStepList) {
        BrinjalStepList = brinjalStepList;
    }
}
