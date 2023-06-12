package com.lemonsqueeze.lemonsqueezebe.utility;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import com.lemonsqueeze.lemonsqueezebe.model.entity.Recipe;

public class StartesJuices {
    private static final List<Recipe> starterJuices = Arrays.asList(
        mapDataToRecipeClass(
            "Mango Juice", 
            "Mango is a pulpy fruit which requires more liquids when blended to juice. Plain water adds no nutrition or flavor to the juice.", 
            Arrays.asList(
                "Firstly, in a blender take 1 mango, 1 cup coconut water, few mint and few ice cubes.",
                "Blend to smooth making sure everything is well combined.",
                "Further, pour the mango juice into a glass and enjoy with ice cubes."
            ), 
            "", 
            Arrays.asList(
                "Mango",
                "Coconut Water",
                "Mint",
                "Ice Cubes"
            ), 
            "https://firebasestorage.googleapis.com/v0/b/lemon-squeeze.appspot.com/o/alexander-mils-pPhN8HFzkDE-unsplash.jpg?alt=media&token=bc713671-9e84-4942-82ac-b34bab4beda6"
        ),

        mapDataToRecipeClass(
            "Lemon Juice", 
            "The juice of the lemon is about 5% to 6% citric acid, with a pH of around 2.2, giving it a sour taste. The distinctive sour taste of lemon juice makes it a key ingredient in drinks and foods such as lemonade and lemon meringue pie.", 
            Arrays.asList(
                "Firstly, in a blender take 2 tbsp lemon juice, few mint, ¼ tsp pepper powder and ½ tsp salt.",
                "Add 1½ cup water and few ice cubes.",
                "Blend to smooth making sure everything is well combined.",
                "In a tall glass take 2 tbsp soaked sabja seeds.",
                "Finally, pour the lemon juice into a glass and enjoy with ice cubes."
            ), 
            "", 
            Arrays.asList(
                "Lemon Juice",
                "Mint",
                "pepper Powder",
                "Salt",
                "Water",
                "Ise Cubes",
                "Sabja Seeds"
            ), 
            "https://firebasestorage.googleapis.com/v0/b/lemon-squeeze.appspot.com/o/reza-jahangir-DXWefT8o3dM-unsplash.jpg?alt=media&token=b37615dc-1b89-4cf6-8503-2ca72b60d941"
        )
    );

    public static List<Recipe> getStarterJuices() {
        return starterJuices;
    }

    private static Recipe mapDataToRecipeClass(
        String title,
        String desc,
        List<String> steps,
        String tips,
        List<String> ingredients,
        String imgUrl
    ) {
        Long currentTime = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
        return new Recipe(
            title, 
            desc, 
            steps, 
            tips, 
            ingredients, 
            imgUrl, 
            sdf.format(currentTime)
        );
    }
}
